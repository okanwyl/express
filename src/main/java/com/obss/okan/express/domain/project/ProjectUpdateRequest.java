package com.obss.okan.express.domain.project;

import java.time.Instant;
import java.util.Optional;

import static java.util.Optional.ofNullable;

public class ProjectUpdateRequest {
    private final String descriptionToUpdate;
    private final String bodyToUpdate;
    private final ProjectTitle titleToUpdate;

    private final Instant dateToUpdate;

    public static ProjectUpdateRequestBuilder builder() {
        return new ProjectUpdateRequestBuilder();
    }

    Optional<ProjectTitle> getTitleToUpdate() {
        return ofNullable(titleToUpdate);
    }

    Optional<String> getDescriptionToUpdate() {
        return ofNullable(descriptionToUpdate);
    }

    Optional<String> getBodyToUpdate() {
        return ofNullable(bodyToUpdate);
    }

    Optional<Instant> getDateToUpdate() {
        return ofNullable(dateToUpdate);
    }

    private ProjectUpdateRequest(ProjectUpdateRequestBuilder builder) {
        this(builder.titleToUpdate, builder.descriptionToUpdate, builder.bodyToUpdate, builder.dateToUpdate);
    }

    private ProjectUpdateRequest(ProjectTitle titleToUpdate, String descriptionToUpdate, String bodyToUpdate, Instant dateToUpdate) {
        this.titleToUpdate = titleToUpdate;
        this.descriptionToUpdate = descriptionToUpdate;
        this.bodyToUpdate = bodyToUpdate;
        this.dateToUpdate = dateToUpdate;
    }

    public static class ProjectUpdateRequestBuilder {
        private ProjectTitle titleToUpdate;
        private String descriptionToUpdate;
        private String bodyToUpdate;
        private Instant dateToUpdate;

        public ProjectUpdateRequestBuilder titleToUpdate(ProjectTitle titleToUpdate) {
            this.titleToUpdate = titleToUpdate;
            return this;
        }


        public ProjectUpdateRequestBuilder descriptionToUpdate(String descriptionToUpdate) {
            this.descriptionToUpdate = descriptionToUpdate;
            return this;
        }

        public ProjectUpdateRequestBuilder bodyToUpdate(String bodyToUpdate) {
            this.bodyToUpdate = bodyToUpdate;
            return this;
        }

        public ProjectUpdateRequestBuilder dateToUpdate(Instant dateToUpdate) {
            this.dateToUpdate = dateToUpdate;
            return this;
        }

        public ProjectUpdateRequest build() {
            return new ProjectUpdateRequest(this);
        }
    }
}
