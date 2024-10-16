package org.example.Mappers;

import org.example.Classes.CareerPackageTemplate;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CareerPackageTemplateMapper {
    CareerPackageTemplateDTO careerPackageTemplateToCareerPackageTemplateDTO(CareerPackageTemplate careerPackageTemplate);
    CareerPackageTemplate careerPackageTemplateDTOToCareerPackageTemplate(CareerPackageTemplateDTO careerPackageTemplateDTO);
    List<CareerPackageTemplateDTO> careerPackageTemplateListToCareerPackageTemplateDTOList(List<CareerPackageTemplate> careerPackageTemplates);
}
