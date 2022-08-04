package com.obss.okan.express.domain.project.sprint;

import com.obss.okan.express.domain.project.Project;
import com.obss.okan.express.domain.project.task.Task;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

@Table(name = "sprints")
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Sprint {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Project project;

    @JoinColumn(name = "project_tasks", referencedColumnName = "id", nullable = false)
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Task> tasks;

    @Column(name = "created_at")
    @CreatedDate
    private Instant createdAt;


    // @Column(name = "modified_at")
    // @LastModifiedDate
    // private Instant modifiedAt;

    // @Column(name = "ends_at")
    // @Date
    // private Instant endsAt;

    @Column(name = "body", nullable = false)
    private String body;

    @Transient
    private final boolean active = false;

    public Sprint(Project project, String body) {
        this.project = project;
        this.body = body;
    }

    protected Sprint() {

    }

    public Long getId() {
        return id;
    }

    public Project getProject() {
        return project;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    // public Instant getEndsAt() {
    // return endsAt;
    // }


    public String getBody() {
        return body;
    }

    public void addTask(long taskId) {
        final var taskToAdd =
                project.getTasks().stream().filter(task -> task.getId().equals(taskId)).findFirst()
                        .orElseThrow(NoSuchElementException::new);
        tasks.add(taskToAdd);
    }

    public void removeTask(long taskId) {
        final var taskToRemove = tasks.stream().filter(task -> task.getId().equals(taskId))
                .findFirst().orElseThrow(NoSuchElementException::new);
        tasks.remove(taskToRemove);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        var sprint = (Sprint) o;
        return project.equals(sprint.project) && body.equals(sprint.body)
                && Objects.equals(createdAt, sprint.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(project, createdAt, body);
    }
}
