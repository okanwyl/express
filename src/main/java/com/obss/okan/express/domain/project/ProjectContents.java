package com.obss.okan.express.domain.project;

//import org.springframework.scheduling.config.Task;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Embeddable
public class ProjectContents {
    @Embedded
    private ProjectTitle title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String body;

    @JoinTable(name = "project_tasks",
            joinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id", nullable = false))
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Set<Task> tasks = new HashSet<>();

    public ProjectContents(String description, ProjectTitle title, String body, Set<Task> tasks) {
        this.description = description;
        this.title = title;
        this.body = body;
        this.tasks = tasks;
    }

    protected ProjectContents() {

    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public ProjectTitle getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getBody() {
        return body;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    void updateProjectContentsIfPresent(ProjectUpdateRequest updateRequest) {
        updateRequest.getTitleToUpdate().ifPresent(titleToUpdate -> title = titleToUpdate);
        updateRequest.getDescriptionToUpdate().ifPresent(descriptionToUpdate -> description = descriptionToUpdate);
        updateRequest.getBodyToUpdate().ifPresent(bodyToUpdate -> body = bodyToUpdate);
    }
}
