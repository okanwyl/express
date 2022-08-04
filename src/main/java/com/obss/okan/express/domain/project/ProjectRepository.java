package com.obss.okan.express.domain.project;

import com.obss.okan.express.domain.project.task.Task;
import com.obss.okan.express.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.Repository;

import java.awt.print.Pageable;
import java.util.Optional;

// @FIXME COMPLETELY
interface ProjectRepository extends Repository<Project, Long> {
    Project save(Project project);

    // Optional<Project> findAll();

    // Page<Project> findAllByUserAttended(User user, Pageable pageable);

    // Page<Project> findAllByCreator(UserName creatorName, Pageable pageable);

    // Page<Project> findAllByTaskContains(Task task, Pageable pageable);

    Optional<Project> findProjectById(long projectId);

    // Page<Project> findAllByUserAttended(User user, Pageable page);

    // Optional<Project> findFirstByContentsTitleSlug(String slug);

}
