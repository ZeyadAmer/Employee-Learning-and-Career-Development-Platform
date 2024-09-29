package org.example.Services;
import org.example.Entities.Title;
import org.example.Exceptions.ExistsException;
import org.example.Mappers.TitleDTO;
import org.example.Mappers.TitleMapper;
import org.example.Repositories.DepartmentRepository;
import org.example.Repositories.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TitleService {
    private final TitleRepository titleRepository;
    private final TitleMapper titleMapper;
    private final DepartmentRepository departmentRepository;
    public TitleService(TitleRepository titleRepository, TitleMapper titleMapper, DepartmentRepository departmentRepository) {
        this.titleRepository = titleRepository;
        this.titleMapper = titleMapper;
        this.departmentRepository = departmentRepository;
    }
    public void createTitle(TitleDTO titleDTO) {
        Optional<Title> title = titleRepository.findByName(titleDTO.getName());
        if (title.isPresent()) {
            throw new ExistsException("This title already exists");
        }
        if(departmentRepository.findByName(titleDTO.getDepartment().getName()).isEmpty()){
            throw new ExistsException("This department does not exist");
        }
        Title newTitle = titleMapper.titleDTOToTitle(titleDTO);
        newTitle.setDepartment(departmentRepository.findByName(titleDTO.getDepartment().getName()).get());
        titleRepository.save(newTitle);
    }
    public void deleteTitle(String titleName) {
        Optional<Title> title = titleRepository.findByName(titleName);
        if (title.isEmpty()) {
            throw new ExistsException("This title does not exist");
        }
        titleRepository.delete(title.get());
    }
    public void updateTitleName(String oldName,String newName) {
        Optional<Title> title = titleRepository.findByName(oldName);
        if (title.isEmpty()) {
            throw new ExistsException("This title does not exist");
        }
        Title updatedTitle = title.get();
        updatedTitle.setName(newName);
        titleRepository.save(updatedTitle);
    }
    public void updateTitleDepartment(String titleName,String department) {
        Optional<Title> title = titleRepository.findByName(titleName);
        if (title.isEmpty()) {
            throw new ExistsException("This title does not exist");
        }
        if(departmentRepository.findByName(department).isEmpty()){
            throw new ExistsException("This department does not exist");
        }
        Title updatedTitle = title.get();
        updatedTitle.setDepartment(departmentRepository.findByName(department).get());
        titleRepository.save(updatedTitle);
    }
    public List<TitleDTO> getAllTitles() {
        List<Title> titles = titleRepository.findAll();
        return titleMapper.titleListToTitleDTOList(titles);
    }
}
