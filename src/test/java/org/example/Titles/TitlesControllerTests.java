package org.example.Titles;

import org.example.Controllers.TitleController;
import org.example.Mappers.TitleDTO;
import org.example.Mappers.DepartmentDTO;
import org.example.Services.TitleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class TitlesControllerTests {

    @InjectMocks
    private TitleController titleController;

    @Mock
    private TitleService titleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetTitles() {

        DepartmentDTO departmentDTO = new DepartmentDTO("Engineering");
        TitleDTO titleDTO = new TitleDTO();
        titleDTO.setName("Software Engineer");
        titleDTO.setDepartment(departmentDTO);
        when(titleService.getAllTitles()).thenReturn(Collections.singletonList(titleDTO));
        ResponseEntity<List<TitleDTO>> response = titleController.getTitles();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertEquals("Software Engineer", response.getBody().get(0).getName());
        assertEquals("Engineering", response.getBody().get(0).getDepartment().getName()); // Check the department name
        verify(titleService, times(1)).getAllTitles();
    }

    @Test
    public void testCreateTitle() {
        TitleDTO titleDTO = new TitleDTO();
        titleDTO.setName("Product Manager");
        DepartmentDTO departmentDTO = new DepartmentDTO("Product");
        titleDTO.setDepartment(departmentDTO);
        doNothing().when(titleService).createTitle(any(TitleDTO.class));
        ResponseEntity<String> response = titleController.createTitle(titleDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Title created", response.getBody());
        verify(titleService, times(1)).createTitle(titleDTO);
    }

    @Test
    public void testDeleteTitle() {
        String titleName = "Product Manager";

        doNothing().when(titleService).deleteTitle(titleName);
        ResponseEntity<String> response = titleController.deleteTitle(titleName);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Title deleted", response.getBody());
        verify(titleService, times(1)).deleteTitle(titleName);
    }

    @Test
    public void testUpdateTitleName() {
        String oldName = "Old Title Name";
        String newName = "New Title Name";
        doNothing().when(titleService).updateTitleName(oldName, newName);
        ResponseEntity<String> response = titleController.updateTitleName(newName, oldName);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Title updated", response.getBody());
        verify(titleService, times(1)).updateTitleName(oldName, newName);
    }

    @Test
    public void testUpdateTitleDepartment() {
        String titleName = "Software Engineer";
        String newDepartment = "Development";
        doNothing().when(titleService).updateTitleDepartment(titleName, newDepartment);
        ResponseEntity<String> response = titleController.updateTitleDepartment(newDepartment, titleName);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Title department updated", response.getBody());
        verify(titleService, times(1)).updateTitleDepartment(titleName, newDepartment);
    }
}
