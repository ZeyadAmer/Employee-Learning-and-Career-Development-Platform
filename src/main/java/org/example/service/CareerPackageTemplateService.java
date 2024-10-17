package org.example.service;

import org.example.classes.CareerPackageTemplate;
import org.example.exception.ExistsException;
import org.example.mapper.CareerPackageTemplateDTO;
import org.example.mapper.CareerPackageTemplateMapper;
import org.example.repository.CareerPackageTemplateRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CareerPackageTemplateService {

    private final CareerPackageTemplateMapper careerPackageTemplateMapper;
    private final CareerPackageTemplateRepository careerPackageTemplateRepository;

    public CareerPackageTemplateService(CareerPackageTemplateMapper careerPackageTemplateMapper, CareerPackageTemplateRepository careerPackageTemplateRepository) {
        this.careerPackageTemplateMapper = careerPackageTemplateMapper;
        this.careerPackageTemplateRepository = careerPackageTemplateRepository;
    }

    public void createCareerPackageTemplate(CareerPackageTemplateDTO careerPackageTemplateDTO){
        if(careerPackageTemplateRepository.findByTitle(careerPackageTemplateDTO.getTitle()).isPresent()){
            throw new ExistsException("Career Package Template already exists");
        }
        careerPackageTemplateRepository.save(careerPackageTemplateMapper.careerPackageTemplateDTOToCareerPackageTemplate(careerPackageTemplateDTO));
    }

    public void deleteCareerPackageTemplate(String title){
        Optional<CareerPackageTemplate> careerPackageTemplate = careerPackageTemplateRepository.findByTitle(title);
        if(careerPackageTemplate.isEmpty()){
            throw new ExistsException("Career Package Template does not exist");
        }
        careerPackageTemplateRepository.delete(careerPackageTemplate.get());
    }

    public void updateCareerPackageTemplate(CareerPackageTemplateDTO careerPackageTemplateDTO){
        Optional<CareerPackageTemplate> careerPackageTemplate = careerPackageTemplateRepository.findByTitle(careerPackageTemplateDTO.getTitle());
        if(careerPackageTemplate.isEmpty()){
            throw new ExistsException("Career Package Template does not exist");
        }
        CareerPackageTemplate updatedCareerPackageTemplate = careerPackageTemplate.get();
        updatedCareerPackageTemplate.setCareerPackageTemplate(careerPackageTemplateDTO.getCareerPackageTemplate());
        careerPackageTemplateRepository.save(updatedCareerPackageTemplate);
    }

    @Transactional
    public CareerPackageTemplateDTO getCareerPackageTemplate(String title){
        Optional<CareerPackageTemplate> careerPackageTemplate = careerPackageTemplateRepository.findByTitle(title);
        if(careerPackageTemplate.isEmpty()){
            throw new ExistsException("Career Package Template does not exist");
        }
        return careerPackageTemplateMapper.careerPackageTemplateToCareerPackageTemplateDTO(careerPackageTemplate.get());
    }

    @Transactional
    public List<CareerPackageTemplateDTO> getAllCareerPackageTemplates(){
        List<CareerPackageTemplate> careerPackageTemplates = careerPackageTemplateRepository.findAll();
        return careerPackageTemplateMapper.careerPackageTemplateListToCareerPackageTemplateDTOList(careerPackageTemplates);
    }

    public byte[] downloadCareerPackageTemplate(String title){
        Optional<CareerPackageTemplate> careerPackageTemplate = careerPackageTemplateRepository.findByTitle(title);
        if(careerPackageTemplate.isEmpty()){
            throw new ExistsException("Career Package Template does not exist");
        }
        return careerPackageTemplate.get().getCareerPackageTemplate();
    }
}
