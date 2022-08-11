package com.obss.okan.express.domain.project.task;

import com.obss.okan.express.domain.project.ProjectTitle;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class TaskTitle {

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false, unique = true)
    private String slug;


    public static TaskTitle of(String title) {
        return new TaskTitle(title, slugFromTitle(title));
    }

    private TaskTitle(String title, String slug) {
        this.title = title;
        this.slug = slug;
    }

    protected TaskTitle() {
    }

    public static String slugFromTitle(String title) {
        return title.toLowerCase()
                .replaceAll("\\$,'\"|\\s|\\.|\\?", "-")
                .replaceAll("-{2,}", "-")
                .replaceAll("(^-)|(-$)", "");
    }


    public String getTitle() {
        return title;
    }

    public String getSlug() {
        return slug;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskTitle that = (TaskTitle) o;
        return slug.equals(that.slug);
    }

    @Override
    public int hashCode() {
        return Objects.hash(slug);
    }

}
