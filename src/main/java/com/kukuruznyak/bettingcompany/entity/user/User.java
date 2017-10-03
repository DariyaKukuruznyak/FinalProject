package com.kukuruznyak.bettingcompany.entity.user;

import com.kukuruznyak.bettingcompany.entity.Model;

import java.util.Date;

public class User extends Model {
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String login;
    protected String password;
    protected Date dateOfRegistration;
    protected UserRole userRole;

    public User() {
    }

    public User(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.dateOfRegistration = user.getDateOfRegistration();
        this.userRole = user.getUserRole();
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(Date dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public String getStringUserRole() {
        return userRole.toString().toLowerCase();
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + this.id + '\'' +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", dateOfRegistration=" + dateOfRegistration +
                ", userRole=" + userRole +
                '}';
    }
}
