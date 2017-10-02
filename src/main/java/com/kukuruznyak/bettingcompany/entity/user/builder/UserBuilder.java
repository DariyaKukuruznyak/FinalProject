package com.kukuruznyak.bettingcompany.entity.user.builder;

import com.kukuruznyak.bettingcompany.entity.user.User;
import com.kukuruznyak.bettingcompany.entity.user.UserRole;

import java.util.Date;

public class UserBuilder {
    private String firstName;
    private String lastName;
    private String email;
    private String login;
    private String password;
    private Date dateOfRegistration = new Date();
    private UserRole userRole;

    public UserBuilder buildFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder buildLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder buildEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder buildLogin(String login) {
        this.login = login;
        return this;
    }

    public UserBuilder buildPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder buildDateOfRegistration(Date dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
        return this;
    }

    public UserBuilder buildUserRole(UserRole userRole) {
        this.userRole = userRole;
        return this;
    }

    public User build() {
        User user = new User();
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setEmail(this.email);
        user.setLogin(this.login);
        user.setPassword(this.password);
        user.setDateOfRegistration(this.dateOfRegistration);
        user.setUserRole(this.userRole);
        return user;
    }
}
