package com.obss.okan.express.application.task;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.obss.okan.express.domain.project.task.TaskTitle;
import com.obss.okan.express.domain.project.task.TaskUpdateRequest;
import com.obss.okan.express.domain.user.Email;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.WRAPPER_OBJECT;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;
import static com.obss.okan.express.domain.project.task.TaskUpdateRequest.TaskUpdateRequestBuilder;
import static com.obss.okan.express.domain.project.task.TaskUpdateRequest.builder;
import static java.util.Optional.of;
import static java.util.Optional.ofNullable;

@JsonTypeName("task")
@JsonTypeInfo(include = WRAPPER_OBJECT, use = NAME)
@Getter
public class TaskPutRequestDTO {
    String title;
    String assigneduser;
    String body;

    TaskUpdateRequest toUpdateRequest() {
        return builder().taskTitleToUpdate(ofNullable(title).map(TaskTitle::of).orElse(null))
                .assignedToUserUpdate(ofNullable(assigneduser).map(Email::new).orElse(null))
                .bodyToUpdate(body).build();
    }

}
