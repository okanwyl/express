package com.obss.okan.express.domain.project;

import java.util.Optional;

public interface ProjectFindService {
    Optional<Project> getProjectBySlug(String slug);

}
