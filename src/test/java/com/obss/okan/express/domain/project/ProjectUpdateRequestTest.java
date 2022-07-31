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
class ProjectUpdateRequestTest {
    private static Instant sampleData = LocalDateTime.parse("04:30 PM, Sat 5/12/2018", DateTimeFormatter.ofPattern("hh:mm a, EEE M/d/uuuu", Locale.US)).atZone(ZoneId.of("Europe/Istanbul")).toInstant();

    @Test
    void when_projectUpdateRequest_created_without_field_expect_get_return_empty() {
        final var requestWithoutFields = builder().build();

        assertThat(requestWithoutFields.getTitleToUpdate()).isEmpty();
        assertThat(requestWithoutFields.getDescriptionToUpdate()).isEmpty();
        assertThat(requestWithoutFields.getBodyToUpdate()).isEmpty();
        assertThat(requestWithoutFields.getDateToUpdate().isEmpty());
    }

    @Test
    void when_projectUpdateRequest_created_with_all_fields_expect_all_fields(@Mock ProjectTitle title) {
        final var requestWithAllFields = builder()
                .titleToUpdate(title)
                .descriptionToUpdate("descriptionToUpdate")
                .bodyToUpdate("bodyToUpdate")
                .dateToUpdate(sampleData)
                .build();

        assertThat(requestWithAllFields).hasNoNullFieldsOrProperties();
        assertThat(requestWithAllFields.getTitleToUpdate()).contains(title);
        assertThat(requestWithAllFields.getDescriptionToUpdate()).contains("descriptionToUpdate");
        assertThat(requestWithAllFields.getBodyToUpdate()).contains("bodyToUpdate");
        assertThat(requestWithAllFields.getDateToUpdate().equals(sampleData));
    }
}
