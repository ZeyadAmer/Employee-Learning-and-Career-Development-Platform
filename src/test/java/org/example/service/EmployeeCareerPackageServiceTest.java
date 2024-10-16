package org.example.service;

import org.example.classes.CareerPackageTemplate;
import org.example.classes.EmployeeCareerPackage;
import org.example.exception.ExistsException;
import org.example.mapper.CareerPackageTemplateDTO;
import org.example.mapper.EmployeeCareerPackageDTO;
import org.example.mapper.EmployeeCareerPackageMapper;
import org.example.repository.CareerPackageTemplateRepository;
import org.example.repository.EmployeeCareerPackageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EmployeeCareerPackageServiceTest {

    @Mock
    EmployeeCareerPackageRepository employeeCareerPackageRepository;
    @Mock
    EmployeeCareerPackageMapper employeeCareerPackageMapper;
    @Mock
    CareerPackageTemplateRepository careerPackageTemplateRepository;
    @InjectMocks
    EmployeeCareerPackageService employeeCareerPackageService;


    @Test
    public void testCreateEmployeeCareerPackage_Success(){
        EmployeeCareerPackage employeeCareerPackage = new EmployeeCareerPackage();
        employeeCareerPackage.setId(1);
        CareerPackageTemplate careerPackageTemplate = new CareerPackageTemplate();
        careerPackageTemplate.setTitle("Test");
        CareerPackageTemplateDTO careerPackageTemplateDTO = new CareerPackageTemplateDTO();
        careerPackageTemplateDTO.setTitle("Test");
        EmployeeCareerPackageDTO employeeCareerPackageDTO = new EmployeeCareerPackageDTO();
        employeeCareerPackageDTO.setId(1);
        employeeCareerPackageDTO.setCareerPackageTemplateDTO(careerPackageTemplateDTO);
        employeeCareerPackage.setCareerPackageTemplate(careerPackageTemplate);
        Mockito.when(employeeCareerPackageRepository.findById(1)).thenReturn(Optional.empty());
        Mockito.when(careerPackageTemplateRepository.findByTitle("Test")).thenReturn(Optional.of(careerPackageTemplate));
        Mockito.when(employeeCareerPackageMapper.employeeCareerPackageDTOToEmployeeCareerPackage(employeeCareerPackageDTO)).thenReturn(employeeCareerPackage);
        employeeCareerPackageService.createEmployeeCareerPackage(employeeCareerPackageDTO);
        Mockito.verify(employeeCareerPackageRepository, Mockito.times(1)).findById(1);
    }

    @Test
    public void testCreateEmployeeCareerPackage_AlreadyExists(){
        EmployeeCareerPackageDTO employeeCareerPackageDTO = new EmployeeCareerPackageDTO();
        employeeCareerPackageDTO.setId(1);
        EmployeeCareerPackage employeeCareerPackage = new EmployeeCareerPackage();
        employeeCareerPackage.setId(1);
        Mockito.when(employeeCareerPackageRepository.findById(1)).thenReturn(Optional.of(employeeCareerPackage));
        assertThrows(ExistsException.class, () -> employeeCareerPackageService.createEmployeeCareerPackage(employeeCareerPackageDTO));
    }

    @Test
    public void deleteEmployeeCareerPackage_Success(){
        EmployeeCareerPackage employeeCareerPackage = new EmployeeCareerPackage();
        employeeCareerPackage.setId(1);
        Mockito.when(employeeCareerPackageRepository.findById(1)).thenReturn(Optional.of(employeeCareerPackage));
        employeeCareerPackageService.deleteEmployeeCareerPackage(1);
        Mockito.verify(employeeCareerPackageRepository, Mockito.times(1)).findById(1);
    }

    @Test
    public void deleteEmployeeCareerPackage_NotFound(){
        Mockito.when(employeeCareerPackageRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(ExistsException.class, () -> employeeCareerPackageService.deleteEmployeeCareerPackage(1));
    }

    @Test
    public void testUpdateEmployeeCareerPackage_Success() {
        int packageId = 1;
        byte[] newCareerPackage = "test".getBytes();

        EmployeeCareerPackage existingPackage = new EmployeeCareerPackage();
        existingPackage.setId(packageId);

        CareerPackageTemplate existingTemplate = new CareerPackageTemplate();
        existingTemplate.setTitle("Test");
        existingPackage.setCareerPackageTemplate(existingTemplate);

        CareerPackageTemplate updatedTemplate = new CareerPackageTemplate();
        updatedTemplate.setTitle("Test");

        Mockito.when(employeeCareerPackageRepository.findById(packageId))
                .thenReturn(Optional.of(existingPackage));
        Mockito.when(careerPackageTemplateRepository.findByTitle("Test"))
                .thenReturn(Optional.of(updatedTemplate));

        employeeCareerPackageService.updateEmployeeCareerPackage(packageId, newCareerPackage);

        Mockito.verify(employeeCareerPackageRepository, Mockito.times(1)).save(existingPackage);
        assertArrayEquals(newCareerPackage, existingPackage.getCareerPackage());
        assertEquals(updatedTemplate, existingPackage.getCareerPackageTemplate());
    }

    @Test
    public void testUpdateEmployeeCareerPackage_EmployeeCareerPackageNotFound(){
        Mockito.when(employeeCareerPackageRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(ExistsException.class, () -> employeeCareerPackageService.updateEmployeeCareerPackage(1,"test".getBytes()));
    }

    @Test
    public void testUpdateEmployeeCareerPackage_CareerPackageTemplateNotFound(){
        EmployeeCareerPackage employeeCareerPackage = new EmployeeCareerPackage();
        employeeCareerPackage.setId(1);
        CareerPackageTemplate careerPackageTemplate = new CareerPackageTemplate();
        careerPackageTemplate.setTitle("Test");
        employeeCareerPackage.setCareerPackageTemplate(careerPackageTemplate);
        Mockito.when(employeeCareerPackageRepository.findById(1)).thenReturn(Optional.of(employeeCareerPackage));
        Mockito.when(careerPackageTemplateRepository.findByTitle("Test")).thenReturn(Optional.empty());
        assertThrows(ExistsException.class, () -> employeeCareerPackageService.updateEmployeeCareerPackage(1,"test".getBytes()));
    }

    @Test
    public void testGetEmployeeCareerPackage_Success(){
        EmployeeCareerPackage employeeCareerPackage = new EmployeeCareerPackage();
        employeeCareerPackage.setId(1);
        EmployeeCareerPackageDTO employeeCareerPackageDTO = new EmployeeCareerPackageDTO();
        employeeCareerPackageDTO.setId(1);
        Mockito.when(employeeCareerPackageRepository.findById(1)).thenReturn(Optional.of(employeeCareerPackage));
        Mockito.when(employeeCareerPackageMapper.employeeCareerPackageToEmployeeCareerPackageDTO(employeeCareerPackage)).thenReturn(employeeCareerPackageDTO);
        EmployeeCareerPackageDTO result = employeeCareerPackageService.getEmployeeCareerPackage(1);
        assertEquals(1, result.getId());
    }

    @Test
    public void testGetEmployeeCareerPackage_NotFound(){
        Mockito.when(employeeCareerPackageRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(ExistsException.class, () -> employeeCareerPackageService.getEmployeeCareerPackage(1));
    }

    @Test
    public void testGetEmployeeCareerPackages(){
        List<EmployeeCareerPackage> employeeCareerPackages = new ArrayList<>();
        EmployeeCareerPackage employeeCareerPackage = new EmployeeCareerPackage();
        employeeCareerPackage.setEmployeeId(1);
        employeeCareerPackages.add(employeeCareerPackage);
        List<EmployeeCareerPackageDTO> employeeCareerPackageDTOS = new ArrayList<>();
        EmployeeCareerPackageDTO employeeCareerPackageDTO = new EmployeeCareerPackageDTO();
        employeeCareerPackageDTO.setEmployeeId(1);
        employeeCareerPackageDTOS.add(employeeCareerPackageDTO);
        Mockito.when(employeeCareerPackageRepository.findByEmployeeId(1)).thenReturn(Optional.of(employeeCareerPackages));
        Mockito.when(employeeCareerPackageMapper.employeeCareerPackageListToEmployeeCareerPackageDTOList(employeeCareerPackages)).thenReturn(employeeCareerPackageDTOS);
        List<EmployeeCareerPackageDTO> result = employeeCareerPackageService.getEmployeeCareerPackages(1);
        assertEquals(1, result.get(0).getEmployeeId());
    }

    @Test
    public void testGetAllEmployeeCareerPackages(){
        List<EmployeeCareerPackage> employeeCareerPackages = new ArrayList<>();
        List<EmployeeCareerPackageDTO> employeeCareerPackageDTOS = new ArrayList<>();
        Mockito.when(employeeCareerPackageRepository.findAll()).thenReturn(employeeCareerPackages);
        Mockito.when(employeeCareerPackageMapper.employeeCareerPackageListToEmployeeCareerPackageDTOList(employeeCareerPackages)).thenReturn(employeeCareerPackageDTOS);
        List<EmployeeCareerPackageDTO> result = employeeCareerPackageService.getAllEmployeeCareerPackages();
        assertEquals(0, result.size());
    }
}
