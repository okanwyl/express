package com.obss.okan.express.domain.user;

import com.obss.okan.express.domain.exception.NotAuthorizedRequestException;
import com.obss.okan.express.domain.project.Project;
import com.obss.okan.express.domain.project.ProjectContents;
import com.obss.okan.express.domain.project.ProjectUpdateRequest;
//import com.obss.okan.express.domain.project.sprint.Sprint;
import com.obss.okan.express.domain.project.task.Task;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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

    static User of(Email address, UserName userName, Password password, String type) {
        return new User(new Profile(address, userName), password, UserType.lookup(Integer.parseInt(type)));
    }

    private User(Profile profile, Password password, UserType type) {
        this.profile = profile;
        this.password = password;
        this.type = type;
    }

    protected User() {
    }


    public void setType(UserType type) {
        this.type = type;
    }

    public Project createProject(ProjectContents contents) {
        if (!checkUserPermission()) {
            throw new NotAuthorizedRequestException("Not authorized access request!");
        }
        return new Project(contents);
    }

    public Project updateProject(Project project, ProjectUpdateRequest request) {
        if (!checkUserPermission()) {
            throw new NotAuthorizedRequestException("Not authorized access request!");
        }
        project.updateProject(this, request);
        return project;
    }

    public Task createAndAddTaskToProject(Project project, String title, String body) {
        return project.addTask(this, title, body);
    }

    public Project addUserToProject(Project project, User user) {
        if (!checkUserPermission()) {
            throw new NotAuthorizedRequestException("Not authorized access request!");
        }
        return project.addUser(user);
    }

    public void removeUserFromProject(Project project, UserName username) {
        if (!checkUserPermission()) {
            throw new NotAuthorizedRequestException("Not authorized access request!");
        }
        project.removeUser(username);
    }

    public Set<Task> viewProjectTasks(Project project) {
        if (checkUserPermission()) {
            return project.getBacklog();
        }
        return project.getBacklogWithUser(this.getId());
    }

    Task viewTask(Task task) {
//        viewProfile(task.getAssignedToUser());
        return task;
    }

    public void deleteProjectTask(Project project, long taskId) {
        project.removeTask(this, taskId);
    }

    public Profile getProfile() {
        return profile;
    }

    boolean matchesPassword(String rawPassword, PasswordEncoder passwordEncoder) {
        return password.matchesPassword(rawPassword, passwordEncoder);
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

//    Profile viewProfile(User user) {
//        return user.profile;
//    }

    public boolean checkUserPermission() {
        return this.getType().equals(UserType.PROJECT_MANAGER)
                || this.getType().equals(UserType.SYSADMIN);
    }

    //    public boolean checkUserPermissionTeamLeader() {
//        return this.getType().equals(UserType.PROJECT_MANAGER)
//                || this.getType().equals(UserType.SYSADMIN)
//                || this.getType().equals(UserType.TEAM_LEADER);
//    }
    @Override
    public String toString() {
        return this.profile.toString();
    }
}
