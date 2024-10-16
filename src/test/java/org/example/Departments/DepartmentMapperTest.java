package org.example.Departments;

import org.example.Entities.Department;
import org.example.Mappers.DepartmentDTO;
import org.example.Mappers.DepartmentMapper;
import org.example.Mappers.TitleMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class DepartmentMapperTest {
    DepartmentMapper departmentMapper;
    @BeforeEach
    public void setUp() {
        departmentMapper = Mappers.getMapper(DepartmentMapper.class);
    }

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
