package com.kukuruznyak.bettingcompany.entity.user;

import com.kukuruznyak.bettingcompany.entity.enums.UserRole;

import java.util.Calendar;

public class User {
    protected UserRole userRole;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String login;
    protected String password;
    protected String securityNumber;
    protected Calendar dateOfRegistration;


    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (getUserRole() != user.getUserRole()) return false;
        if (getFirstName() != null ? !getFirstName().equals(user.getFirstName()) : user.getFirstName() != null)
            return false;
        if (getLastName() != null ? !getLastName().equals(user.getLastName()) : user.getLastName() != null)
            return false;
        if (getEmail() != null ? !getEmail().equals(user.getEmail()) : user.getEmail() != null) return false;
        if (getLogin() != null ? !getLogin().equals(user.getLogin()) : user.getLogin() != null) return false;
        if (getPassword() != null ? !getPassword().equals(user.getPassword()) : user.getPassword() != null)
            return false;
        if (getSecurityNumber() != null ? !getSecurityNumber().equals(user.getSecurityNumber()) : user.getSecurityNumber() != null)
            return false;
        return getDateOfRegistration() != null ? getDateOfRegistration().equals(user.getDateOfRegistration()) : user.getDateOfRegistration() == null;
    }

    @Override
    public int hashCode() {
        int result = getUserRole() != null ? getUserRole().hashCode() : 0;
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getLogin() != null ? getLogin().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getSecurityNumber() != null ? getSecurityNumber().hashCode() : 0);
        result = 31 * result + (getDateOfRegistration() != null ? getDateOfRegistration().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "userRole=" + userRole +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", dateOfRegistration=" + dateOfRegistration +
                '}';
    }
}
