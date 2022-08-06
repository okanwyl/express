package com.obss.okan.express.domain.project;

import com.obss.okan.express.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.Optional;

interface ProjectRepository extends Repository<Project, Long> {
    Project save(Project project);


    Page<Project> findAll(Pageable pageable);


    Page<Project> findAllByUserAdded(User user, Pageable pageable);

    Optional<Project> findFirstByContentsTitleSlug(String slug);

//    void deleteProjectByContentsTitleSlug(User user, String slug);

}
