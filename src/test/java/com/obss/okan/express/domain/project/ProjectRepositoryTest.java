package com.obss.okan.express.domain.project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.stream.Stream;

import static com.obss.okan.express.domain.user.UserTestUtils.databaseElevatedUser;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@EnableJpaAuditing
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProjectRepositoryTest {
//    private static Instant sampleData = LocalDateTime.parse("04:30 PM, Sat 5/12/2018", DateTimeFormatter.ofPattern("hh:mm a, EEE M/d/uuuu", Locale.US)).atZone(ZoneId.of("Europe/Istanbul")).toInstant();

    @Autowired
    private ProjectRepository repository;


    @MethodSource("provideInvalidProject")
    @ParameterizedTest
    void when_save_invalid_project_expect_DataIntegrityViolationException(Project invalidProject) {
        assertThatThrownBy(() ->
                repository.save(invalidProject)
        ).isInstanceOf(DataIntegrityViolationException.class);
    }


    @Test
    void when_save_project_expect_auditing_works() {
        var contentsToSave = new ProjectContents("description2", ProjectTitle.of("some title2"), "body2", null);
        var projectToSave = databaseElevatedUser().createProject(contentsToSave);

        var articleSaved = repository.save(projectToSave);

        assertThat(articleSaved).hasNoNullFieldsOrProperties();
    }

    private static Stream<Arguments> provideInvalidProject() {
        return provideInvalidProjectContents()
                .map(invalidProjectContents -> new Project(invalidProjectContents))
                .map(Arguments::of);
    }


    private static Stream<ProjectContents> provideInvalidProjectContents() {
        return Stream.of(
                new ProjectContents(null, null, null, null),
                new ProjectContents("description", null, null, null),
                new ProjectContents(null, ProjectTitle.of("title"), null, null),
                new ProjectContents(null, null, "body", null)
        );
    }
}
