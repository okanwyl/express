package com.obss.okan.express.domain.project;

import com.obss.okan.express.domain.user.UserFindService;
import com.obss.okan.express.domain.user.UserName;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.NoSuchElementException;
import java.util.Optional;

public class ProjectService implements ProjectFindService {

    private final UserFindService userFindService;
    private final ProjectRepository projectRepository;


    ProjectService(UserFindService userFindService, ProjectRepository projectRepository) {
        this.userFindService = userFindService;
        this.projectRepository = projectRepository;
    }

    @Transactional
    public Project createNewProject(long creatorId, ProjectContents contents) {
        return userFindService.findById(creatorId)
                .map(creator -> creator.createProject(contents))
                .map(projectRepository::save)
                .orElseThrow(NoSuchElementException::new);
    }

    @Transactional(readOnly = true)
    public Page<Project> getProjects(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Page<Project> getProjectByUserId(long userId, Pageable pageable) {
        return userFindService.findById(userId)
                .map(user -> projectRepository.findAllByUserAttended(user, pageable))
                .orElseThrow(NoSuchElementException::new);
    }

    @Transactional(readOnly = true)
    public Page<Project> getProjectByCreator(UserName creatorName, Pageable pageable){
        return projectRepository.findAllByCreator()
    }

    @Override
    public Optional<Project> getProjectBySlug(String slug) {
        return Optional.empty();
    }
}
