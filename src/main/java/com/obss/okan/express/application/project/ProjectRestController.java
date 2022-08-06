package com.obss.okan.express.application.project;


import com.obss.okan.express.domain.project.ProjectService;
import com.obss.okan.express.domain.user.Email;
import com.obss.okan.express.infrastructure.jwt.UserJWTPayload;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.ResponseEntity.of;


@RestController
class ProjectRestController {
    private final ProjectService projectService;

    ProjectRestController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/projects")
    public ProjectModel postArticle(@AuthenticationPrincipal UserJWTPayload jwtPayload, @Valid @RequestBody ProjectPostRequestDTO dto) {
        var projectCreated = projectService.createNewProject(jwtPayload.getUserId(), dto.toProjectContents());
        return ProjectModel.fromProject(projectCreated);
    }

    @GetMapping("/projects")
    public MultipleProjectModel getProjects(@AuthenticationPrincipal UserJWTPayload jwtPayload,
                                            Pageable pageable) {
        final var projects = projectService.getProjects(jwtPayload.getUserId(), pageable);
        return MultipleProjectModel.fromProjects(projects);
    }

    @GetMapping("/projects/feed")
    public MultipleProjectModel getFeed(@AuthenticationPrincipal UserJWTPayload jwtPayload, Pageable pageable) {
        final var projects = projectService.getFeedByUserId(jwtPayload.getUserId(), pageable);
        return MultipleProjectModel.fromProjects(projects);
    }

    @PutMapping("/projects/{slug}")
    public ProjectModel putArticleBySlug(@AuthenticationPrincipal UserJWTPayload jwtPayload, @PathVariable String slug, @RequestBody ProjectPutRequestDTO dto) {
        final var projectUpdated = projectService.updateProject(jwtPayload.getUserId(), slug, dto.toUpdateRequest());
        return ProjectModel.fromProject(projectUpdated);
    }


    @GetMapping("/projects/{slug}")
    public ResponseEntity<Object> getProjectBySlug(@AuthenticationPrincipal UserJWTPayload jwtPayload, @PathVariable String slug) {
        final var projectToShow = projectService.getProjectBySlug(slug);
        if (!projectToShow.map(project -> project.getAttenders().contains(jwtPayload)).get())
            return new ResponseEntity<Object>("You are not authorized to view this project", new HttpHeaders(), HttpStatus.FORBIDDEN);
        return of(projectToShow.map(ProjectModel::fromProject));
    }

    @PostMapping("/projects/{slug}/user")
    public ProjectModel postUserToProject(@AuthenticationPrincipal UserJWTPayload jwtPayload,
                                          @PathVariable String slug,
                                          @Valid @RequestBody ProjectUsersPostRequestDTO dto) {
        final var projectUpdated = projectService.addUserToProject(jwtPayload.getUserId(), slug, Long.parseLong(dto.getId()));
        return ProjectModel.fromProject(projectUpdated);
    }

    @ResponseStatus(NO_CONTENT)
    @PostMapping("/projects/{slug}/deleteuser")
    public void deleteUserFromProject(@AuthenticationPrincipal UserJWTPayload jwtPayload,
                                      @PathVariable String slug,
                                      @Valid @RequestBody ProjectUsersPostRequestDTO dto) {
        projectService.deleteUserFromProject(jwtPayload.getUserId(), slug, Long.parseLong(dto.getId()));
    }

//    @ResponseStatus(NO_CONTENT)
//    @DeleteMapping("/projects/{slug}")
//    public void deleteProjectBySlug(@AuthenticationPrincipal UserJWTPayload jwtPayload,
//                                    @PathVariable String slug) {
//        projectService.deleteProjectBySlug(jwtPayload.getUserId(), slug);
//    }

}
