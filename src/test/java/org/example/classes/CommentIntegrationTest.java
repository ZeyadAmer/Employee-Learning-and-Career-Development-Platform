//package org.example.classes;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import org.example.controller.CareerPackageTemplateController;
//import org.example.controller.CommentController;
//import org.example.controller.EmployeeCareerPackageController;
//import org.example.controller.SubmittedCareerPackageController;
//import org.example.enums.CareerPackageStatus;
//import org.example.exception.ExistsException;
//import org.example.mapper.CareerPackageTemplateDTO;
//import org.example.mapper.CommentDTO;
//import org.example.mapper.EmployeeCareerPackageDTO;
//import org.example.mapper.SubmittedCareerPackageDTO;
//import org.example.repository.CommentRepository;
//import org.junit.jupiter.api.*;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.web.multipart.MultipartFile;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.io.IOException;
//import java.util.Date;
//
//@SpringBootTest
//public class CommentIntegrationTest {
//    @Autowired
//    private CommentRepository commentRepository;
//    @Autowired
//    private CommentController commentController;
//    @Autowired
//    private CareerPackageTemplateController careerPackageTemplateController;
//    @Autowired
//    private SubmittedCareerPackageController submittedCareerPackageController;
//    @Autowired
//    private EmployeeCareerPackageController employeeCareerPackageController;
//
//    private CareerPackageTemplateDTO careerPackageTemplateDTO;
//    private EmployeeCareerPackageDTO employeeCareerPackageDTO;
//    private SubmittedCareerPackageDTO submittedCareerPackageDTO;
//
////    @BeforeEach
////    public void setUp() throws IOException {
////        MultipartFile file = Mockito.mock(MultipartFile.class);
////        careerPackageTemplateController.createCareerPackageTemplate(file,"Test");
////        careerPackageTemplateDTO = careerPackageTemplateController.getCareerPackageTemplate("Test").getBody();
////        employeeCareerPackageController.createEmployeeCareerPackage(1,file,"Test",new Date(),"Test");
////        employeeCareerPackageDTO = employeeCareerPackageController.getEmployeeCareerPackage(1).getBody();
////        submittedCareerPackageDTO = new SubmittedCareerPackageDTO(1,employeeCareerPackageDTO,1, CareerPackageStatus.valueOf("PENDING"));
////        submittedCareerPackageController.createSubmittedCareerPackage(submittedCareerPackageDTO);
////        submittedCareerPackageDTO = submittedCareerPackageController.getSubmittedCareerPackage(1).getBody();
////    }
//
////    @AfterEach
////    public void after(){
////        submittedCareerPackageController.deleteSubmittedCareerPackage(1);
////        employeeCareerPackageController.deleteEmployeeCareerPackage(1);
////        careerPackageTemplateController.deleteCareerPackageTemplate("Test");
////    }
//
////    @Test
////    public void testCreateComment_Success() throws IOException {
////        MultipartFile file = Mockito.mock(MultipartFile.class);
////        careerPackageTemplateController.createCareerPackageTemplate(file,"Test");
////        careerPackageTemplateDTO = careerPackageTemplateController.getCareerPackageTemplate("Test").getBody();
////        employeeCareerPackageController.createEmployeeCareerPackage(1,file,"Test",new Date(),"Test");
////        employeeCareerPackageDTO = employeeCareerPackageController.getEmployeeCareerPackage(1).getBody();
////        submittedCareerPackageDTO = new SubmittedCareerPackageDTO(1,employeeCareerPackageDTO,1, CareerPackageStatus.valueOf("PENDING"));
////        submittedCareerPackageController.createSubmittedCareerPackage(submittedCareerPackageDTO);
////        submittedCareerPackageDTO = submittedCareerPackageController.getSubmittedCareerPackage(1).getBody();
////
////        CommentDTO commentDTO = new CommentDTO("Test",submittedCareerPackageDTO);
////        commentDTO.setId(1);
////        commentController.createComment(commentDTO);
////        CommentDTO result = commentController.getComment(1).getBody();
////        assertEquals(result.getId(), commentDTO.getId());
////    }
//
////    @Test
////    public void testCreateComment_AlreadyExists(){
////        CommentDTO commentDTO = new CommentDTO("Test",submittedCareerPackageDTO);
////        commentDTO.setId(1);
////        commentController.createComment(commentDTO);
////        assertThrows(ExistsException.class, () -> commentController.createComment(commentDTO));
////    }
//
//
//
//
//}
