//package org.example.classes;
//
//import org.example.controller.CareerPackageTemplateController;
//import org.example.exception.ExistsException;
//import org.example.mapper.CareerPackageTemplateDTO;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//public class CareerPackageTemplateIntegrationTest {
//
//    @Autowired
//    private CareerPackageTemplateController careerPackageTemplateController;
//
//    @Test
//    public void testCreateCareerPackageTemplate_Success() throws IOException {
//        MultipartFile file = Mockito.mock(MultipartFile.class);
//        careerPackageTemplateController.createCareerPackageTemplate(file,"");
//        CareerPackageTemplateDTO result = careerPackageTemplateController.getCareerPackageTemplate("").getBody();
//        assertEquals(1,result.getId());
//        //careerPackageTemplateController.deleteCareerPackageTemplate("");
//    }
//
//    @Test
//    public void testCreateCareerPackageTemplate_AlreadyExists() throws IOException {
//        MultipartFile file = Mockito.mock(MultipartFile.class);
//        assertThrows(ExistsException.class, () -> careerPackageTemplateController.createCareerPackageTemplate(file,""));
//    }
//
//    @Test
//    public void testDeleteCareerPackageTemplate_Success(){
//        careerPackageTemplateController.deleteCareerPackageTemplate("");
//    }
//
//    @Test
//    public void testDeleteCareerPackageTemplate_NotFound(){
//        assertThrows(ExistsException.class, () -> careerPackageTemplateController.downloadCareerPackageTemplate(""));
//    }
//
//
//
//
//}
