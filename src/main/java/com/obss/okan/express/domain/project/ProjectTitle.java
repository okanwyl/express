package com.obss.okan.express.domain.project;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Locale;
import java.util.Objects;

@Embeddable
public class ProjectTitle {

    @Column(nullable = false)
    private String title;

    // error prone
    @Column(nullable = false)
    private String slug;

    public static ProjectTitle of(String title) {
        return new ProjectTitle(title, slugFromTitle(title));
    }

    private ProjectTitle(String title, String slug) {
        this.title = title;
        this.slug = slug;
    }

    protected ProjectTitle() {

    }


    private static String slugFromTitle(String title) {
        return title.toLowerCase(Locale.ROOT);
    }

    public String getSlug() {
        return slug;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectTitle that = (ProjectTitle) o;
        return slug.equals(that.slug);
    }

    @Override
    public int hashCode() {
        return Objects.hash(slug);
    }
}
