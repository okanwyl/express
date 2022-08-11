package com.obss.okan.express.domain.project.task;


import com.obss.okan.express.domain.project.Project;
import org.springframework.data.repository.Repository;

import java.util.Optional;

interface TaskRepository extends Repository<Task, Long> {


    Optional<Task> findFirstByTitleSlug(String slug);

    Optional<Task> findById(long taskId);
}
