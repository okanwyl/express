package com.obss.okan.express.domain.project.task;

import com.obss.okan.express.domain.user.User;

import java.util.Optional;

import static java.util.Optional.ofNullable;

public class TaskUpdateRequest {
    private final User assignedToUserUpdate;
    private final String bodyToUpdate;

    private TaskUpdateRequest(TaskUpdateRequestBuilder builder) {
        this.assignedToUserUpdate = builder.assignedToUserUpdate;
        this.bodyToUpdate = builder.bodyToUpdate;
    }

    private TaskUpdateRequest(User userToUpdate, String bodyToUpdate) {
        this.assignedToUserUpdate = userToUpdate;
        this.bodyToUpdate = bodyToUpdate;
    }

    public static TaskUpdateRequestBuilder builder() {
        return new TaskUpdateRequestBuilder();
    }

    Optional<User> getAssignedToUserUpdate() {
        return ofNullable(assignedToUserUpdate);
    }

    Optional<String> getBodyToUpdate() {
        return ofNullable(bodyToUpdate);
    }

    public static class TaskUpdateRequestBuilder {

        private User assignedToUserUpdate;
        private String bodyToUpdate;

        public TaskUpdateRequestBuilder assignedToUserUpdate(User assignedToUserUpdate) {
            this.assignedToUserUpdate = assignedToUserUpdate;
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
