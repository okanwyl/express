package com.obss.okan.express.domain.user;

import com.obss.okan.express.domain.project.Project;
import com.obss.okan.express.domain.project.ProjectContents;
import com.obss.okan.express.domain.project.ProjectUpdateRequest;
import com.obss.okan.express.domain.project.task.Task;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static javax.persistence.CascadeType.REMOVE;

@Table(name = "users")
@Entity
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Embedded
    private Email email;

    @Embedded
    private Profile profile;

    @Embedded
    private Password password;

    private UserType type;

    static User of(Email email, UserName name, Password password, UserType type) {
        return new User(email, new Profile(name), password, type);
    }

    private User(Email email, Profile profile, Password password, UserType type) {
        this.email = email;
        this.profile = profile;
        this.password = password;
        this.type = type;
    }

    protected User() {
    }

    public Project createProject(ProjectContents contents) {
        return new Project(this, contents);
    }

    public Project updateProject(Project project, ProjectUpdateRequest request) {
        if (project.getCreator() != this || this.type != UserType.SYSADMIN) {
            throw new IllegalAccessError("Not authorized to update this article");
        }
        project.updateProject(request);
        return project;
    }

    public Task createTaskToProject(Project project, String body) {
        return project.addTask(this, body);
    }


    public void deleteProjectTask(Project project, long taskId) {
        project.removeTaskByUser(this, taskId);
    }

    // @TODO
    //
    public Set<Task> viewProjectTasks(Project project) {
        return project.getTasks().stream()
                .map(this::viewTask)
                .collect(Collectors.toSet());
    }

    Task viewTask(Task task) {
        viewProfile(task.getCreator());
        return task;
    }


    public Profile getProfile() {
        return profile;
    }

    boolean matchesPassword(String rawPassword, PasswordEncoder passwordEncoder) {
        return password.matchesPassword(rawPassword, passwordEncoder);
    }

    void changeEmail(Email email) {
        this.email = email;
    }

    void changePassword(Password password) {
        this.password = password;
    }

    void changeName(UserName userName) {
        profile.changeUserName(userName);
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
        return email;
    }

    public UserName getName() {
        return profile.getUserName();
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final var user = (User) o;
        return email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    Profile viewProfile(User user) {
        return user.profile;
    }
}
