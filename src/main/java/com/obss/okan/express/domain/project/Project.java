package com.obss.okan.express.domain.project;

import com.obss.okan.express.domain.exception.NotAuthorizedRequestException;
import com.obss.okan.express.domain.project.task.Task;
import com.obss.okan.express.domain.project.task.TaskService;
import com.obss.okan.express.domain.project.task.TaskTitle;
import com.obss.okan.express.domain.user.User;
import com.obss.okan.express.domain.user.UserName;
import com.obss.okan.express.domain.user.UserType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REMOVE;
import static javax.persistence.FetchType.EAGER;

@Table(name = "projects")
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Project {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Transient
    private boolean attended = false;

    @JoinTable(name = "project_users",
            joinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id",
                    nullable = false),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id",
                    nullable = false))
    @ManyToMany(fetch = EAGER, cascade = PERSIST)
    private final Set<User> userAdded = new HashSet<>();

    @OneToMany(mappedBy = "project", cascade = {PERSIST, REMOVE}, fetch = EAGER)
    private final Set<Task> backlog = new HashSet<>();

    // @FIMXE should we implement as a queue?
    // @OneToMany(mappedBy = "project", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    // private final Set<Sprint> sprints = new HashSet<>();


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
        userAdded.add(userToAdd);
        return this;
    }

    public void removeUser(UserName userId) {
        final var userToDelete = userAdded.stream().filter(user -> user.getProfile().getUserName().equals(userId))
                .findFirst().orElseThrow(NoSuchElementException::new);
        userAdded.remove(userToDelete);
    }

    public void updateProject(User user, ProjectUpdateRequest updateRequest) {
        contents.updateProjectContentsIfPresent(updateRequest);
    }

    public Task addTask(User user, String body, String title) {
        boolean checkTeamLeader = userAdded.contains(user) || user.getType().equals(UserType.TEAM_LEADER);
        if (user.getType().equals(UserType.SYSADMIN) || user.getType().equals(UserType.PROJECT_MANAGER)
        || checkTeamLeader)
        {
            final var taskToAdd = new Task(this, user, TaskTitle.of(title), body);
            backlog.add(taskToAdd);
            return taskToAdd;
        }
        throw new NotAuthorizedRequestException("Not authorized");
    }

    public void removeTask(User user, long taskId) {
        if (checkUserAttendingStatus(user.getId())) {
            throw new NotAuthorizedRequestException("Not authorized request");
        }
        final var taskToRemove = backlog.stream().filter(task -> task.getId().equals(taskId))
                .findFirst().orElseThrow(NoSuchElementException::new);
        backlog.remove(taskToRemove);
    }

    public Project updateAttendedByUser(User user) {
        attended = userAdded.contains(user);
        return this;
    }
    //
    // public Sprint createSprint(String body) {
    // final var sprintToAdd = new Sprint(this, body);
    // sprints.add(sprintToAdd);
    // return sprintToAdd;
    // }
    //
    // public void addTaskToSprint(long taskId, long sprintId) {
    // final var taskToAdd = tasks.stream().filter(task -> task.getId().equals(taskId)).findFirst()
    // .orElseThrow(NoSuchElementException::new);
    // final var usedSprint = sprints.stream()
    // .filter(sprint -> sprint.getId().equals(sprintId)).findFirst()
    // .orElseThrow(NoSuchElementException::new);
    // usedSprint.addTask(taskToAdd);
    //
    // }
    //
    // public void removeTaskFromSprint(long taskId, long sprintId) {
    // final var usedSprint = sprints.stream()
    // .filter(sprint -> sprint.getId().equals(sprintId)).findFirst()
    // .orElseThrow(NoSuchElementException::new);
    // usedSprint.removeTask(taskId);
    //
    // }


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
        return userAdded.size();
    }

    public Set<User> getAttenders() {
        return userAdded;
    }

    public Set<Task> getBacklog() {
        return backlog;
    }

    public Set<Task> getBacklogWithUser(long userId) {
        if (!checkUserAttendingStatus(userId)) {
            throw new NotAuthorizedRequestException("Not authorized request");
        }
        return backlog;
    }

    public int getTaskCount() {
        return backlog.size();
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


    private boolean checkUserAttendingStatus(long userId) {
        final var userToLook = userAdded.stream().filter(user -> user.getId().equals(userId)).findFirst();
        if (userToLook.isPresent()) {
            return true;
        }
        return false;

    }
}
