package com.obss.okan.express.domain.project;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class ProjectTitle {

    @Column(nullable = false)
    private String title;

    private ProjectTitle(String title) {
        this.title = title;
    }

    protected ProjectTitle() {
    }

    public static ProjectTitle of(String title) {
        return new ProjectTitle(title);
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectTitle that = (ProjectTitle) o;
        return title.equals(that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
