package com.obss.okan.express.application.project;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.obss.okan.express.application.user.ProfileModel;
import com.obss.okan.express.domain.project.Project;
import com.obss.okan.express.domain.project.task.Task;
import com.obss.okan.express.domain.user.User;
import lombok.Value;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.WRAPPER_OBJECT;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;
import static java.util.stream.Collectors.toSet;


@JsonTypeName("projects")
@JsonTypeInfo(include = WRAPPER_OBJECT, use = NAME)
@Value
class ProjectModelDetailed {

    ProjectModelNestedDetailed project;


    static ProjectModelDetailed fromProject(Project project) {
        return new ProjectModelDetailed(ProjectModelNestedDetailed.fromProject(project));
    }


    @Value
    static class ProjectModelNestedDetailed {
        String slug;
        String title;
        String body;
        Set<String> users;
        Set<String> tasks;
        ZonedDateTime createdAt;
        ZonedDateTime updatedAt;

        static ProjectModelNestedDetailed fromProject(Project project) {
            final var contents = project.getContents();
            final var titleFromProject = contents.getTitle();
            return new ProjectModelNestedDetailed(
                    titleFromProject.getSlug(), titleFromProject.getTitle(),
                    contents.getBody(),
                    project.getAttenders().stream().map(User::toString).collect(toSet()),
                    project.getBacklog().stream().map(Task::toString).collect(toSet()),
                    project.getCreatedAt().atZone(ZoneId.of("Europe/Istanbul")),
                    project.getUpdatedAt().atZone(ZoneId.of("Europe/Istanbul")));

        }
    }
}
