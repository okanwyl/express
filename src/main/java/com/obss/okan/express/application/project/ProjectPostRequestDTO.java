package com.obss.okan.express.application.project;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.obss.okan.express.domain.project.Project;
import com.obss.okan.express.domain.project.ProjectContents;
import com.obss.okan.express.domain.project.ProjectTitle;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.WRAPPER_OBJECT;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeName("project")
@JsonTypeInfo(include = WRAPPER_OBJECT, use = NAME)
@Value
class ProjectPostRequestDTO {
    @NotBlank(message = "Project title cannot be empty!")
    String title;

    @NotBlank(message = "Project body cannot be empty!")
    String body;
//    @NotNull String endDate;
//    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // @TODO
    ProjectContents toProjectContents() {
//            return new ProjectContents(ProjectTitle.of(title), body,
//                    format.parse(endDate));
        return new ProjectContents(ProjectTitle.of(title), body);
    }
}