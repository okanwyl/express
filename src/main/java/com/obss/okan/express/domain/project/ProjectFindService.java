package com.obss.okan.express.domain.project;

import java.util.Optional;

public interface ProjectFindService {
    Optional<Project> getProjectByTitle(String title);

    Optional<Project> getProjectById(long projectId);
}
