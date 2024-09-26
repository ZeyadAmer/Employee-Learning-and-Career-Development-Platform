package org.example.Mappers;

import org.example.Entities.Department;

public class TitleDTO {
    private String name;
    private DepartmentDTO departmentDTO;
    private boolean isManager;
    public TitleDTO() {}
    public TitleDTO(String name, DepartmentDTO departmentDTO, boolean isManager) {
        this.name = name;
        this.departmentDTO = departmentDTO;
        this.isManager = isManager;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DepartmentDTO getDepartment() {
        return departmentDTO;
    }

    public void setDepartment(DepartmentDTO departmentDTO) {
        this.departmentDTO = departmentDTO;
    }
    public boolean isManager() {
        return isManager;
    }
    public void setManager(boolean manager) {
        isManager = manager;
    }
}
