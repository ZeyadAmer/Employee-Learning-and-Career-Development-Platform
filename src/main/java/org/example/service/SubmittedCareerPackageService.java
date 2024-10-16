package org.example.service;

import org.example.classes.EmployeeCareerPackage;
import org.example.classes.SubmittedCareerPackage;
import org.example.enums.CareerPackageStatus;
import org.example.exception.ExistsException;
import org.example.mapper.SubmittedCareerPackageDTO;
import org.example.mapper.SubmittedCareerPackageMapper;
import org.example.repository.EmployeeCareerPackageRepository;
import org.example.repository.SubmittedCareerPackageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SubmittedCareerPackageService {

    private final SubmittedCareerPackageRepository submittedCareerPackageRepository;
    private final SubmittedCareerPackageMapper submittedCareerPackageMapper;
    private final EmployeeCareerPackageRepository employeeCareerPackageRepository;

    public SubmittedCareerPackageService(SubmittedCareerPackageRepository submittedCareerPackageRepository, SubmittedCareerPackageMapper submittedCareerPackageMapper, EmployeeCareerPackageRepository employeeCareerPackageRepository) {
        this.submittedCareerPackageRepository = submittedCareerPackageRepository;
        this.submittedCareerPackageMapper = submittedCareerPackageMapper;
        this.employeeCareerPackageRepository = employeeCareerPackageRepository;
    }

    public void createSubmittedCareerPackage(SubmittedCareerPackageDTO submittedCareerPackageDTO){
        Optional<EmployeeCareerPackage> employeeCareerPackage = employeeCareerPackageRepository.findById(submittedCareerPackageDTO.getEmployeeCareerPackage().getId());
        if(employeeCareerPackage.isEmpty()){
            throw new ExistsException("Employee Career Package does not exist");
        }
        SubmittedCareerPackage submittedCareerPackage = submittedCareerPackageMapper.submittedCareerPackageDTOToSubmittedCareerPackage(submittedCareerPackageDTO);
        submittedCareerPackage.setEmployeeCareerPackage(employeeCareerPackage.get());
        submittedCareerPackageRepository.save(submittedCareerPackage);
    }

    public void deleteSubmittedCareerPackage(int id){
        Optional<SubmittedCareerPackage> submittedCareerPackage = submittedCareerPackageRepository.findById(id);
        if(submittedCareerPackage.isEmpty()){
            throw new ExistsException("Submitted Career Package does not exist");
        }
        submittedCareerPackageRepository.delete(submittedCareerPackage.get());
    }

    public void updateSubmittedCareerPackage(int id, CareerPackageStatus careerPackageStatus){
        Optional<SubmittedCareerPackage> submittedCareerPackage = submittedCareerPackageRepository.findById(id);
        if(submittedCareerPackage.isEmpty()){
            throw new ExistsException("Submitted Career Package does not exist");
        }
        SubmittedCareerPackage updateSubmittedCareerPackage = submittedCareerPackage.get();
        updateSubmittedCareerPackage.setCareerPackageStatus(careerPackageStatus);
        submittedCareerPackageRepository.save(updateSubmittedCareerPackage);
    }

    public SubmittedCareerPackageDTO getSubmittedCareerPackage(int id){
        Optional<SubmittedCareerPackage> submittedCareerPackage = submittedCareerPackageRepository.findById(id);
        if(submittedCareerPackage.isEmpty()){
            throw new ExistsException("Submitted Career Package does not exist");
        }
        return submittedCareerPackageMapper.submittedCareerPackageToSubmittedCareerPackageDTO(submittedCareerPackage.get());
    }

    @Transactional
    public List<SubmittedCareerPackageDTO> getAllEmployeeSubmittedCareerPackage(int employeeId){
        Optional<List<SubmittedCareerPackage>> submittedCareerPackageDTOS = submittedCareerPackageRepository.findByEmployeeId(employeeId);
        return submittedCareerPackageMapper.submittedCareerPackageListToSubmittedCareerPackageDTOList(submittedCareerPackageDTOS.get());
    }

    @Transactional
    public List<SubmittedCareerPackageDTO> getAllManagerReceivedCareerPackage(int managerId){
        Optional<List<SubmittedCareerPackage>> submittedCareerPackages = submittedCareerPackageRepository.findByManagerId(managerId);
        return submittedCareerPackageMapper.submittedCareerPackageListToSubmittedCareerPackageDTOList(submittedCareerPackages.get());
    }

    public List<SubmittedCareerPackageDTO> getAllSubmittedCareerPackages(){
        List<SubmittedCareerPackage> submittedCareerPackages = submittedCareerPackageRepository.findAll();
        return submittedCareerPackageMapper.submittedCareerPackageListToSubmittedCareerPackageDTOList(submittedCareerPackages);
    }

    public byte[] downloadSubmittedCareerPackage(int id){
        Optional<SubmittedCareerPackage> submittedCareerPackage = submittedCareerPackageRepository.findById(id);
        if(submittedCareerPackage.isEmpty()){
            throw new ExistsException("Submitted Career Package does not exist");
        }
        return submittedCareerPackage.get().getEmployeeCareerPackage().getCareerPackage();
    }

}
