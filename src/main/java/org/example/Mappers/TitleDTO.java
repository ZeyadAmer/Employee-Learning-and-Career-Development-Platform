package org.example.Mappers;

import org.example.Entities.Department;

public class TitleDTO {
    private String name;
    private String department;
    private boolean isManager;
    public TitleDTO() {}
    public TitleDTO(String name, String department, boolean isManager) {
        this.name = name;
        this.department = department;
        this.isManager = isManager;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
    public boolean isManager() {
        return isManager;
    }
    public void setManager(boolean manager) {
        isManager = manager;
    }
}
