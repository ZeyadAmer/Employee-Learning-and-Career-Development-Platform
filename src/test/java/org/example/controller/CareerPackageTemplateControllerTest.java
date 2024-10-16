package org.example.controller;

import org.example.configuration.JwtUtil;
import org.example.mapper.CareerPackageTemplateDTO;
import org.example.service.CareerPackageTemplateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;


@ExtendWith(MockitoExtension.class)
public class CareerPackageTemplateControllerTest {

    @Mock
    private CareerPackageTemplateService careerPackageTemplateService;
    @InjectMocks
    CareerPackageTemplateController careerPackageTemplateController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateCareerPackageTemplate() throws IOException {
        MultipartFile file = Mockito.mock(MultipartFile.class);
        Mockito.when(file.getBytes()).thenReturn("mock data".getBytes());

        CareerPackageTemplateDTO careerPackageTemplateDTO = new CareerPackageTemplateDTO();
        careerPackageTemplateDTO.setCareerPackageTemplate(null);
        careerPackageTemplateDTO.setTitle("");

        ResponseEntity<String> response = careerPackageTemplateController.createCareerPackageTemplate(file, "");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("{\"message\": \"Career Package Template created.\"}", response.getBody());

        Mockito.verify(careerPackageTemplateService, Mockito.times(1))
                .createCareerPackageTemplate(Mockito.any(CareerPackageTemplateDTO.class));
    }

    @Test
    public void testDeleteCareerPackageTemplate(){
        ResponseEntity<String> response = careerPackageTemplateController.deleteCareerPackageTemplate("");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("{\"message\": \"Career Package Template deleted.\"}", response.getBody());

        Mockito.verify(careerPackageTemplateService, Mockito.times(1))
                .deleteCareerPackageTemplate("");
    }

    @Test
    public void testUpdateCareerPackageTemplate(){
        CareerPackageTemplateDTO careerPackageTemplateDTO = new CareerPackageTemplateDTO();
        careerPackageTemplateDTO.setTitle("Test");
        ResponseEntity<String> response = careerPackageTemplateController.updateCareerPackageTemplate(careerPackageTemplateDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("{\"message\": \"Career Package Template updated.\"}", response.getBody());

        Mockito.verify(careerPackageTemplateService, Mockito.times(1))
                .updateCareerPackageTemplate(careerPackageTemplateDTO);
    }

    @Test
    public void testGetCareerPackageTemplate(){
        ResponseEntity<CareerPackageTemplateDTO> response = careerPackageTemplateController.getCareerPackageTemplate("");

        assertEquals(200, response.getStatusCodeValue());

        Mockito.verify(careerPackageTemplateService, Mockito.times(1))
                .getCareerPackageTemplate("");
    }

    @Test
    public void testDownloadCareerPackageTemplate() {
        byte[] mockFile = "".getBytes();  // Mock byte array

        Mockito.when(careerPackageTemplateService.downloadCareerPackageTemplate(""))
                .thenReturn(mockFile);

        ResponseEntity<byte[]> response = careerPackageTemplateController.downloadCareerPackageTemplate("");

        assertEquals(200, response.getStatusCodeValue());

        Mockito.verify(careerPackageTemplateService, Mockito.times(1))
                .downloadCareerPackageTemplate("");
    }
}
