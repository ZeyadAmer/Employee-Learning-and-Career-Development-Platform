package org.example.classes;

import org.example.controller.CareerPackageTemplateController;
import org.example.controller.EmployeeCareerPackageController;
import org.example.exception.ExistsException;
import org.example.mapper.EmployeeCareerPackageDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.Date;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;


@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class EmployeeCareerPackageIntegrationTest {

    @Autowired
    private EmployeeCareerPackageController employeeCareerPackageController;
    @Autowired
    private CareerPackageTemplateController careerPackageTemplateController;

    @Test
    @Order(1)
    public void testCreateEmployeeCareerPackage() throws IOException {
        MultipartFile file = Mockito.mock(MultipartFile.class);
        careerPackageTemplateController.createCareerPackageTemplate(file,"test1");
        employeeCareerPackageController.createEmployeeCareerPackage(1,file,"test1",new Date(),"test1");
        EmployeeCareerPackageDTO employeeCareerPackageDTO = employeeCareerPackageController.getEmployeeCareerPackage(1).getBody();
        assertEquals(1,employeeCareerPackageDTO.getEmployeeId());
        employeeCareerPackageController.deleteEmployeeCareerPackage(1);
        careerPackageTemplateController.deleteCareerPackageTemplate("test1");
    }

    @Test
    @Order(2)
    public void testDeleteEmployeeCareerPackage() throws IOException {
        MultipartFile file = Mockito.mock(MultipartFile.class);
        careerPackageTemplateController.createCareerPackageTemplate(file,"test2");
        employeeCareerPackageController.createEmployeeCareerPackage(1,file,"test2",new Date(),"test2");
        employeeCareerPackageController.deleteEmployeeCareerPackage(2);
        careerPackageTemplateController.deleteCareerPackageTemplate("test2");
        assertThrows(ExistsException.class, () -> employeeCareerPackageController.deleteEmployeeCareerPackage(2));
    }

    @Test
    @Order(3)
    public void testUpdateEmployeeCareerPackage() throws IOException {
        MultipartFile file = Mockito.mock(MultipartFile.class);
        careerPackageTemplateController.createCareerPackageTemplate(file,"test3");
        employeeCareerPackageController.createEmployeeCareerPackage(1,file,"test3",new Date(),"test3");
        MultipartFile file2 = Mockito.mock(MultipartFile.class);
        employeeCareerPackageController.updateEmployeeCareerPackage(3,file2);
        EmployeeCareerPackageDTO employeeCareerPackageDTO = employeeCareerPackageController.getEmployeeCareerPackage(3).getBody();
        assertEquals(3, employeeCareerPackageDTO.getId());
    }

    @Test
    @Order(4)
    public void testGetEmployeeCareerPackage_Success(){
        assertEquals(3, employeeCareerPackageController.getEmployeeCareerPackage(3).getBody().getId());
    }

    @Test
    @Order(5)
    public void testGetEmployeeCareerPackage_NotFound(){
        assertThrows(ExistsException.class, () -> employeeCareerPackageController.getEmployeeCareerPackage(2));
    }

    @Test
    @Order(6)
    public void testGetEmployeeCareerPackages_Success(){
        assertEquals(1,employeeCareerPackageController.getEmployeeCareerPackages(1).getBody().get(0).getEmployeeId());
    }

    @Test
    @Order(7)
    public void testGetEmployeeCareerPackages_NotFound(){
        assertEquals(0,employeeCareerPackageController.getEmployeeCareerPackages(2).getBody().size());
    }

    @Test
    @Order(8)
    public void testGetAllEmployeeCareerPackages(){
        assertEquals(1,employeeCareerPackageController.getAllEmployeeCareerPackages().getBody().size());
    }
}
