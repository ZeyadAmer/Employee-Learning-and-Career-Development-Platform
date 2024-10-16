package org.example.controller;

import org.example.enums.CareerPackageStatus;
import org.example.mapper.SubmittedCareerPackageDTO;
import org.example.service.SubmittedCareerPackageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class SubmittedCareerPackageControllerTest {

    @Mock
    private SubmittedCareerPackageService submittedCareerPackageService;
    @InjectMocks
    private SubmittedCareerPackageController submittedCareerPackageController;

    @Test
    public void testCreateSubmittedCareerPackage(){

        SubmittedCareerPackageDTO submittedCareerPackageDTO = new SubmittedCareerPackageDTO(); // Initialize with necessary properties if any

        Mockito.doNothing().when(submittedCareerPackageService).createSubmittedCareerPackage(submittedCareerPackageDTO);

        ResponseEntity<String> response = submittedCareerPackageController.createSubmittedCareerPackage(submittedCareerPackageDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("{\"message\": \"Submitted Career Package created.\"}", response.getBody());

        Mockito.verify(submittedCareerPackageService, Mockito.times(1)).createSubmittedCareerPackage(submittedCareerPackageDTO);
    }

    @Test
    public void testDeleteSubmittedCareerPackage(){

        Mockito.doNothing().when(submittedCareerPackageService).deleteSubmittedCareerPackage(1);

        ResponseEntity<String> response = submittedCareerPackageController.deleteSubmittedCareerPackage(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Submitted Career Package deleted.", response.getBody());

        Mockito.verify(submittedCareerPackageService, Mockito.times(1)).deleteSubmittedCareerPackage(1);
    }

    @Test
    public void testUpdateSubmittedCareerPackage(){

        CareerPackageStatus newStatus = CareerPackageStatus.APPROVED;

        Mockito.doNothing().when(submittedCareerPackageService).updateSubmittedCareerPackage(1, newStatus);

        ResponseEntity<String> response = submittedCareerPackageController.updateSubmittedCareerPackage(1, newStatus);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("{\"message\": \"Submiited Career Package Status updated.\"}", response.getBody());

        Mockito.verify(submittedCareerPackageService, Mockito.times(1))
                .updateSubmittedCareerPackage(1, newStatus);
    }

    @Test
    public void testGetSubmittedCareerPackage(){

        SubmittedCareerPackageDTO mockCareerPackage = new SubmittedCareerPackageDTO();

        Mockito.when(submittedCareerPackageService.getSubmittedCareerPackage(1)).thenReturn(mockCareerPackage);

        ResponseEntity<SubmittedCareerPackageDTO> response = submittedCareerPackageController.getSubmittedCareerPackage(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockCareerPackage, response.getBody());

        Mockito.verify(submittedCareerPackageService, Mockito.times(1)).getSubmittedCareerPackage(1);
    }

    @Test
    public void testGetAllEmployeeSubmittedCareerPackage(){

        List<SubmittedCareerPackageDTO> mockCareerPackages = Collections.singletonList(new SubmittedCareerPackageDTO());

        Mockito.when(submittedCareerPackageService.getAllEmployeeSubmittedCareerPackage(1)).thenReturn(mockCareerPackages);

        ResponseEntity<List<SubmittedCareerPackageDTO>> response = submittedCareerPackageController.getAllEmployeeSubmittedCareerPackage(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockCareerPackages, response.getBody());

        Mockito.verify(submittedCareerPackageService, Mockito.times(1)).getAllEmployeeSubmittedCareerPackage(1);
    }

    @Test
    public void testGetAllManagerReceivedCareerPackage(){

        List<SubmittedCareerPackageDTO> mockCareerPackages = Collections.singletonList(new SubmittedCareerPackageDTO());

        Mockito.when(submittedCareerPackageService.getAllManagerReceivedCareerPackage(1)).thenReturn(mockCareerPackages);

        ResponseEntity<List<SubmittedCareerPackageDTO>> response = submittedCareerPackageController.getAllManagerReceivedCareerPackage(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockCareerPackages, response.getBody());

        Mockito.verify(submittedCareerPackageService, Mockito.times(1)).getAllManagerReceivedCareerPackage(1);
    }

    @Test
    public void testGetAllSubmittedCareerPackages(){

        List<SubmittedCareerPackageDTO> mockCareerPackages = Collections.singletonList(new SubmittedCareerPackageDTO());

        Mockito.when(submittedCareerPackageService.getAllSubmittedCareerPackages()).thenReturn(mockCareerPackages);

        ResponseEntity<List<SubmittedCareerPackageDTO>> response = submittedCareerPackageController.getAllSubmittedCareerPackages();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockCareerPackages, response.getBody());

        Mockito.verify(submittedCareerPackageService, Mockito.times(1)).getAllSubmittedCareerPackages();
    }

    @Test
    public void testDownloadSubmittedCareerPackage_Success(){

        String careerPackageName = "CareerPackage";
        byte[] mockFileData = new byte[]{1, 2, 3};

        Mockito.when(submittedCareerPackageService.downloadSubmittedCareerPackage(1)).thenReturn(mockFileData);

        ResponseEntity<byte[]> response = submittedCareerPackageController.downloadSubmittedCareerPackage(1, careerPackageName);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockFileData, response.getBody());

        assertEquals("attachment; filename=\"CareerPackage\"", response.getHeaders().getFirst(HttpHeaders.CONTENT_DISPOSITION));

        Mockito.verify(submittedCareerPackageService, Mockito.times(1)).downloadSubmittedCareerPackage(1);
    }

    @Test
    public void testDownloadSubmittedCareerPackage_NotFound(){

        String careerPackageName = "CareerPackage";

        Mockito.when(submittedCareerPackageService.downloadSubmittedCareerPackage(1)).thenReturn(null);

        ResponseEntity<byte[]> response = submittedCareerPackageController.downloadSubmittedCareerPackage(1, careerPackageName);

        assertEquals(404, response.getStatusCodeValue());

        Mockito.verify(submittedCareerPackageService, Mockito.times(1)).downloadSubmittedCareerPackage(1);
    }
}



