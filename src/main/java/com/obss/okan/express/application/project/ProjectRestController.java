//package com.obss.okan.express.application.project;
//
//
//import com.obss.okan.express.domain.project.ProjectService;
//import com.obss.okan.express.infrastructure.jwt.UserJWTPayload;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.validation.Valid;
//import java.awt.print.Pageable;
//
//@RestController
//class ProjectRestController {
//    private final ProjectService projectService;
//
//    ProjectRestController(ProjectService projectService) {
//        this.projectService = projectService;
//    }
//
//    // @FIXME
//    @PostMapping("/projects")
//    public ProjectModel postArticle(@AuthenticationPrincipal UserJWTPayload jwtPayload,
//                                    @Valid @RequestBody ProjectPutRequestDTO dto) {
//        var projectCreated = projectService.createNewProject(jwtPayload.getUserId(), dto.toUpdateRequest());
//        return ProjectModel.fromProject(projectCreated);
//    }
//
//    @GetMapping("/projects")
//    public MultipleProjectModel getProjects(Pageable pageable) {
//        final var projects = projectService.getProjects(pageable);
//        return MultipleProjectModel.fromProjects(projects);
//    }
//
//
//}
