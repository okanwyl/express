//package com.obss.okan.express.application.project;
//
//import com.fasterxml.jackson.annotation.JsonTypeInfo;
//import com.fasterxml.jackson.annotation.JsonTypeName;
//import com.obss.okan.express.domain.project.ProjectContents;
//import com.obss.okan.express.domain.project.ProjectTitle;
//import lombok.Value;
//
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;
//import java.time.LocalDateTime;
//
//import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.WRAPPER_OBJECT;
//import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;
//
//@JsonTypeName("project")
//@JsonTypeInfo(include = WRAPPER_OBJECT, use = NAME)
//@Value
//class ProfilePostRequestDTO {
//    @NotBlank String title;
//    @NotBlank String description;
//    @NotBlank String body;
//    // @NotNull
//    // Set<> tagList;
//    @NotNull String endDate;
//
//    // @TODO
//    ProjectContents toProjectContents() {
//        return new ProjectContents(description, ProjectTitle.of(title), body, LocalDateTime.parse(endDate));
//    }
//}
