package com.obss.okan.express.domain.project.task;

import com.obss.okan.express.domain.project.ProjectFindService;
import com.obss.okan.express.domain.user.UserFindService;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
  private final ProjectFindService projectFindService;
  private final UserFindService userFindService;

  TaskService(ProjectFindService projectFindService, UserFindService userFindService) {
    this.projectFindService = projectFindService;
    this.userFindService = userFindService;
  }
  //
  //    @Transactional
  //    public Task createTask(long userId, String slug, String body) {
  //        return mapIfAllPresent(userFindService.findById(userId),
  // projectFindService.getProjectByTitle(slug),
  //                (user, article) -> user.ad(article, body))
  //                .orElseThrow(NoSuchElementException::new);
  //    }

  //    @Transactional(readOnly = true)
  //    public Set<Task> getComments(long userId, String slug) {
  //        return mapIfAllPresent(userFindService.findById(userId),
  // projectFindService.getProjectByTitle(slug),
  //                User::)
  //                .orElseThrow(NoSuchElementException::new);
  //    }
  //
  //    @Transactional(readOnly = true)
  //    public Set<Comment> getComments(String slug) {
  //        return articleFindService.getArticleBySlug(slug)
  //                .map(Article::getComments)
  //                .orElseThrow(NoSuchElementException::new);
  //    }
  //
  //    @Transactional
  //    public void deleteCommentById(long userId, String slug, long commentId) {
  //        final var articleContainsComments = articleFindService.getArticleBySlug(slug)
  //                .orElseThrow(NoSuchElementException::new);
  //        userFindService.findById(userId)
  //                .ifPresentOrElse(user -> user.deleteArticleComment(articleContainsComments,
  // commentId),
  //                        () -> {throw new NoSuchElementException();});
  //    }

}
