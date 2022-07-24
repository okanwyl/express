package com.obss.okan.express.domain.project;

import com.obss.okan.express.domain.user.User;
import com.obss.okan.express.domain.user.UserName;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.Repository;

import java.awt.print.Pageable;
import java.util.Optional;

interface ProjectRepository extends Repository<Project, Long> {
    Project save(Project project);

    Page<Project> findAll(Pageable pageable);
    Page<Project> findAllByUserAttended(User user, Pageable pageable);
    Page<Project> findAllByCreator(UserName creator, Pageable pageable);
//    Page<Project> findAllByTask
    Optional<Project> findFirstByContentTitleSlug(String title);

    void deleteProjectByCreatorAndContentTitleAndSlug(User user, String title);
}
