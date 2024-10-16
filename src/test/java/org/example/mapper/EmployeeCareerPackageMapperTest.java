package org.example.mapper;

import org.example.classes.EmployeeCareerPackage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class EmployeeCareerPackageMapperTest {

    @InjectMocks
    private EmployeeCareerPackageMapper employeeCareerPackageMapper = Mappers.getMapper(EmployeeCareerPackageMapper.class);

    @Test
    public void testEmployeeCareerPackageToEmployeeCareerPackageDTO() {
        EmployeeCareerPackage employeeCareerPackage = new EmployeeCareerPackage();
        employeeCareerPackage.setId(1);

        EmployeeCareerPackageDTO employeeCareerPackageDTO = employeeCareerPackageMapper.employeeCareerPackageToEmployeeCareerPackageDTO(employeeCareerPackage);
        assertEquals(employeeCareerPackage.getId(), employeeCareerPackageDTO.getId());
    }

    @Test
    public void testEmployeeCareerPackageDTOToEmployeeCareerPackage() {
        EmployeeCareerPackageDTO employeeCareerPackageDTO = new EmployeeCareerPackageDTO();
        employeeCareerPackageDTO.setId(1);

        EmployeeCareerPackage employeeCareerPackage = employeeCareerPackageMapper.employeeCareerPackageDTOToEmployeeCareerPackage(employeeCareerPackageDTO);
        assertEquals(employeeCareerPackageDTO.getId(), employeeCareerPackage.getId());
    }

    @Test
    public void testEmployeeCareerPackageListToEmployeeCareerPackageDTOList() {
        EmployeeCareerPackage employeeCareerPackage1 = new EmployeeCareerPackage();
        employeeCareerPackage1.setId(1);

        EmployeeCareerPackage employeeCareerPackage2 = new EmployeeCareerPackage();
        employeeCareerPackage2.setId(2);

        List<EmployeeCareerPackage> employeeCareerPackages = Arrays.asList(employeeCareerPackage1, employeeCareerPackage2);
        List<EmployeeCareerPackageDTO> employeeCareerPackageDTOS = employeeCareerPackageMapper.employeeCareerPackageListToEmployeeCareerPackageDTOList(employeeCareerPackages);

        assertEquals(employeeCareerPackages.size(), employeeCareerPackageDTOS.size());
    }

    @Test
    public void testEmployeeCareerPackageDTOListToEmployeeCareerPackageList() {
        EmployeeCareerPackageDTO employeeCareerPackageDTO1 = new EmployeeCareerPackageDTO();
        employeeCareerPackageDTO1.setId(1);

        EmployeeCareerPackageDTO employeeCareerPackageDTO2 = new EmployeeCareerPackageDTO();
        employeeCareerPackageDTO2.setId(2);

        List<EmployeeCareerPackageDTO> employeeCareerPackageDTOS = Arrays.asList(employeeCareerPackageDTO1, employeeCareerPackageDTO2);
        List<EmployeeCareerPackage> employeeCareerPackages = employeeCareerPackageMapper.employeeCareerPackageDTOListToEmployeeCareerPackageList(employeeCareerPackageDTOS);

        assertEquals(employeeCareerPackageDTOS.size(), employeeCareerPackages.size());
    }
}
