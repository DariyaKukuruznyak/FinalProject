package com.kukuruznyak.bettingcompany.entity.user;

import com.kukuruznyak.bettingcompany.entity.Model;

import java.util.Calendar;

public abstract class User extends Model {
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String login;
    protected String password;
    protected String securityNumber;
    protected Calendar dateOfRegistration;

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

    public String getSecurityNumber() {
        return securityNumber;
    }

    public void setSecurityNumber(String securityNumber) {
        this.securityNumber = securityNumber;
    }

    public Calendar getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(Calendar dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }
}
