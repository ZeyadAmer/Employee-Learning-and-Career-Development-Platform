package org.example.service;

import org.example.mapper.SubmittedCareerPackageMapper;
import org.example.repository.EmployeeCareerPackageRepository;
import org.example.repository.SubmittedCareerPackageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;

public class SubmittedCareerPackageServiceTest {

    @Mock
    SubmittedCareerPackageRepository submittedCareerPackageRepository;
    @Mock
    SubmittedCareerPackageMapper submittedCareerPackageMapper;
    @Mock
    EmployeeCareerPackageRepository employeeCareerPackageRepository;
    @InjectMocks
    SubmittedCareerPackageService submittedCareerPackageService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateSubmittedCareerPackage(){

    }
}
