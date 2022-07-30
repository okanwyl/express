package com.obss.okan.express.domain.project.task;


import org.springframework.data.repository.Repository;

import java.util.Optional;

interface TaskRepository extends Repository<Task, Long> {

    Optional<Task> findByBody(String body);

    Optional<Task> findById(long taskId);
}
