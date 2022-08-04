package com.obss.okan.express.domain.user;

import com.obss.okan.express.domain.project.Project;
import com.obss.okan.express.domain.project.ProjectContents;
import com.obss.okan.express.domain.project.ProjectUpdateRequest;
import com.obss.okan.express.domain.project.sprint.Sprint;
import com.obss.okan.express.domain.project.task.Task;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.Objects;

@Table(name = "users")
@Entity
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Embedded
    private Profile profile;

    @Embedded
    private Password password;

    @Column(name = "type")
    private UserType type;

    static User of(Email address, Password password, UserType type) {
        return new User(new Profile(address), password, type);
    }

    private User(Profile profile, Password password, UserType type) {
        this.profile = profile;
        this.password = password;
        this.type = type;
    }

    protected User() {}


    public void setType(UserType type) {
        this.type = type;
    }

    public Project createProject(ProjectContents contents) {
        if (!checkUserPermission(this)) {
            throw new IllegalAccessError("Not authorized access request!");
        }
        return new Project(contents);
    }

    public Project updateProject(Project project, ProjectUpdateRequest request) {
        if (!checkUserPermission(this)) {
            throw new IllegalAccessError("Not authorized access request!");
        }
        project.updateProject(this, request);
        return project;
    }

    public Task createAndAddTaskToProject(Project project, String body) {
        return project.addTask(this, body);
    }

    public void deleteTaskFromProject(Project project, long taskId) {
        project.removeTask(this, taskId);
    }


    public Project addUserToProject(Project project, User user) {
        if (!checkUserPermission(this)) {
            throw new IllegalAccessError("Not authorized access request!");
        }
        return project.addUser(user);
    }

    public void removeUserFromProject(Project project, long userId) {
        if (!checkUserPermission(this)) {
            throw new IllegalAccessError("Not authorized access request!");
        }
        project.removeUser(userId);
    }

    // public Sprint createAndAddSprintToProject(Project project, String body) {
    // if (!checkUserPermission(this)) {
    // throw new IllegalAccessError("Not authorized access request!");
    // }
    // return project.createSprint(body);
    // }
    //
    // public void addTaskToSprint(Project project, long sprintId, long taskId) {
    // if (!checkUserPermission(this)) {
    // throw new IllegalAccessError("Not authorized access request!");
    // }
    // project.addTaskToSprint(taskId, sprintId);
    //
    // }

    // public void removeTaskFromSprint(Project project, long sprintId, long taskId) {
    // if (!checkUserPermission(this)) {
    // throw new IllegalAccessError("Not authorized access request!");
    // }
    // project.addTaskToSprint(taskId, sprintId);
    //
    // }


    public Profile getProfile() {
        return profile;
    }

    boolean matchesPassword(String rawPassword, PasswordEncoder passwordEncoder) {
        return password.matchesPassword(rawPassword, passwordEncoder);
    }

    void changeEmail(Email email) {
        this.profile.changeEmail(email);
    }

    void changePassword(Password password) {
        this.password = password;
    }

    void changeName(String name) {
        profile.changeName(name);
    }

    void changeSurname(String surname) {
        profile.changeSurname(surname);
    }

    void changeBio(String bio) {
        profile.changeBio(bio);
    }

    void changeImage(Image image) {
        profile.changeImage(image);
    }

    public Long getId() {
        return id;
    }

    public Email getEmail() {
        return profile.getEmail();
    }

    public String getName() {
        return profile.getName();
    }

    public String getSurname() {
        return profile.getSurname();
    }

    public UserType getType() {
        return type;
    }

    String getBio() {
        return profile.getBio();
    }

    Image getImage() {
        return profile.getImage();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final var user = (User) o;
        return profile.getEmail().equals(user.profile.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(profile.getEmail());
    }

    Profile viewProfile(User user) {
        return user.profile;
    }

    private boolean checkUserPermission(User user) {
        return user.getType().equals(UserType.PROJECT_MANAGER)
                || user.getType().equals(UserType.SYSADMIN);
    }
}
