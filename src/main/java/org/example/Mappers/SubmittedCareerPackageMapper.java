package org.example.Mappers;

import org.example.Classes.SubmittedCareerPackage;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubmittedCareerPackageMapper {
    SubmittedCareerPackageDTO submittedCareerPackageToSubmittedCareerPackageDTO(SubmittedCareerPackage submittedCareerPackage);
    SubmittedCareerPackage submittedCareerPackageDTOToSubmittedCareerPackage(SubmittedCareerPackageDTO submittedCareerPackageDTO);
    List<SubmittedCareerPackageDTO> submittedCareerPackageListToSubmittedCareerPackageDTOList(List<SubmittedCareerPackage> submittedCareerPackages);
    List<SubmittedCareerPackage> submittedCareerPackageDTOListToSubmittedCareerPackageList(List<SubmittedCareerPackageDTO> submittedCareerPackageDTOS);
}
