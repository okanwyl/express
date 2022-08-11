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
    private String body;

    public ProjectContents(ProjectTitle title, String body) {
        this.title = title;
        this.body = body;
    }

    protected ProjectContents() {
    }

    public ProjectTitle getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    void updateProjectContentsIfPresent(ProjectUpdateRequest updateRequest) {
        updateRequest.getTitleToUpdate().ifPresent(titleToUpdate -> title = titleToUpdate);
        updateRequest.getBodyToUpdate().ifPresent(bodyToUpdate -> body = bodyToUpdate);
    }
}
