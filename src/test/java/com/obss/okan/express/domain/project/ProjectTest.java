package com.obss.okan.express.domain.project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectTest {

    @Mock
    private ProjectContents contents;
    @Mock
    private ProjectTitle title;

    @Test
    void when_project_has_different_contents_expect_not_equal_and_hashCode(@Mock ProjectContents otherContents, @Mock ProjectTitle otherTitle) {
        when(contents.getTitle()).thenReturn(title);
        when(otherContents.getTitle()).thenReturn(otherTitle);

        var project = new Project(contents);
        var projectWithOtherContents = new Project(otherContents);

        assertThat(projectWithOtherContents)
                .isNotEqualTo(project)
                .extracting(Project::hashCode)
                .isNotEqualTo(project.hashCode());
    }


}
