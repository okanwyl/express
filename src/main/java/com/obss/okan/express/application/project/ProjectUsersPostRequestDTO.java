package com.obss.okan.express.application.project;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;

import javax.validation.constraints.NotBlank;


import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.WRAPPER_OBJECT;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeName("project")
@JsonTypeInfo(include = WRAPPER_OBJECT, use = NAME)
@Getter
class ProjectUsersPostRequestDTO {

    @NotBlank(message = "Username cannot be blank")
    private final String username;

    @JsonCreator
    ProjectUsersPostRequestDTO(String username) {
        this.username = username;
    }
}
