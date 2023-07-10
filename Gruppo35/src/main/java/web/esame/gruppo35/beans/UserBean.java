package web.esame.gruppo35.beans;

import web.esame.gruppo35.helperClasses.UserRole;
import java.io.Serializable;
import java.util.Date;

public class UserBean implements Serializable {

    private int id;
    private String name;
    private String surname;
    private Date birthDate;
    private String emailAddress;
    private String telephoneNumber;
    private UserRole role;
    private String username;
    private String password;

    public UserBean(){}

    public UserBean(String name, String surname, Date birthDate, String emailAddress, String telephoneNumber, UserRole role, String username, String password) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.emailAddress = emailAddress;
        this.telephoneNumber = telephoneNumber;
        this.role = role;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public UserRole getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
