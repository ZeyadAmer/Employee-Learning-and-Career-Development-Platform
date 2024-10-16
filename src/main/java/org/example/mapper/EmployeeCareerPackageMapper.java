package org.example.mapper;

import org.example.classes.EmployeeCareerPackage;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeCareerPackageMapper {
    EmployeeCareerPackageDTO employeeCareerPackageToEmployeeCareerPackageDTO(EmployeeCareerPackage employeeCareerPackage);
    EmployeeCareerPackage employeeCareerPackageDTOToEmployeeCareerPackage(EmployeeCareerPackageDTO employeeCareerPackageDTO);
    List<EmployeeCareerPackageDTO> employeeCareerPackageListToEmployeeCareerPackageDTOList(List<EmployeeCareerPackage> employeeCareerPackages);
    List<EmployeeCareerPackage> employeeCareerPackageDTOListToEmployeeCareerPackageList(List<EmployeeCareerPackageDTO> employeeCareerPackageDTOS);
}
