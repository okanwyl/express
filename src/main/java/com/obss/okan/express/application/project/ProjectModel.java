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
        String title;
        String description;
        String body;
        // Set<String> userList;
        ZonedDateTime createdAt;
        ZonedDateTime updatedAt;
        // boolean favorited;
        // int favoritesCount;
        ProfileModel.ProfileModelNested author;

        static ProjectModelNested fromProject(Project project) {
            final var contents = project.getContents();
            final var titleFromArticle = contents.getTitle();
            return new ProjectModelNested(titleFromArticle.getTitle(), contents.getDescription(),
                    contents.getBody(),
                    // contents.getTags().stream().map(Tag::toString).collect(toSet()),
                    project.getCreatedAt().atZone(ZoneId.of("Europe/Istanbul")),
                    project.getUpdatedAt().atZone(ZoneId.of("Europe/Istanbul")),
                    ProfileModel.ProfileModelNested.fromProfile(project.getCreator().getProfile()));
        }
    }
}
