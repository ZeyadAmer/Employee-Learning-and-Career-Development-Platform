package org.example.classes;

import org.example.controller.CareerPackageTemplateController;
import org.example.exception.ExistsException;
import org.example.mapper.CareerPackageTemplateDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CareerPackageTemplateIntegrationTest {

    @Autowired
    private CareerPackageTemplateController careerPackageTemplateController;

    @Test
    public void testCreateCareerPackageTemplate_Success() throws IOException {
        MultipartFile file = Mockito.mock(MultipartFile.class);
        careerPackageTemplateController.createCareerPackageTemplate(file,"test");
        CareerPackageTemplateDTO result = careerPackageTemplateController.getCareerPackageTemplate("test").getBody();
        assertEquals("test",result.getTitle());
        careerPackageTemplateController.deleteCareerPackageTemplate("test");
    }

    @Test
    public void testCreateCareerPackageTemplate_AlreadyExists() throws IOException {
        MultipartFile file = Mockito.mock(MultipartFile.class);
        careerPackageTemplateController.createCareerPackageTemplate(file,"test");
        CareerPackageTemplateDTO result = careerPackageTemplateController.getCareerPackageTemplate("test").getBody();
        assertThrows(ExistsException.class, () -> careerPackageTemplateController.createCareerPackageTemplate(file,"test"));
    }

    @Test
    public void testGetCareerPackageTemplate_NotFound(){
        assertThrows(ExistsException.class, () -> careerPackageTemplateController.getCareerPackageTemplate("test1"));
    }

    @Test
    public void testUpdateCareerPackageTemplate(){
        CareerPackageTemplateDTO careerPackageTemplateDTO = new CareerPackageTemplateDTO("test","test1".getBytes());
        careerPackageTemplateController.updateCareerPackageTemplate(careerPackageTemplateDTO);
        CareerPackageTemplateDTO result = careerPackageTemplateController.getCareerPackageTemplate("test").getBody();
    }

    @Test
    public void testDeleteCareerPackageTemplate_Success(){
        careerPackageTemplateController.deleteCareerPackageTemplate("test");
    }


    @Test
    public void testDeleteCareerPackageTemplate_NotFound(){
        assertThrows(ExistsException.class, () -> careerPackageTemplateController.deleteCareerPackageTemplate("test"));
    }

    @Test
    public void testGetAllCareerPackageTemplates(){
        List<CareerPackageTemplateDTO> result = careerPackageTemplateController.getAllCareerPackageTemplates().getBody();
        assertEquals(0,result.size());
    }
}
