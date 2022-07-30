package com.obss.okan.express.application.project;

import com.obss.okan.express.domain.project.Project;
import lombok.Value;
import org.springframework.data.domain.Page;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Value
class MultipleProjectModel {
    List<ProjectModel.ProjectModelNested> projects;
    int projectCount;

    static MultipleProjectModel fromProjects(Page<Project> projects) {
        final var projectsCollected = projects.map(ProjectModel.ProjectModelNested::fromProject)
                .stream().collect(toList());
        return new MultipleProjectModel(projectsCollected, projectsCollected.size());
    }

}
