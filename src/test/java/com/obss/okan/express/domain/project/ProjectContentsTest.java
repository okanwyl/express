package com.obss.okan.express.domain.project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.obss.okan.express.domain.project.ProjectUpdateRequest.builder;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class ProjectContentsTest {
    @Test
    void when_updateProject_with_no_update_field_request_expect_not_changed() {
        final var projectContents = sampleProjectContents();
        final var emptyUpdateRequest = builder().build();

        projectContents.updateProjectContentsIfPresent(emptyUpdateRequest);

        assertThatEqualProjectContents(projectContents, sampleProjectContents());
    }

    @Test
    void when_updateArticle_with_all_field_expect_changed(@Mock ProjectTitle titleToUpdate) {
        final var projectContents = sampleProjectContents();
        final Instant endDateToUpdate = Instant.now();
        final var fullUpdateRequest = builder().titleToUpdate(titleToUpdate)
                .descriptionToUpdate("descriptionToUpdate")
                .bodyToUpdate("bodyToUpdate")
                .dateToUpdate(endDateToUpdate)
                .build();

        projectContents.updateProjectContentsIfPresent(fullUpdateRequest);

        assertThat(projectContents.getTitle()).isEqualTo(titleToUpdate);
        assertThat(projectContents.getDescription()).isEqualTo("descriptionToUpdate");
        assertThat(projectContents.getBody()).isEqualTo("bodyToUpdate");
        assertThat(projectContents.getEndDate()).isEqualTo(endDateToUpdate);

    }

    private ProjectContents sampleProjectContents() {
        Instant sampleData = LocalDateTime.parse("04:30 PM, Sat 5/12/2018", DateTimeFormatter.ofPattern("hh:mm a, EEE M/d/uuuu", Locale.US)).atZone(ZoneId.of("Europe/Istanbul")).toInstant();
        return new ProjectContents("description", ProjectTitle.of("title"), "body", sampleData);
    }

    private void assertThatEqualProjectContents(ProjectContents left, ProjectContents right) {
        assertThat(equalsProjectContents(left, right)).isTrue();
    }

    private boolean equalsProjectContents(ProjectContents left, ProjectContents right) {
        if (!left.getTitle().equals(right.getTitle())) {
            return false;
        }
        if (!left.getDescription().equals(right.getDescription())) {
            return false;
        }
        if (!left.getBody().equals(right.getBody())) {
            return false;
        }
        return left.getEndDate().equals(right.getEndDate());
    }


}
