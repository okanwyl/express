package com.obss.okan.express.application.project;

import com.obss.okan.express.application.user.ProfileModel;
import com.obss.okan.express.domain.project.Project;
import lombok.Value;

import java.time.ZoneId;
import java.time.ZonedDateTime;


@Value
class ProjectModel {

    ProjectModelNested project;


    static ProjectModel fromProject(Project project) {
        return new ProjectModel(ProjectModelNested.fromProject(project));
    }


    @Value
    static class ProjectModelNested {
        String slug;
        String title;
        String description;
        String body;
        ZonedDateTime createdAt;
        ZonedDateTime updatedAt;
        ProfileModel.ProfileModelNested creator;

        static ProjectModelNested fromProject(Project project) {
            final var contents = project.getContents();
            final var titleFromProject = contents.getTitle();
            return new ProjectModelNested(
                    titleFromProject.getSlug(), titleFromProject.getTitle(),
                    contents.getDescription(), contents.getBody(),
                    project.getCreatedAt().atZone(ZoneId.of("Europe/Istanbul")),
                    project.getUpdatedAt().atZone(ZoneId.of("Europe/Istanbul")),
                    ProfileModel.ProfileModelNested.fromProfile(project.getCreatedBy().getProfile()));

        }
    }
}