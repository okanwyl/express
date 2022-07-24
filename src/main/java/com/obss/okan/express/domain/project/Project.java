package com.obss.okan.express.domain.project;

import com.obss.okan.express.domain.user.User;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.Instant;
import java.util.HashSet;
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

    @Column(name = "ends_at")
    private Instant endsAt;

    @Embedded
    private ProjectProfile projectProfile;

    @JoinTable(name = "users_attended",
            joinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false))
    @ManyToMany(fetch = EAGER, cascade = CascadeType.PERSIST)
    private Set<User> userAttended = new HashSet<>();

//    @OneToMany(mappedBy = "project", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
//    private Set<Task> tasks = new HashSet<>();

    @Transient
    private boolean active = false;

    public Project(User creator, ProjectContents contents){
        this.creator = creator;
        this.contents = contents;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public ProjectContents getContent() {
        return contents;
    }

    public void setContent(ProjectContents contents) {
        this.contents = contents;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Instant getEndsAt() {
        return endsAt;
    }

    public void setEndsAt(Instant endsAt) {
        this.endsAt = endsAt;
    }

    public ProjectProfile getProjectProfile() {
        return projectProfile;
    }

    public void setProjectProfile(ProjectProfile projectProfile) {
        this.projectProfile = projectProfile;
    }

    public Set<User> getUserAttended() {
        return userAttended;
    }

    public void setUserAttended(Set<User> userAttended) {
        this.userAttended = userAttended;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    protected Project(){

    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var project= (Project) o;
        return creator.equals(project.creator) && contents.getTitle().equals(project.contents.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(creator, contents.getTitle());
    }

}
