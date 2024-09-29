package org.example.Departments;

import org.example.Entities.Department;
import org.example.Entities.Title;
import org.example.Exceptions.ExistsException;
import org.example.Mappers.DepartmentDTO;
import org.example.Mappers.DepartmentMapper;
import org.example.Repositories.DepartmentRepository;
import org.example.Services.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

public class DepartmentServiceTests {
    @Mock
    DepartmentRepository departmentRepository;
    @Mock
    DepartmentMapper departmentMapper;

    @InjectMocks
    DepartmentService departmentService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testCreateDepartment_Success(){
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setName("dept 1");
        Mockito.when(departmentRepository.findByName("dept 1")).thenReturn(Optional.empty());
        departmentService.createDepartment(departmentDTO);
        Mockito.verify(departmentRepository, Mockito.times(1)).findByName("dept 1");
    }
    @Test
    public void testCreateDepartment_DepartmentExists(){
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setName("dept 1");
        Department department = new Department();
        department.setName("dept 1");
        Mockito.when(departmentRepository.findByName("dept 1")).thenReturn(Optional.of(department));
        assertThrows(ExistsException.class,() -> departmentService.createDepartment(departmentDTO));
    }
    @Test
    public void testDeleteDepartment_Success(){
        Department department = new Department();
        department.setName("dept 1");
        Mockito.when(departmentRepository.findByName("dept 1")).thenReturn(Optional.of(department));
        departmentService.deleteDepartment("dept 1");
        Mockito.verify(departmentRepository, Mockito.times(1)).findByName("dept 1");
    }
    @Test
    public void testDeleteDepartment_NotFound(){
        Mockito.when(departmentRepository.findByName("dept 1")).thenReturn(Optional.empty());
        assertThrows(ExistsException.class, () -> departmentService.deleteDepartment("dept 1"));
    }
    @Test
    public void updateDepartment_Success(){
        Department department = new Department();
        department.setName("dept 1");
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setName("dept 1");
        Mockito.when(departmentRepository.findByName("dept 1")).thenReturn(Optional.of(department));
        departmentService.updateDepartment(departmentDTO, "dept 2");
        Mockito.verify(departmentRepository, Mockito.times(1)).findByName("dept 1");
    }
    @Test
    public void testUpdateDepartment_DepartmentNotFound(){
        Mockito.when(departmentRepository.findByName("dept 1")).thenReturn(Optional.empty());
        assertThrows(ExistsException.class, () -> departmentService.updateDepartment(new DepartmentDTO("dept 2"),"dept 1") );
    }
    @Test
    public void testUpdateDepartment_DepartmentExists(){
        Department department = new Department();
        department.setName("dept 1");
        Mockito.when(departmentRepository.findByName("dept 1")).thenReturn(Optional.of(department));
        assertThrows(ExistsException.class, () -> departmentService.updateDepartment(new DepartmentDTO("dept 1"), "dept 1"));
    }
    @Test
    public void testGetAllDepartments_Success(){
        List<Department> departments = new ArrayList<>();
        departments.add(new Department("dept 1"));
        departments.add(new Department("dept 2"));
        List<DepartmentDTO> departmentDTOS = new ArrayList<>();
        departmentDTOS.add(new DepartmentDTO("dept 1"));
        departmentDTOS.add(new DepartmentDTO("dept 2"));
        Mockito.when(departmentRepository.findAll()).thenReturn(departments);
        Mockito.when(departmentMapper.departmentsToDepartmentDTOs(departments)).thenReturn(departmentDTOS);
        List<DepartmentDTO> departmentDTOS1 = departmentService.getAllDepartments();
        assertEquals(departmentDTOS1.get(0).getName(),departmentDTOS.get(0).getName());
        Mockito.verify(departmentRepository, Mockito.times(1)).findAll();
    }
    @Test
    public void testGetAllTitles_Success(){
        List<Title> titles = new ArrayList<>();
        titles.add(new Title(
                new Department("dept 1"),
                "title 1",
                false
        ));
        titles.add(new Title(
                new Department("dept 1"),
                "title 2",
                false
        ));

        Department department = new Department("dept 1");
        department.setTitles(titles);
        DepartmentDTO departmentDTO = new DepartmentDTO("dept 1");
        Mockito.when(departmentRepository.findByName("dept 1")).thenReturn(Optional.of(department));
        Mockito.when(departmentMapper.departmentToDepartmentDTO(department)).thenReturn(departmentDTO);
        Mockito.when(departmentMapper.departmentDTOToDepartment(departmentDTO)).thenReturn(department);
        List<Title> result = departmentService.getAllTitles(departmentDTO);
        assertEquals(2, result.size());
        assertEquals("title 1", result.get(0).getName());
        assertEquals("title 2", result.get(1).getName());
    }
}
