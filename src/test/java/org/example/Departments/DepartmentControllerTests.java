package org.example.Departments;

import org.example.Controllers.DepartmentController;
import org.example.Entities.Title;
import org.example.Mappers.DepartmentDTO;
import org.example.Services.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@SpringBootTest
public class DepartmentControllerTests {
    @InjectMocks
    private DepartmentController departmentController;
    @Mock
    private DepartmentService departmentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateDepartment() {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setName("Engineering");

        ResponseEntity<String> response = departmentController.createDepartment(departmentDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Department created", response.getBody());
        verify(departmentService, times(1)).createDepartment(departmentDTO);
    }

    @Test
    public void testDeleteDepartment() {
        String departmentName = "Engineering";

        ResponseEntity<String> response = departmentController.deleteDepartment(departmentName);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Department deleted", response.getBody());
        verify(departmentService, times(1)).deleteDepartment(departmentName);
    }

    @Test
    public void testGetAllDepartments() {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setName("Engineering");

        when(departmentService.getAllDepartments()).thenReturn(Collections.singletonList(departmentDTO));

        ResponseEntity<List<DepartmentDTO>> response = departmentController.getAllDepartments();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertEquals("Engineering", response.getBody().get(0).getName());
        verify(departmentService, times(1)).getAllDepartments();
    }

    @Test
    public void testGetAllDepartmentsTitles() {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setName("Engineering");
        Title title = new Title();
        title.setName("Software Engineer");

        when(departmentService.getAllTitles(departmentDTO)).thenReturn(Collections.singletonList(title));

        ResponseEntity<List<Title>> response = departmentController.getAllDepartmentsTitles(departmentDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertEquals("Software Engineer", response.getBody().get(0).getName());
        verify(departmentService, times(1)).getAllTitles(departmentDTO);
    }

    @Test
    public void testUpdateDepartment() {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setName("Engineering");

        String name = "Engineering";

        ResponseEntity<String> response = departmentController.updateDepartment(departmentDTO, name);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Department updated", response.getBody());
        verify(departmentService, times(1)).updateDepartment(departmentDTO, name);
    }
}
