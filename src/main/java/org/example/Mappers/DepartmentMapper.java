package org.example.Mappers;

import org.example.Entities.Department;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartmentMapper{
    DepartmentDTO departmentToDepartmentDTO(Department department);
    Department departmentDTOToDepartment(DepartmentDTO departmentDTO);
    List<DepartmentDTO> departmentsToDepartmentDTOs(List<Department> departments);
    List<Department> departmentDTOsToDepartments(List<DepartmentDTO> departmentDTOs);
}
