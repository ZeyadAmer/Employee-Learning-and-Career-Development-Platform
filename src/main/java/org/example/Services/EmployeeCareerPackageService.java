package org.example.Services;

import org.example.Classes.EmployeeCareerPackage;
import org.example.Exceptions.ExistsException;
import org.example.Mappers.EmployeeCareerPackageDTO;
import org.example.Mappers.EmployeeCareerPackageMapper;
import org.example.Repository.EmployeeCareerPackageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeCareerPackageService {

    private final EmployeeCareerPackageMapper employeeCareerPackageMapper;
    private final EmployeeCareerPackageRepository employeeCareerPackageRepository;

    public EmployeeCareerPackageService(EmployeeCareerPackageMapper employeeCareerPackageMapper, EmployeeCareerPackageRepository employeeCareerPackageRepository) {
        this.employeeCareerPackageMapper = employeeCareerPackageMapper;
        this.employeeCareerPackageRepository = employeeCareerPackageRepository;
    }

    public void createEmployeeCareerPackage(EmployeeCareerPackageDTO employeeCareerPackageDTO){
        if(employeeCareerPackageRepository.findById(employeeCareerPackageDTO.getId()).isPresent()){
            throw new ExistsException("Employee Career Package already exists");
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
        EmployeeCareerPackage updatedEmployeeCareerPackage = employeeCareerPackage.get();
        updatedEmployeeCareerPackage.setCareerPackage(careerPackage);
        employeeCareerPackageRepository.save(updatedEmployeeCareerPackage);
    }

    public  EmployeeCareerPackageDTO getEmployeeCareerPackage(int id){
        Optional<EmployeeCareerPackage> employeeCareerPackage = employeeCareerPackageRepository.findById(id);
        if(employeeCareerPackage.isEmpty()){
            throw new ExistsException("Employee Career Package does not exist");
        }
        return employeeCareerPackageMapper.employeeCareerPackageToEmployeeCareerPackageDTO(employeeCareerPackage.get());
    }

    public List<EmployeeCareerPackageDTO> getEmployeeCareerPackages(int employeeId){
        Optional<List<EmployeeCareerPackage>> employeeCareerPackages = employeeCareerPackageRepository.findByEmployeeId(employeeId);
        return employeeCareerPackageMapper.employeeCareerPackageListToEmployeeCareerPackageDTOList(employeeCareerPackages.get());
    }

    public List<EmployeeCareerPackageDTO> getAllEmployeeCareerPackages(){
        List<EmployeeCareerPackage> employeeCareerPackages = employeeCareerPackageRepository.findAll();
        return employeeCareerPackageMapper.employeeCareerPackageListToEmployeeCareerPackageDTOList(employeeCareerPackages);
    }
}
