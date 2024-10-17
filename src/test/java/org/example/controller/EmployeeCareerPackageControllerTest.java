package org.example.controller;

import org.example.mapper.CareerPackageTemplateDTO;
import org.example.mapper.CommentDTO;
import org.example.mapper.EmployeeCareerPackageDTO;
import org.example.service.EmployeeCareerPackageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class EmployeeCareerPackageControllerTest {

    @Mock
    private EmployeeCareerPackageService employeeCareerPackageService;
    @InjectMocks
    private EmployeeCareerPackageController employeeCareerPackageController;

    @Test
    public void testCreateEmployeeCareerPackage() throws IOException {
        MultipartFile mockFile = Mockito.mock(MultipartFile.class);
        byte[] fileContent = {1, 2, 3};
        Mockito.when(mockFile.getBytes()).thenReturn(fileContent);

        CareerPackageTemplateDTO templateDTO = new CareerPackageTemplateDTO();
        Date date = new Date();

        Mockito.doNothing().when(employeeCareerPackageService)
                .createEmployeeCareerPackage(Mockito.any(EmployeeCareerPackageDTO.class));

        ResponseEntity<String> response = employeeCareerPackageController.createEmployeeCareerPackage(
                1, mockFile, "Career Package", date, "");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("{\"message\": \"Employee Career Package created.\"}", response.getBody());

        Mockito.verify(employeeCareerPackageService, Mockito.times(1))
                .createEmployeeCareerPackage(Mockito.any(EmployeeCareerPackageDTO.class));
    }

    @Test
    public void testDeleteEmployeeCareerPackage(){
        Mockito.doNothing().when(employeeCareerPackageService).deleteEmployeeCareerPackage(1);
        ResponseEntity<String> response = employeeCareerPackageController.deleteEmployeeCareerPackage(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("{\"message\": \"Employee Career Package deleted.\"}", response.getBody());

        Mockito.verify(employeeCareerPackageService, Mockito.times(1))
                .deleteEmployeeCareerPackage(1);
    }

    @Test
    public void testUpdateEmployeeCareerPackage() throws IOException {
        MultipartFile mockFile = Mockito.mock(MultipartFile.class);
        byte[] mockBytes = new byte[]{1, 2, 3};
        Mockito.when(mockFile.getBytes()).thenReturn(mockBytes);

        Mockito.doNothing().when(employeeCareerPackageService).updateEmployeeCareerPackage(1, mockBytes);

        ResponseEntity<String> response = employeeCareerPackageController.updateEmployeeCareerPackage(1, mockFile);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Employee Career Package updated.", response.getBody());

        Mockito.verify(employeeCareerPackageService, Mockito.times(1))
                .updateEmployeeCareerPackage(1, mockBytes);
    }

    @Test
    public void testGetEmployeeCareerPackage(){
        EmployeeCareerPackageDTO mockCareerPackage = new EmployeeCareerPackageDTO(1, new byte[]{}, "Career Package", new Date(), new CareerPackageTemplateDTO());

        Mockito.when(employeeCareerPackageService.getEmployeeCareerPackage(1)).thenReturn(mockCareerPackage);

        ResponseEntity<EmployeeCareerPackageDTO> response = employeeCareerPackageController.getEmployeeCareerPackage(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockCareerPackage, response.getBody());

        Mockito.verify(employeeCareerPackageService, Mockito.times(1))
                .getEmployeeCareerPackage(1);
    }

    @Test
    public void testGetEmployeeCareerPackages(){
        List<EmployeeCareerPackageDTO> mockCareerPackages = Arrays.asList(
                new EmployeeCareerPackageDTO(1, new byte[]{}, "Career Package 1", new Date(), new CareerPackageTemplateDTO()),
                new EmployeeCareerPackageDTO(1, new byte[]{}, "Career Package 2", new Date(), new CareerPackageTemplateDTO())
        );

        Mockito.when(employeeCareerPackageService.getEmployeeCareerPackages(1)).thenReturn(mockCareerPackages);

        ResponseEntity<List<EmployeeCareerPackageDTO>> response = employeeCareerPackageController.getEmployeeCareerPackages(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockCareerPackages, response.getBody());

        Mockito.verify(employeeCareerPackageService, Mockito.times(1))
                .getEmployeeCareerPackages(1);
    }

    @Test
    public void testGetAllEmployeeCareerPackages(){
        List<EmployeeCareerPackageDTO> mockCareerPackages = Arrays.asList(
                new EmployeeCareerPackageDTO(1, new byte[]{}, "Career Package 1", new Date(), new CareerPackageTemplateDTO()),
                new EmployeeCareerPackageDTO(2, new byte[]{}, "Career Package 2", new Date(), new CareerPackageTemplateDTO())
        );

        Mockito.when(employeeCareerPackageService.getAllEmployeeCareerPackages()).thenReturn(mockCareerPackages);

        ResponseEntity<List<EmployeeCareerPackageDTO>> response = employeeCareerPackageController.getAllEmployeeCareerPackages();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockCareerPackages, response.getBody());

        Mockito.verify(employeeCareerPackageService, Mockito.times(1))
                .getAllEmployeeCareerPackages();
    }


}
