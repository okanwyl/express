package com.obss.okan.express.domain.project.task;

import com.obss.okan.express.domain.user.Email;
import com.obss.okan.express.domain.user.User;

import java.util.Optional;

import static java.util.Optional.ofNullable;

public class TaskUpdateRequest {
    private final Email assignedToUserUpdate;
    private final TaskTitle taskTitleToUpdate;
    private final String bodyToUpdate;

    public static TaskUpdateRequestBuilder builder() {
        return new TaskUpdateRequestBuilder();
    }

    Optional<Email> getAssignedToUserUpdate() {
        return ofNullable(assignedToUserUpdate);
    }

    Optional<String> getBodyToUpdate() {
        return ofNullable(bodyToUpdate);
    }

    Optional<TaskTitle> getTaskTitleToUpdate() {
        return ofNullable(taskTitleToUpdate);
    }
    private TaskUpdateRequest(TaskUpdateRequestBuilder builder) {
        this.assignedToUserUpdate = builder.assignedToUserUpdate;
        this.taskTitleToUpdate = builder.taskTitleToUpdate;
        this.bodyToUpdate = builder.bodyToUpdate;
    }


    public static class TaskUpdateRequestBuilder {

        private Email assignedToUserUpdate;
        private TaskTitle taskTitleToUpdate;
        private String bodyToUpdate;

        public TaskUpdateRequestBuilder assignedToUserUpdate(Email assignedToUserUpdate) {
            this.assignedToUserUpdate = assignedToUserUpdate;
            return this;
        }

        public TaskUpdateRequestBuilder taskTitleToUpdate(TaskTitle taskTitleToUpdate) {
            this.taskTitleToUpdate = taskTitleToUpdate;
            return this;
        }
        public TaskUpdateRequestBuilder bodyToUpdate(String bodyToUpdate) {
            this.bodyToUpdate = bodyToUpdate;
            return this;
        }

        public TaskUpdateRequest build() {
            return new TaskUpdateRequest(this);
        }

    }
}
