package com.obss.okan.express.application.task;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.WRAPPER_OBJECT;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeName("task")
@JsonTypeInfo(include = WRAPPER_OBJECT, use = NAME)
@Getter
class TaskPostRequestDTO {

    @NotBlank(message = "Task body cannot be empty")
    private final String body;

    @NotBlank(message = "Task body cannot be empty")
    private final String title;
    @JsonCreator
    TaskPostRequestDTO(String body, String title) {
        this.body = body;
        this.title = title;
    }
}
