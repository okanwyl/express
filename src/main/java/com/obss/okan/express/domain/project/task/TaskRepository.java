package com.obss.okan.express.domain.project.task;

import com.obss.okan.express.domain.user.User;
import org.springframework.data.repository.Repository;

import java.util.Optional;

interface TaskRepository extends Repository<Task, Long> {

  Optional<Task> findById(Long id);

  Optional<Task> findByFirstBody(String body);

  Optional<Task> findByAssignedToUser(User user);
}
