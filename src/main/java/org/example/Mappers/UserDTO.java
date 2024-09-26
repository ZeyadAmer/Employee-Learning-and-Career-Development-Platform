package org.example.Mappers;

import org.example.Entities.Department;
import org.example.Entities.Title;
import org.example.Entities.User;

public class UserDTO {
    private String firstName;
    private String lastName;
    private Title title;
    private Department department;
    private User manager;

    public UserDTO() {}
    public UserDTO(String firstName, String lastName, Title title, Department department, User manager) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.department = department;
        this.manager = manager;
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

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }
}
