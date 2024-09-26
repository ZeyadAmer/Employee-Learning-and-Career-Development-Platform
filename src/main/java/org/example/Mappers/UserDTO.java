package org.example.Mappers;

import org.example.Entities.Department;
import org.example.Entities.Title;
import org.example.Entities.User;

public class UserDTO {
    private String firstName;
    private String lastName;
    private String title;
    private String department;
    private String managerEmail;
    private String email;

    public UserDTO() {}
    public UserDTO(String firstName, String lastName, String title, String department, String managerEmail, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.department = department;
        this.managerEmail = managerEmail;
        this.email = email;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getManager() {
        return managerEmail;
    }

    public void setManager(String managerEmail) {
        this.managerEmail = managerEmail;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
