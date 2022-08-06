package com.obss.okan.express.domain.project;

// import org.springframework.scheduling.config.Task;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@Embeddable
public class ProjectContents {
    @Embedded
    private ProjectTitle title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String body;

    @Column(nullable = true)
    private Date endDate;

    public ProjectContents(String description, ProjectTitle title, String body, Date endDate) {
        this.description = description;
        this.title = title;
        this.body = body;
        this.endDate = endDate;
    }

    protected ProjectContents() {}

    public ProjectTitle getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getBody() {
        return body;
    }

    public Date getEndDate() {
        return endDate;
    }

    void updateProjectContentsIfPresent(ProjectUpdateRequest updateRequest) {
        updateRequest.getTitleToUpdate().ifPresent(titleToUpdate -> title = titleToUpdate);
        updateRequest.getDescriptionToUpdate()
                .ifPresent(descriptionToUpdate -> description = descriptionToUpdate);
        updateRequest.getBodyToUpdate().ifPresent(bodyToUpdate -> body = bodyToUpdate);
        updateRequest.getDateToUpdate().ifPresent(endDateToUpdate -> endDate = endDateToUpdate);
    }
}
