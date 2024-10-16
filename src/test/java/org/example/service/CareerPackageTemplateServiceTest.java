package org.example.service;

import org.example.classes.CareerPackageTemplate;
import org.example.exception.ExistsException;
import org.example.mapper.CareerPackageTemplateDTO;
import org.example.mapper.CareerPackageTemplateMapper;
import org.example.repository.CareerPackageTemplateRepository;
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
public class CareerPackageTemplateServiceTest {

    @Mock
    CareerPackageTemplateRepository careerPackageTemplateRepository;
    @Mock
    CareerPackageTemplateMapper careerPackageTemplateMapper;
    @InjectMocks
    CareerPackageTemplateService careerPackageTemplateService;

    @Test
    public void testCreateCareerPackageTemplate_Success(){
        CareerPackageTemplateDTO careerPackageTemplateDTO = new CareerPackageTemplateDTO();
        careerPackageTemplateDTO.setTitle("Test");
        Mockito.when(careerPackageTemplateRepository.findByTitle("Test")).thenReturn(Optional.empty());
        careerPackageTemplateService.createCareerPackageTemplate(careerPackageTemplateDTO);
        Mockito.verify(careerPackageTemplateRepository, Mockito.times(1)).findByTitle("Test");
    }

    @Test
    public void testCreateCareerPackageTemplate_AlreadyExists(){
        CareerPackageTemplate careerPackageTemplate = new CareerPackageTemplate();
        careerPackageTemplate.setTitle("Test");
        CareerPackageTemplateDTO careerPackageTemplateDTO = new CareerPackageTemplateDTO();
        careerPackageTemplateDTO.setTitle("Test");
        Mockito.when(careerPackageTemplateRepository.findByTitle("Test")).thenReturn(Optional.of(careerPackageTemplate));
        assertThrows(ExistsException.class, () -> careerPackageTemplateService.createCareerPackageTemplate(careerPackageTemplateDTO));
    }

    @Test
    public void testDeleteCareerPackage_Success(){
        CareerPackageTemplate careerPackageTemplate = new CareerPackageTemplate();
        careerPackageTemplate.setTitle("Test");
        Mockito.when(careerPackageTemplateRepository.findByTitle("Test")).thenReturn(Optional.of(careerPackageTemplate));
        careerPackageTemplateService.deleteCareerPackageTemplate("Test");
        Mockito.verify(careerPackageTemplateRepository, Mockito.times(1)).findByTitle("Test");
    }

    @Test
    public void testDeleteCareerPackageTemplate_NotFound(){
        Mockito.when(careerPackageTemplateRepository.findByTitle("Test")).thenReturn(Optional.empty());
        assertThrows(ExistsException.class, () -> careerPackageTemplateService.deleteCareerPackageTemplate("Test"));
    }

    @Test
    public void testUpdateCareerPackageTemplate_Success(){
        CareerPackageTemplate careerPackageTemplate = new CareerPackageTemplate();
        careerPackageTemplate.setTitle("Test");
        CareerPackageTemplateDTO careerPackageTemplateDTO = new CareerPackageTemplateDTO();
        careerPackageTemplateDTO.setTitle("Test");
        Mockito.when(careerPackageTemplateRepository.findByTitle("Test")).thenReturn(Optional.of(careerPackageTemplate));
        careerPackageTemplateService.updateCareerPackageTemplate(careerPackageTemplateDTO);
        Mockito.verify(careerPackageTemplateRepository, Mockito.times(1)).findByTitle("Test");
    }

    @Test
    public void testUpdateCareerPackageTemplate_NotFound(){
        CareerPackageTemplateDTO careerPackageTemplateDTO = new CareerPackageTemplateDTO();
        careerPackageTemplateDTO.setTitle("Test");
        Mockito.when(careerPackageTemplateRepository.findByTitle("Test")).thenReturn(Optional.empty());
        assertThrows(ExistsException.class, () -> careerPackageTemplateService.updateCareerPackageTemplate(careerPackageTemplateDTO));
    }

    @Test
    public void testGetCareerPackageTemplate_Success(){
        CareerPackageTemplate careerPackageTemplate = new CareerPackageTemplate();
        careerPackageTemplate.setTitle("Test");
        CareerPackageTemplateDTO careerPackageTemplateDTO = new CareerPackageTemplateDTO();
        careerPackageTemplateDTO.setTitle("Test");
        Mockito.when(careerPackageTemplateRepository.findByTitle("Test")).thenReturn(Optional.of(careerPackageTemplate));
        Mockito.when(careerPackageTemplateMapper.careerPackageTemplateToCareerPackageTemplateDTO(careerPackageTemplate)).thenReturn(careerPackageTemplateDTO);
        CareerPackageTemplateDTO result = careerPackageTemplateService.getCareerPackageTemplate("Test");
        assertEquals("Test",result.getTitle());
    }

    @Test
    public void testGetCareerPackageTemplate_NotFound(){
        Mockito.when(careerPackageTemplateRepository.findByTitle("Test")).thenReturn(Optional.empty());
        assertThrows(ExistsException.class, () -> careerPackageTemplateService.getCareerPackageTemplate("Test"));
    }

    @Test
    public void testGetAllCareerPackageTemplates(){
        List<CareerPackageTemplate> careerPackageTemplates = new ArrayList<>();
        careerPackageTemplates.add(new CareerPackageTemplate());
        List<CareerPackageTemplateDTO> careerPackageTemplateDTOS = new ArrayList<>();
        careerPackageTemplateDTOS.add(new CareerPackageTemplateDTO());
        Mockito.when(careerPackageTemplateRepository.findAll()).thenReturn(careerPackageTemplates);
        Mockito.when(careerPackageTemplateMapper.careerPackageTemplateListToCareerPackageTemplateDTOList(careerPackageTemplates)).thenReturn(careerPackageTemplateDTOS);
        List<CareerPackageTemplateDTO> result = careerPackageTemplateService.getAllCareerPackageTemplates();
        assertEquals(null,result.get(0).getTitle());
    }

    @Test
    public void testDownloadCareerPackageTemplate_Success(){
        CareerPackageTemplate careerPackageTemplate = new CareerPackageTemplate();
        careerPackageTemplate.setTitle("Test");
        Mockito.when(careerPackageTemplateRepository.findByTitle("Test")).thenReturn(Optional.of(careerPackageTemplate));
        byte[] result = careerPackageTemplateService.downloadCareerPackageTemplate("Test");
        assertEquals(null, result);
    }

    @Test
    public void testDownloadCareerPackageTemplate_NotFound(){
        Mockito.when(careerPackageTemplateRepository.findByTitle("Test")).thenReturn(Optional.empty());
        assertThrows(ExistsException.class, () -> careerPackageTemplateService.downloadCareerPackageTemplate("Test"));
    }
}
