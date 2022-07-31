package com.obss.okan.express.domain.project;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProjectTitleTest {

    @Test
    void when_projectTitle_has_different_title_expect_not_equal_and_hashCode() {
        var projectTitle = ProjectTitle.of("some title");
        var projectTitleWithDifferentTitle = ProjectTitle.of("other Title");

        assertThat(projectTitleWithDifferentTitle)
                .isNotEqualTo(projectTitle)
                .extracting(ProjectTitle::hashCode)
                .isNotEqualTo(projectTitle.hashCode());
    }

    @Test
        // @TODO add project trailwhitespacing and lowercase checker
    void when_projectTitle_has_same_title_expect_equal_and_hashCode() {
        var projectTitle = ProjectTitle.of("some title");
        var projectTitleWithSameTitle = ProjectTitle.of("some title");

        assertThat(projectTitleWithSameTitle)
                .isEqualTo(projectTitle)
                .hasSameHashCodeAs(projectTitle);
    }
}
