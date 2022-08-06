package com.obss.okan.express.application.task;

import com.obss.okan.express.domain.project.task.Task;
import lombok.Value;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Value
class MultipleTaskModel {
    List<TaskModel.TaskModelNested> tasks;

    static MultipleTaskModel fromTasks(Set<Task> tasks) {
        final var tasksCollected = tasks.stream().map(TaskModel.TaskModelNested::fromTask)
                .collect(Collectors.toList());
        return new MultipleTaskModel(tasksCollected);
    }
}
