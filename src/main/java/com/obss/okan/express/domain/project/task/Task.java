package com.obss.okan.express.domain.project.task;

import com.obss.okan.express.domain.project.Project;
import com.obss.okan.express.domain.user.User;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

@Table(name = "tasks")
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Task {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Project project;

    @JoinColumn(name = "creator_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private User creator;

    @JoinColumn(name = "assigned_id", referencedColumnName = "id", nullable = true)
    @ManyToOne(fetch = FetchType.EAGER)
    private User assignedToUser;

    @Column(name = "created_at")
    @CreatedDate
    private Instant createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private Instant updatedAt;

    @Column(name = "body", nullable = false)
    private String body;

    public Task(Project project, User creator, String body) {
        this.project = project;
        this.creator = creator;
        this.body = body;
    }

    protected Task() {
    }

    public Long getId() {
        return id;
    }

    public User getCreator() {
        return creator;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public String getBody() {
        return body;
    }

    public User getAssignedToUser() {
        return assignedToUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var task = (Task) o;
        return project.equals(task.project)
                && creator.equals(task.creator)
                && Objects.equals(createdAt, task.createdAt)
                && body.equals(task.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(project, creator, createdAt, body);
    }
}
