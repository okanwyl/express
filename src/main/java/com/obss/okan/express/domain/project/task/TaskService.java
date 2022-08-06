package com.obss.okan.express.domain.project.task;

import com.obss.okan.express.domain.project.Project;
import com.obss.okan.express.domain.project.ProjectFindService;
import com.obss.okan.express.domain.user.User;
import com.obss.okan.express.domain.user.UserFindService;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import static org.springframework.data.util.Optionals.mapIfAllPresent;

@Service
public class TaskService {

    private final UserFindService userFindService;
    private final ProjectFindService projectFindService;

    TaskService(UserFindService userFindService, ProjectFindService projectFindService) {
        this.userFindService = userFindService;
        this.projectFindService = projectFindService;
    }

    @Transactional
    public Task createTask(long userId, String slug, String body) {
        return mapIfAllPresent(userFindService.findById(userId), projectFindService.getProjectBySlug(slug),
                (user, article) -> user.createAndAddTaskToProject(article, body))
                .orElseThrow(NoSuchElementException::new);
    }

    @Transactional(readOnly = true)
    public Set<Task> getTasks(long userId, String slug) {
        return mapIfAllPresent(userFindService.findById(userId), projectFindService.getProjectBySlug(slug),
                User::viewProjectTasks)
                .orElseThrow(NoSuchElementException::new);
    }

    @Transactional(readOnly = true)
    public Set<Task> getTasks(String slug) {
        return projectFindService.getProjectBySlug(slug)
                .map(Project::getTasks)
                .orElseThrow(NoSuchElementException::new);
    }

    @Transactional
    public void deleteTaskById(long userId, String slug, long taskId) {
        final var projectContainsTask = projectFindService.getProjectBySlug(slug)
                .orElseThrow(NoSuchElementException::new);
        userFindService.findById(userId)
                .ifPresentOrElse(user -> user.deleteProjectTask(projectContainsTask, taskId),
                        () -> {
                            throw new NoSuchElementException();
                        });
    }

}
