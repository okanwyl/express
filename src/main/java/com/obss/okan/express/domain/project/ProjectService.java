package com.obss.okan.express.domain.project;

import com.obss.okan.express.domain.user.User;
import com.obss.okan.express.domain.user.UserFindService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.springframework.data.util.Optionals.mapIfAllPresent;

@Service
public class ProjectService implements ProjectFindService {

  private final UserFindService userFindService;
  private final ProjectRepository projectRepository;

  ProjectService(UserFindService userFindService, ProjectRepository projectRepository) {
    this.userFindService = userFindService;
    this.projectRepository = projectRepository;
  }

  @Transactional
  public Project createNewProject(long creatorId, ProjectContents contents) {
    return userFindService
        .findById(creatorId)
        .map(creator -> creator.createProject(contents))
        .map(projectRepository::save)
        .orElseThrow(NoSuchElementException::new);
  }

  @Transactional(readOnly = true)
  public Page<Project> getProjects(Pageable pageable) {
    return projectRepository.findAll(pageable);
  }

  @Transactional
  public Project updateProject(long userId, String title, ProjectUpdateRequest request) {
    return mapIfAllPresent(
            userFindService.findById(userId),
            getProjectByTitle(title),
            (user, project) -> user.updateProject(project, request))
        .orElseThrow(NoSuchElementException::new);
  }

  @Transactional
  public Project addUser(long userId, long userToAddId, String title) {
    User actionTaker = userFindService.findById(userId).orElseThrow(NoSuchElementException::new);
    User userToAdd = userFindService.findById(userId).orElseThrow(NoSuchElementException::new);
    Project project = getProjectByTitle(title).orElseThrow(NoSuchElementException::new);
    return actionTaker.addUserToProject(project, userToAdd);
  }

  @Transactional
  public void removeUser(long userId, long userToRemoveId, String title) {
    Project project = getProjectByTitle(title).orElseThrow(NoSuchElementException::new);
    userFindService
        .findById(userId)
        .ifPresentOrElse(
            user -> user.removeUserFromProject(project, userToRemoveId),
            () -> {
              throw new NoSuchElementException();
            });
  }

  @Override
  public Optional<Project> getProjectByTitle(String title) {
    return Optional.empty();
  }

  @Override
  public Optional<Project> getProjectById(long projectId) {
    return projectRepository.findProjectById(projectId);
  }
  // @TODO implement the task service with user project dependency
}
