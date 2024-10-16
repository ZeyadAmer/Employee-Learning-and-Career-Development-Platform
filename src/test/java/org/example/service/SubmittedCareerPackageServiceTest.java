package org.example.service;

import org.example.classes.CareerPackageTemplate;
import org.example.classes.EmployeeCareerPackage;
import org.example.classes.SubmittedCareerPackage;
import org.example.enums.CareerPackageStatus;
import org.example.exception.ExistsException;
import org.example.mapper.EmployeeCareerPackageDTO;
import org.example.mapper.SubmittedCareerPackageDTO;
import org.example.mapper.SubmittedCareerPackageMapper;
import org.example.repository.EmployeeCareerPackageRepository;
import org.example.repository.SubmittedCareerPackageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class SubmittedCareerPackageServiceTest {

    @Mock
    SubmittedCareerPackageRepository submittedCareerPackageRepository;
    @Mock
    SubmittedCareerPackageMapper submittedCareerPackageMapper;
    @Mock
    EmployeeCareerPackageRepository employeeCareerPackageRepository;
    @InjectMocks
    SubmittedCareerPackageService submittedCareerPackageService;


    @Test
    public void testCreateSubmittedCareerPackage_Success(){
        EmployeeCareerPackage employeeCareerPackage = new EmployeeCareerPackage();
        employeeCareerPackage.setId(1);
        SubmittedCareerPackage submittedCareerPackage = new SubmittedCareerPackage();
        submittedCareerPackage.setId(1);
        SubmittedCareerPackageDTO submittedCareerPackageDTO = new SubmittedCareerPackageDTO();
        submittedCareerPackageDTO.setId(1);
        EmployeeCareerPackageDTO employeeCareerPackageDTO = new EmployeeCareerPackageDTO();
        employeeCareerPackageDTO.setId(1);
        submittedCareerPackageDTO.setEmployeeCareerPackage(employeeCareerPackageDTO);
        submittedCareerPackage.setEmployeeCareerPackage(employeeCareerPackage);
        Mockito.when(submittedCareerPackageRepository.findById(1)).thenReturn(Optional.empty());
        Mockito.when(employeeCareerPackageRepository.findById(1)).thenReturn(Optional.of(employeeCareerPackage));
        Mockito.when(submittedCareerPackageMapper.submittedCareerPackageDTOToSubmittedCareerPackage(submittedCareerPackageDTO)).thenReturn(submittedCareerPackage);
        submittedCareerPackageService.createSubmittedCareerPackage(submittedCareerPackageDTO);
        Mockito.verify(submittedCareerPackageRepository, Mockito.times(1)).findById(1);
    }

    @Test
    public void testCreateSubmittedCareerPackage_SubmittedCareerPackageAlreadyExists(){
        SubmittedCareerPackage submittedCareerPackage = new SubmittedCareerPackage();
        submittedCareerPackage.setId(1);
        SubmittedCareerPackageDTO submittedCareerPackageDTO = new SubmittedCareerPackageDTO();
        submittedCareerPackageDTO.setId(1);
        Mockito.when(submittedCareerPackageRepository.findById(1)).thenReturn(Optional.of(submittedCareerPackage));
        assertThrows(ExistsException.class, () -> submittedCareerPackageService.createSubmittedCareerPackage(submittedCareerPackageDTO));
    }

    @Test
    public void testCreateSubmittedCareerPackage_EmployeeCareerPackageNotFound(){
        SubmittedCareerPackageDTO submittedCareerPackageDTO = new SubmittedCareerPackageDTO();
        submittedCareerPackageDTO.setId(1);
        EmployeeCareerPackageDTO employeeCareerPackageDTO = new EmployeeCareerPackageDTO();
        employeeCareerPackageDTO.setId(1);
        submittedCareerPackageDTO.setEmployeeCareerPackage(employeeCareerPackageDTO);
        Mockito.when(submittedCareerPackageRepository.findById(1)).thenReturn(Optional.empty());
        Mockito.when(employeeCareerPackageRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(ExistsException.class, () -> submittedCareerPackageService.createSubmittedCareerPackage(submittedCareerPackageDTO));
    }

    @Test
    public void testDeleteSubmittedCareerPackage_Success(){
        SubmittedCareerPackage submittedCareerPackage = new SubmittedCareerPackage();
        submittedCareerPackage.setId(1);
        Mockito.when(submittedCareerPackageRepository.findById(1)).thenReturn(Optional.of(submittedCareerPackage));
        submittedCareerPackageService.deleteSubmittedCareerPackage(1);
        Mockito.verify(submittedCareerPackageRepository, Mockito.times(1)).findById(1);
    }

    @Test
    public void testDeleteSubmittedCareerPackage_NotFound(){
        Mockito.when(submittedCareerPackageRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(ExistsException.class, () -> submittedCareerPackageService.deleteSubmittedCareerPackage(1));
    }

    @Test
    public void testUpdateSubmittedCareerPackage_Success(){
        CareerPackageStatus careerPackageStatus = CareerPackageStatus.valueOf("APPROVED");
        SubmittedCareerPackage submittedCareerPackage = new SubmittedCareerPackage();
        submittedCareerPackage.setId(1);
        submittedCareerPackage.setCareerPackageStatus(careerPackageStatus);
        Mockito.when(submittedCareerPackageRepository.findById(1)).thenReturn(Optional.of(submittedCareerPackage));
        submittedCareerPackageService.updateSubmittedCareerPackage(1,careerPackageStatus);
        Mockito.verify(submittedCareerPackageRepository, Mockito.times(1)).findById(1);
    }

    @Test
    public void testUpdateSubmittedCareerPackage_NotFound(){
        CareerPackageStatus careerPackageStatus = CareerPackageStatus.valueOf("APPROVED");
        Mockito.when(submittedCareerPackageRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(ExistsException.class, () -> submittedCareerPackageService.updateSubmittedCareerPackage(1,careerPackageStatus));
    }

    @Test
    public void testGetSubmittedCareerPackage_Success(){
        SubmittedCareerPackage submittedCareerPackage = new SubmittedCareerPackage();
        submittedCareerPackage.setId(1);
        SubmittedCareerPackageDTO submittedCareerPackageDTO = new SubmittedCareerPackageDTO();
        submittedCareerPackageDTO.setId(1);
        Mockito.when(submittedCareerPackageRepository.findById(1)).thenReturn(Optional.of(submittedCareerPackage));
        Mockito.when(submittedCareerPackageMapper.submittedCareerPackageToSubmittedCareerPackageDTO(submittedCareerPackage)).thenReturn(submittedCareerPackageDTO);
        SubmittedCareerPackageDTO result = submittedCareerPackageService.getSubmittedCareerPackage(1);
        assertEquals(1, result.getId());
    }

    @Test
    public void testGetSubmittedCareerPackage_NotFound(){
        Mockito.when(submittedCareerPackageRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(ExistsException.class, () -> submittedCareerPackageService.getSubmittedCareerPackage(1));
    }

    @Test
    public void testGetAllEmployeeSubmittedCareerPackage(){
        SubmittedCareerPackage submittedCareerPackage = new SubmittedCareerPackage();
        submittedCareerPackage.setEmployeeId(1);
        SubmittedCareerPackageDTO submittedCareerPackageDTO = new SubmittedCareerPackageDTO();
        submittedCareerPackageDTO.setEmployeeId(1);
        List<SubmittedCareerPackage> submittedCareerPackageList = new ArrayList<>();
        submittedCareerPackageList.add(submittedCareerPackage);
        List<SubmittedCareerPackageDTO> submittedCareerPackageDTOS = new ArrayList<>();
        submittedCareerPackageDTOS.add(submittedCareerPackageDTO);
        Mockito.when(submittedCareerPackageRepository.findByEmployeeId(1)).thenReturn(Optional.of(submittedCareerPackageList));
        Mockito.when(submittedCareerPackageMapper.submittedCareerPackageListToSubmittedCareerPackageDTOList(submittedCareerPackageList)).thenReturn(submittedCareerPackageDTOS);
        List<SubmittedCareerPackageDTO> result = submittedCareerPackageService.getAllEmployeeSubmittedCareerPackage(1);
        assertEquals(1, result.get(0).getEmployeeId());
    }

    @Test
    public void testGetAllManagerReceivedCareerPackage(){
        SubmittedCareerPackage submittedCareerPackage = new SubmittedCareerPackage();
        submittedCareerPackage.setManagerId(1);
        SubmittedCareerPackageDTO submittedCareerPackageDTO = new SubmittedCareerPackageDTO();
        submittedCareerPackageDTO.setManagerId(1);
        List<SubmittedCareerPackage> submittedCareerPackageList = new ArrayList<>();
        submittedCareerPackageList.add(submittedCareerPackage);
        List<SubmittedCareerPackageDTO> submittedCareerPackageDTOS = new ArrayList<>();
        submittedCareerPackageDTOS.add(submittedCareerPackageDTO);
        Mockito.when(submittedCareerPackageRepository.findByManagerId(1)).thenReturn(Optional.of(submittedCareerPackageList));
        Mockito.when(submittedCareerPackageMapper.submittedCareerPackageListToSubmittedCareerPackageDTOList(submittedCareerPackageList)).thenReturn(submittedCareerPackageDTOS);
        List<SubmittedCareerPackageDTO> result = submittedCareerPackageService.getAllManagerReceivedCareerPackage(1);
        assertEquals(1, result.get(0).getManagerId());
    }

    @Test
    public void testGetAllSubmittedCareerPackages(){
        List<SubmittedCareerPackage> submittedCareerPackageList = new ArrayList<>();
        List<SubmittedCareerPackageDTO> submittedCareerPackageDTOS = new ArrayList<>();
        Mockito.when(submittedCareerPackageRepository.findAll()).thenReturn(submittedCareerPackageList);
        Mockito.when(submittedCareerPackageMapper.submittedCareerPackageListToSubmittedCareerPackageDTOList(submittedCareerPackageList)).thenReturn(submittedCareerPackageDTOS);
        List<SubmittedCareerPackageDTO> result = submittedCareerPackageService.getAllSubmittedCareerPackages();
        assertEquals(0, result.size());
    }

    @Test
    public void testDownloadSubmittedCareerPackage_Success(){
        EmployeeCareerPackage employeeCareerPackage = new EmployeeCareerPackage();
        employeeCareerPackage.setCareerPackage(null);
        employeeCareerPackage.setId(1);

        SubmittedCareerPackage submittedCareerPackage = new SubmittedCareerPackage();
        submittedCareerPackage.setId(1);
        submittedCareerPackage.setEmployeeCareerPackage(employeeCareerPackage);

        Mockito.when(submittedCareerPackageRepository.findById(1))
                .thenReturn(Optional.of(submittedCareerPackage));

        byte[] result = submittedCareerPackageService.downloadSubmittedCareerPackage(1);
        assertArrayEquals(null, result);
    }

    @Test
    public void testDownloadSubmittedCareerPackage_NotFound(){
        Mockito.when(submittedCareerPackageRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(ExistsException.class, () -> submittedCareerPackageService.downloadSubmittedCareerPackage(1));
    }
}
