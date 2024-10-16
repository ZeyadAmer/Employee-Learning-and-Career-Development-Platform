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
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@ExtendWith(MockitoExtension.class)
public class CareerPackageTemplateControllerTest {

    @Mock
    private CareerPackageTemplateService careerPackageTemplateService;
    @Mock
    private HttpServletRequest request;
    @Mock
    private JwtUtil jwtUtil;
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
                .createCareerPackageTemplate(Mockito.any(CareerPackageTemplateDTO.class));
    }



}
