package com.obss.okan.express.domain.project;

import com.obss.okan.express.domain.project.task.Task;
import com.obss.okan.express.domain.user.User;
import com.obss.okan.express.domain.user.UserType;
import org.aspectj.bridge.ICommand;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.Instant;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;

@Table(name = "projects")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Project {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @JoinColumn(name = "creator_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = EAGER)
    private User creator;

    @Embedded
    private ProjectContents contents;

    @Column(name = "created_at")
    @CreatedDate
    private Instant createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private Instant updatedAt;


    @JoinTable(name = "users_attended", joinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false))
    @ManyToMany(fetch = EAGER, cascade = CascadeType.PERSIST)
    private Set<User> userAttended = new HashSet<>();

    @JoinTable(name = "project_managers_attended", joinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false))
    @ManyToMany
    private Set<User> projectManagers = new HashSet<>();


    @OneToMany(mappedBy = "project", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<Task> tasks = new HashSet<>();

    @Transient
    private boolean active = false;

    public Project(User creator, ProjectContents contents) {
        this.creator = creator;
        this.contents = contents;
    }

    protected Project() {

    }

    public Project addUserByMasterUser(User user, User masterUser) {
        if (!masterUser.getType().equals(UserType.PROJECT_MANAGER) ||
                !projectManagers.contains(masterUser)) {
            throw new IllegalAccessError("Not authorized to add user");
        }
        userAttended.add(user);
        return this;
    }

    public void removeUserByMasterUser(User masterUser, long userId) {
        if (!masterUser.getType().equals(UserType.PROJECT_MANAGER) ||
                !projectManagers.contains(masterUser)) {
            throw new IllegalAccessError("Not authorized to remove user");
        }
        final var userToDelete = userAttended.stream()
                .filter(streamUser -> streamUser.getId().equals(userId))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
        userAttended.remove(userToDelete);
    }

    public void updateProject(ProjectUpdateRequest updateRequest) {
        contents.updateProjectContentsIfPresent(updateRequest);
    }

    public User getCreator() {
        return creator;
    }


    public ProjectContents getContents() {
        return contents;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public int getAttendedCount() {
        return userAttended.size();
    }

    public int getProjectManagerSize() {
        return projectManagers.size();
    }

    public Set<User> getAttenders() {
        return userAttended;
    }


    public Set<User> getProjectManagers() {
        return projectManagers;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public int getTaskCount() {
        return tasks.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var project = (Project) o;
        return creator.equals(project.creator) && contents.getTitle().equals(project.contents.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(creator, contents.getTitle());
    }
}
