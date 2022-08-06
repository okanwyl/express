package com.obss.okan.express.application.task;

import com.obss.okan.express.application.user.ProfileModel;
import com.obss.okan.express.domain.project.task.Task;
import lombok.Value;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Value
public class TaskModel {

    TaskModelNested task;

    static TaskModel fromTask(Task task) {
        return new TaskModel(TaskModelNested.fromTask(task));


    }

    @Value
    static class TaskModelNested {
        long id;
        String body;
        ZonedDateTime createdAt;
        ZonedDateTime updatedAt;
        ProfileModel.ProfileModelNested assignedTo;
        ProfileModel.ProfileModelNested createdBy;

        static TaskModelNested fromTask(Task task) {
            return new TaskModelNested(task.getId(),
                    task.getBody(),
                    task.getCreatedAt().atZone(ZoneId.of("Europe/Istanbul")),
                    task.getUpdatedAt().atZone(ZoneId.of("Europe/Istanbul")),
                    ProfileModel.ProfileModelNested.fromProfile(task.getAssignedToUser().getProfile()),
                    ProfileModel.ProfileModelNested.fromProfile(task.getCreator().getProfile()));

        }
    }
}