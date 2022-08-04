package com.obss.okan.express.application.project;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.obss.okan.express.domain.project.ProjectTitle;
import com.obss.okan.express.domain.project.ProjectUpdateRequest;
import lombok.Value;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.WRAPPER_OBJECT;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;
import static com.obss.okan.express.domain.project.ProjectUpdateRequest.builder;
import static java.util.Optional.ofNullable;

@JsonTypeName("project")
@JsonTypeInfo(include = WRAPPER_OBJECT, use = NAME)
@Value
class ProjectPutRequestDTO {

    String title;
    String description;
    String body;

    // @FIXME DATE UPDATE
    ProjectUpdateRequest toUpdateRequest() {
        return builder().titleToUpdate(ofNullable(title).map(ProjectTitle::of).orElse(null))
                .descriptionToUpdate(description).bodyToUpdate(body).build();
    }

}
