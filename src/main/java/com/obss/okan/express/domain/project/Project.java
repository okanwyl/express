package com.obss.okan.express.domain.project;

import com.obss.okan.express.domain.project.task.Task;
import com.obss.okan.express.domain.user.User;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
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

    @Transient
    private final boolean active = false;

    @JoinTable(name = "project_users",
            joinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false))
    @ManyToMany(fetch = EAGER, cascade = CascadeType.PERSIST)
    private final Set<User> attendedUsers = new HashSet<>();

    @OneToMany(mappedBy = "project", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private final Set<Task> tasks = new HashSet<>();

    // @FIMXE should we implement as a queue?
//    @OneToMany(mappedBy = "project", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
//    private final Set<Sprint> sprints = new HashSet<>();


    @Embedded
    private ProjectContents contents;

    @Column(name = "created_at")
    @CreatedDate
    private Instant createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private Instant updatedAt;

    public Project(ProjectContents contents) {
        this.contents = contents;
    }

    protected Project() {
    }

    public Project addUser(User userToAdd) {
        attendedUsers.add(userToAdd);
        return this;
    }

    public void removeUser(long userId) {
        final var userToDelete = attendedUsers.stream().filter(user -> user.getId().equals(userId))
                .findFirst().orElseThrow(NoSuchElementException::new);
        attendedUsers.remove(userToDelete);
    }

    public void updateProject(User user, ProjectUpdateRequest updateRequest) {
        contents.updateProjectContentsIfPresent(updateRequest);
    }

    public Task addTask(User user, String body) {
        if (!checkUserAttendingStatus(user))
            throw new IllegalAccessError("Not authorized request!");
        final var taskToAdd = new Task(this, user, body);
        tasks.add(taskToAdd);
        return taskToAdd;
    }

    public void removeTask(User user, long taskId) {
        if (!checkUserAttendingStatus(user))
            throw new IllegalAccessError("Not authorized request!");
        final var taskToRemove = tasks.stream().filter(task -> task.getId().equals(taskId)).findFirst()
                .orElseThrow(NoSuchElementException::new);
        tasks.remove(taskToRemove);
    }
//
//    public Sprint createSprint(String body) {
//        final var sprintToAdd = new Sprint(this, body);
//        sprints.add(sprintToAdd);
//        return sprintToAdd;
//    }
//
//    public void addTaskToSprint(long taskId, long sprintId) {
//        final var taskToAdd = tasks.stream().filter(task -> task.getId().equals(taskId)).findFirst()
//                .orElseThrow(NoSuchElementException::new);
//        final var usedSprint = sprints.stream()
//                .filter(sprint -> sprint.getId().equals(sprintId)).findFirst()
//                .orElseThrow(NoSuchElementException::new);
//        usedSprint.addTask(taskToAdd);
//
//    }
//
//    public void removeTaskFromSprint(long taskId, long sprintId) {
//        final var usedSprint = sprints.stream()
//                .filter(sprint -> sprint.getId().equals(sprintId)).findFirst()
//                .orElseThrow(NoSuchElementException::new);
//        usedSprint.removeTask(taskId);
//
//    }


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
        return attendedUsers.size();
    }

    public Set<User> getAttenders() {
        return attendedUsers;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public int getTaskCount() {
        return tasks.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        var project = (Project) o;
        return contents.getTitle().equals(project.contents.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(contents.getTitle());
    }


    private boolean checkUserAttendingStatus(User user) {
        return attendedUsers.contains(user);
    }
}
