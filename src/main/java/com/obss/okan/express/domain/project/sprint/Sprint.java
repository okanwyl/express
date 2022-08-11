package com.obss.okan.express.domain.project.sprint;

import com.obss.okan.express.domain.project.Project;
import com.obss.okan.express.domain.project.task.Task;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

@Embeddable
public class Sprint {

    @Column(name = "active")
    private boolean isActive;


    public Sprint(boolean isActive) {
        this.isActive = isActive;
    }

    protected Sprint() {

    }


    public boolean getActive() {
        return isActive;
    }

}
