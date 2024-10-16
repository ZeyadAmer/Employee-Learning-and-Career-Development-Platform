package org.example.Departments;

import org.example.Entities.Department;
import org.example.Mappers.DepartmentDTO;
import org.example.Mappers.DepartmentMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DepartmentMapperTest {
    @Autowired
    DepartmentMapper departmentMapper;

    @Test
    void testDepartmentToDepartmentDTO() {
        Department department = new Department();
        department.setName("Engineering");

        DepartmentDTO departmentDTO = departmentMapper.departmentToDepartmentDTO(department);

        assertEquals("Engineering", departmentDTO.getName());
    }

    @Test
    void testDepartmentDTOToDepartment() {
        DepartmentDTO departmentDTO = new DepartmentDTO("Engineering");

        Department department = departmentMapper.departmentDTOToDepartment(departmentDTO);

        assertEquals("Engineering", department.getName());
    }

    @Test
    void testDepartmentsToDepartmentDTOs() {
        Department department = new Department();
        department.setName("Engineering");
        List<DepartmentDTO> departmentDTOs = departmentMapper.departmentsToDepartmentDTOs(Collections.singletonList(department));

        assertEquals(1, departmentDTOs.size());
        assertEquals("Engineering", departmentDTOs.get(0).getName());
    }

    @Test
    void testDepartmentDTOsToDepartments() {
        DepartmentDTO departmentDTO = new DepartmentDTO("Engineering");
        List<Department> departments = departmentMapper.departmentDTOsToDepartments(Collections.singletonList(departmentDTO));

        assertEquals(1, departments.size());
        assertEquals("Engineering", departments.get(0).getName());
    }
}
