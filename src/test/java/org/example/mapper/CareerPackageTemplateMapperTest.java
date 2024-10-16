package org.example.mapper;

import org.example.classes.CareerPackageTemplate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class CareerPackageTemplateMapperTest {

    @InjectMocks
    private CareerPackageTemplateMapper careerPackageTemplateMapper = Mappers.getMapper(CareerPackageTemplateMapper.class);

    @Test
    public void testCareerPackageTemplateToCareerPackageTemplateDTO() {
        CareerPackageTemplate careerPackageTemplate = new CareerPackageTemplate();
        careerPackageTemplate.setId(1);

        CareerPackageTemplateDTO careerPackageTemplateDTO = careerPackageTemplateMapper.careerPackageTemplateToCareerPackageTemplateDTO(careerPackageTemplate);
        assertEquals(careerPackageTemplate.getId(), careerPackageTemplateDTO.getId());
    }

    @Test
    public void testCareerPackageTemplateDTOToCareerPackageTemplate() {
        CareerPackageTemplateDTO careerPackageTemplateDTO = new CareerPackageTemplateDTO();
        careerPackageTemplateDTO.setId(1);

        CareerPackageTemplate careerPackageTemplate = careerPackageTemplateMapper.careerPackageTemplateDTOToCareerPackageTemplate(careerPackageTemplateDTO);
        assertEquals(careerPackageTemplateDTO.getId(), careerPackageTemplate.getId());
    }

    @Test
    public void testCareerPackageTemplateListToCareerPackageTemplateDTOList() {
        CareerPackageTemplate careerPackageTemplate1 = new CareerPackageTemplate();
        careerPackageTemplate1.setId(1);

        CareerPackageTemplate careerPackageTemplate2 = new CareerPackageTemplate();
        careerPackageTemplate2.setId(2);

        List<CareerPackageTemplate> careerPackageTemplates = Arrays.asList(careerPackageTemplate1, careerPackageTemplate2);
        List<CareerPackageTemplateDTO> careerPackageTemplateDTOS = careerPackageTemplateMapper.careerPackageTemplateListToCareerPackageTemplateDTOList(careerPackageTemplates);

        assertEquals(careerPackageTemplates.size(), careerPackageTemplateDTOS.size());
    }
}
