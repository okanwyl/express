package com.obss.okan.express.domain.project;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import static java.util.Optional.ofNullable;

public class ProjectUpdateRequest {
    private final String bodyToUpdate;
    private final ProjectTitle titleToUpdate;
    private final Date dateToUpdate;


    private ProjectUpdateRequest(ProjectUpdateRequestBuilder builder) {
        this(builder.titleToUpdate, builder.bodyToUpdate, builder.dateToUpdate);
    }

    private ProjectUpdateRequest(ProjectTitle titleToUpdate,
                                 String bodyToUpdate, Date dateToUpdate) {
        this.titleToUpdate = titleToUpdate;
        this.bodyToUpdate = bodyToUpdate;
        this.dateToUpdate = dateToUpdate;
    }

    public static ProjectUpdateRequestBuilder builder() {
        return new ProjectUpdateRequestBuilder();
    }

    Optional<ProjectTitle> getTitleToUpdate() {
        return ofNullable(titleToUpdate);
    }


    Optional<String> getBodyToUpdate() {
        return ofNullable(bodyToUpdate);
    }

    Optional<Date> getDateToUpdate() {
        return ofNullable(dateToUpdate);
    }

    public static class ProjectUpdateRequestBuilder {
        private ProjectTitle titleToUpdate;
        private String bodyToUpdate;
        private Date dateToUpdate;

        public ProjectUpdateRequestBuilder titleToUpdate(ProjectTitle titleToUpdate) {
            this.titleToUpdate = titleToUpdate;
            return this;
        }


        public ProjectUpdateRequestBuilder bodyToUpdate(String bodyToUpdate) {
            this.bodyToUpdate = bodyToUpdate;
            return this;
        }

        public ProjectUpdateRequestBuilder dateToUpdate(Date dateToUpdate) {
            this.dateToUpdate = dateToUpdate;
            return this;
        }

        public ProjectUpdateRequest build() {
            return new ProjectUpdateRequest(this);
        }
    }
}
