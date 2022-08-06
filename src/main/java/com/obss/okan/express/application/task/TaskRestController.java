package com.obss.okan.express.application.task;

import com.obss.okan.express.domain.jwt.JWTPayload;
import com.obss.okan.express.domain.project.task.TaskService;
import com.obss.okan.express.infrastructure.jwt.UserJWTPayload;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static java.util.Optional.ofNullable;

@RestController
class TaskRestController {
    private final TaskService taskService;

    TaskRestController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/projects/{slug}/tasks")
    public TaskModel postTask(@AuthenticationPrincipal UserJWTPayload jwtPayload,
                              @PathVariable String slug,
                              @Valid @RequestBody TaskPostRequestDTO dto) {
        final var taskAdded = taskService.createTask(jwtPayload.getUserId(), slug, dto.getBody());
        return TaskModel.fromTask(taskAdded);
    }

    @GetMapping("/projects/{slug}/tasks")
    public MultipleTaskModel getTasks(@AuthenticationPrincipal UserJWTPayload jwtPayload,
                                      @PathVariable String slug) {
        final var tasks = ofNullable(jwtPayload)
                .map(JWTPayload::getUserId)
                .map(userId -> taskService.getTasks(userId, slug))
                .orElseGet(() -> taskService.getTasks(slug));
        return MultipleTaskModel.fromTasks(tasks);
    }

    @DeleteMapping("/projects/{slug}/tasks/{id}")
    public void deleteTask(@AuthenticationPrincipal UserJWTPayload jwtPayload,
                           @PathVariable String slug, @PathVariable long id) {
        taskService.deleteTaskById(jwtPayload.getUserId(), slug, id);
    }

}
