package com.obss.okan.express.domain.project;

import com.obss.okan.express.domain.user.User;
import com.obss.okan.express.domain.user.UserFindService;
import com.obss.okan.express.domain.user.UserType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static java.util.Optional.of;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    private ProjectService projectService;

    @Mock
    private UserFindService userFindService;
// @TODO add task service
    @Mock
    private ProjectRepository repository;

    @Spy
    private User actionTaker;

    @BeforeEach
    private void initializeService() {
        projectService = new ProjectService(userFindService, repository);
    }

    @Test
    void given_actionTaker_createNewArticle_then_actionTaker_writeArticle_contents(@Mock ProjectContents contents) {
        given(userFindService.findById(1L)).willReturn(of(actionTaker));
        given(repository.save(any())).willReturn(mock(Project.class));
        actionTaker.setType(UserType.SYSADMIN);
        projectService.createNewProject(1L, contents);

        then(actionTaker).should(times(1)).createProject(contents);
    }

    @Test
    void given_actionTaker_writeProject_then_userRepository_save(@Mock ProjectContents contents, @Mock Project project) {
        given(userFindService.findById(1L)).willReturn(of(actionTaker));
        actionTaker.setType(UserType.SYSADMIN);
        given(actionTaker.createProject(contents)).willReturn(project);
        given(repository.save(project)).willReturn(project);

        projectService.createNewProject(1L, contents);

        then(repository).should(times(1)).save(project);
    }
}
