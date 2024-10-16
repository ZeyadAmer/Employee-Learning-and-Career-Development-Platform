package org.example.service;

import org.example.classes.CareerPackageTemplate;
import org.example.classes.EmployeeCareerPackage;
import org.example.exception.ExistsException;
import org.example.mapper.EmployeeCareerPackageDTO;
import org.example.mapper.EmployeeCareerPackageMapper;
import org.example.repository.CareerPackageTemplateRepository;
import org.example.repository.EmployeeCareerPackageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeCareerPackageService {

    private final EmployeeCareerPackageMapper employeeCareerPackageMapper;
    private final EmployeeCareerPackageRepository employeeCareerPackageRepository;
    private final CareerPackageTemplateRepository careerPackageTemplateRepository;

    public EmployeeCareerPackageService(EmployeeCareerPackageMapper employeeCareerPackageMapper, EmployeeCareerPackageRepository employeeCareerPackageRepository, CareerPackageTemplateRepository careerPackageTemplateRepository) {
        this.employeeCareerPackageMapper = employeeCareerPackageMapper;
        this.employeeCareerPackageRepository = employeeCareerPackageRepository;
        this.careerPackageTemplateRepository = careerPackageTemplateRepository;
    }

    public void createEmployeeCareerPackage(EmployeeCareerPackageDTO employeeCareerPackageDTO){
        if(employeeCareerPackageRepository.findById(employeeCareerPackageDTO.getId()).isPresent()){
            throw new ExistsException("Employee Career Package already exists");
        }
        Optional<CareerPackageTemplate> careerPackageTemplate = careerPackageTemplateRepository.findByTitle(employeeCareerPackageDTO.getCareerPackageTemplateDTO().getTitle());
        if(careerPackageTemplate.isEmpty()){
            throw new ExistsException("Career Package Template does not exist.");
        }
        System.out.println("employee id: "+ employeeCareerPackageDTO.getEmployeeId());
        System.out.println("career package: "+employeeCareerPackageDTO.getCareerPackage());
        employeeCareerPackageRepository.save(employeeCareerPackageMapper.employeeCareerPackageDTOToEmployeeCareerPackage(employeeCareerPackageDTO));
    }

    public void deleteEmployeeCareerPackage(int id){
        Optional<EmployeeCareerPackage> employeeCareerPackage = employeeCareerPackageRepository.findById(id);
        if(employeeCareerPackage.isEmpty()){
            throw new ExistsException("Employee Career Package does not exist");
        }
        employeeCareerPackageRepository.delete(employeeCareerPackage.get());
    }

    public void updateEmployeeCareerPackage(int id, byte[] careerPackage){
        Optional<EmployeeCareerPackage> employeeCareerPackage = employeeCareerPackageRepository.findById(id);
        if(employeeCareerPackage.isEmpty()){
            throw new ExistsException("Employee Career Package does not exist");
        }
        Optional<CareerPackageTemplate> careerPackageTemplate = careerPackageTemplateRepository.findByTitle(employeeCareerPackage.get().getCareerPackageTemplate().getTitle());
        if(careerPackageTemplate.isEmpty()){
            throw new ExistsException("Career Package Template does not exist.");
        }
        EmployeeCareerPackage updatedEmployeeCareerPackage = employeeCareerPackage.get();
        updatedEmployeeCareerPackage.setCareerPackage(careerPackage);
        updatedEmployeeCareerPackage.setCareerPackageTemplate(careerPackageTemplate.get());
        employeeCareerPackageRepository.save(updatedEmployeeCareerPackage);
    }

    public  EmployeeCareerPackageDTO getEmployeeCareerPackage(int id){
        Optional<EmployeeCareerPackage> employeeCareerPackage = employeeCareerPackageRepository.findById(id);
        if(employeeCareerPackage.isEmpty()){
            throw new ExistsException("Employee Career Package does not exist");
        }
        return employeeCareerPackageMapper.employeeCareerPackageToEmployeeCareerPackageDTO(employeeCareerPackage.get());
    }

    @Transactional
    public List<EmployeeCareerPackageDTO> getEmployeeCareerPackages(int employeeId){
        Optional<List<EmployeeCareerPackage>> employeeCareerPackages = employeeCareerPackageRepository.findByEmployeeId(employeeId);
        System.out.println(employeeCareerPackages);
        return employeeCareerPackageMapper.employeeCareerPackageListToEmployeeCareerPackageDTOList(employeeCareerPackages.get());
    }

    public List<EmployeeCareerPackageDTO> getAllEmployeeCareerPackages(){
        List<EmployeeCareerPackage> employeeCareerPackages = employeeCareerPackageRepository.findAll();
        return employeeCareerPackageMapper.employeeCareerPackageListToEmployeeCareerPackageDTOList(employeeCareerPackages);
    }
}
