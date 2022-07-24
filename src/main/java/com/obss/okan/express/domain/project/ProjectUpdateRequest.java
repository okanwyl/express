package com.obss.okan.express.domain.project;

import java.util.Optional;

import static java.util.Optional.ofNullable;

public class ProjectUpdateRequest {
    private final String descriptionToUpdate;
    private final String bodyToUpdate;
    private final ProjectTitle titleToUpdate;

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

    private ProjectUpdateRequest(ProjectUpdateRequestBuilder builder) {
        this(builder.titleToUpdate, builder.descriptionToUpdate, builder.bodyToUpdate);
    }

    private ProjectUpdateRequest(ProjectTitle titleToUpdate, String descriptionToUpdate, String bodyToUpdate) {
        this.titleToUpdate = titleToUpdate;
        this.descriptionToUpdate = descriptionToUpdate;
        this.bodyToUpdate = bodyToUpdate;
    }

    public static class ProjectUpdateRequestBuilder {
        private ProjectTitle titleToUpdate;
        private String descriptionToUpdate;
        private String bodyToUpdate;

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

        public ProjectUpdateRequest build() {
            return new ProjectUpdateRequest(this);
        }
    }
}
