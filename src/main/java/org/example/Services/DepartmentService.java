package org.example.Services;
import org.example.Entities.Department;
import org.example.Entities.Title;
import org.example.Exceptions.ExistsException;
import org.example.Mappers.DepartmentDTO;
import org.example.Mappers.DepartmentMapper;
import org.example.Repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;
    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository, DepartmentMapper departmentMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
    }
    public void createDepartment(DepartmentDTO departmentDTO) {
        Optional<Department> department = departmentRepository.findByName(departmentDTO.getName());
        if (department.isPresent()) {
            throw new ExistsException("This department already exists");
        }
        departmentRepository.save(departmentMapper.departmentDTOToDepartment(departmentDTO));
    }
    public void deleteDepartment(String departmentName) {
        Optional<Department> department = departmentRepository.findByName(departmentName);
        if (department.isEmpty()) {
            throw new ExistsException("This department does not exist");
        }
        departmentRepository.delete(department.get());
    }
    public void updateDepartment(DepartmentDTO departmentDTO,String name) {
        Optional<Department> department = departmentRepository.findByName(departmentDTO.getName());
        if (department.isEmpty()) {
            throw new ExistsException("The department you want to update does not exist");
        }
        if(departmentRepository.findByName(name).isPresent()) {
            throw new ExistsException("Duplicate department name");
        }
        Department updatedDepartment = department.get();
        updatedDepartment.setName(name);
        departmentRepository.save(updatedDepartment);
    }
    public List<DepartmentDTO> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departmentMapper.departmentsToDepartmentDTOs(departments);
    }
    public List<Title> getAllTitles(DepartmentDTO departmentDTO) {
        Department department = departmentMapper.departmentDTOToDepartment(departmentDTO);
        return department.getTitles();
    }

}
