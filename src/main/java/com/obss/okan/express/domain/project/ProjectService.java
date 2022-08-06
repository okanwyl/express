package com.obss.okan.express.domain.project;

import com.obss.okan.express.domain.user.User;
import com.obss.okan.express.domain.user.UserFindService;
import org.hibernate.cache.spi.access.UnknownAccessTypeException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return userFindService.findById(creatorId).map(creator -> creator.createProject(contents)).map(projectRepository::save).orElseThrow(NoSuchElementException::new);
    }

    @Transactional(readOnly = true)
    public Page<Project> getProjects(long userId, Pageable pageable) {
        if (userFindService.findById(userId).get().checkUserPermission()) return projectRepository.findAll(pageable);
        return Page.empty();
    }

    @Transactional
    public Project updateProject(long userId, String slug, ProjectUpdateRequest request) {
        return mapIfAllPresent(userFindService.findById(userId), getProjectBySlug(slug), (user, project) -> user.updateProject(project, request)).orElseThrow(NoSuchElementException::new);
    }

    @Transactional
    public Project addUserToProject(long userId, String slug, long attempt) {
        final var userToAdd = userFindService.findById(attempt).orElseThrow(NoSuchElementException::new);
        return mapIfAllPresent(
                userFindService.findById(userId), getProjectBySlug(slug),
                (user, project) -> user.addUserToProject(project, userToAdd)).orElseThrow(NoSuchElementException::new);

    }

    @Transactional
    public void deleteUserFromProject(long userId, String slug, long attempt) {
        final var projectToUpdate = getProjectBySlug(slug).orElseThrow(NoSuchElementException::new);
        userFindService.findById(userId)
                .ifPresentOrElse(user -> user.removeUserFromProject(projectToUpdate, attempt),
                        () -> {
                            throw new NoSuchElementException();
                        });
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Project> getProjectBySlug(String slug) {
        return projectRepository.findFirstByContentsTitleSlug(slug);
    }

    @Transactional(readOnly = true)
    public Page<Project> getFeedByUserId(long userId, Pageable pageable) {
        return userFindService.findById(userId).map(user -> projectRepository.findAllByUserAdded(user, pageable).map(project -> project.updateFavoriteByUser(user))).orElseThrow(NoSuchElementException::new);
    }


//    @Transactional
//    public void deleteProjectBySlug(long userId, String slug) {
//        final var userToTakeAction = userFindService.findById(userId).orElseThrow(NoSuchElementException::new);
//        if (userToTakeAction.checkUserPermission()) {
//            projectRepository.deleteProjectByContentsTitleSlug(userToTakeAction, slug);
//        }
//
//    }

}
