package org.example.Mappers;


import org.example.Entities.Department;
import org.example.Entities.Title;
import org.example.Entities.User;

public class UserDTO {
    private String firstName;
    private String lastName;
    private TitleDTO titleDTO;
    private String email;

    public UserDTO() {}
    public UserDTO(String firstName, String lastName, TitleDTO titleDTO, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.titleDTO = titleDTO;
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

    public TitleDTO getTitle() {
        return titleDTO;
    }

    public void setTitle(TitleDTO titleDTO) {
        this.titleDTO = titleDTO;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
