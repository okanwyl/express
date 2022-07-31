//package com.obss.okan.express.domain.project.task;
//
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//import java.util.Optional;
//
//import static org.springframework.data.util.Optionals.mapIfAllPresent;
//
//@Service
//public class TaskService {
//
//    private final TaskRepository taskRepository;
//
//    TaskService(TaskRepository taskRepository) {
//        this.taskRepository = taskRepository;
//    }
//
//    @Transactional
//    public Task updateTask(long userId, String body, TaskUpdateRequest request) {
//        return mapIfAllPresent(userFindService.findById(userId), getProjectByBody(body),
//                (user, task) -> user.updateArticle(article, request))
//                .orElseThrow(NoSuchElementException::new);
//    }
//
//    public Optional<Task> getProjectByBody(String body) {
//        return taskRepository.findByBody(body);
//    }
//}
