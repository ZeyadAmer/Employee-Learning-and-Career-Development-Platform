package org.example.Titles;
import org.example.Entities.Department;
import org.example.Entities.Title;
import org.example.Exceptions.ExistsException;
import org.example.Main;
import org.example.Mappers.DepartmentDTO;
import org.example.Mappers.TitleDTO;
import org.example.Mappers.TitleMapper;
import org.example.Repositories.DepartmentRepository;
import org.example.Repositories.TitleRepository;
import org.example.Services.TitleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class TitlesServiceTests {
    @Mock
    TitleRepository titleRepository;
    @Mock
    TitleMapper titleMapper;
    @Mock
    DepartmentRepository departmentRepository;

    @InjectMocks
    TitleService titleService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testGetAllTitles() {
        Title title = new Title();
        Title title1 = new Title();
        List<Title> titles = new ArrayList<Title>();
        titles.add(title);
        titles.add(title1);
        TitleDTO titleDTO = new TitleDTO("title 1",new DepartmentDTO(),true);
        TitleDTO titleDTO1 = new TitleDTO("title 2",new DepartmentDTO(),true);
        List<TitleDTO> titleDTOs = new ArrayList<>();
        titleDTOs.add(titleDTO);
        titleDTOs.add(titleDTO1);
        Mockito.when(titleRepository.findAll()).thenReturn(titles);
        Mockito.when(titleMapper.titleListToTitleDTOList(titles)).thenReturn(titleDTOs);
        List<TitleDTO> titleDTOs1 = titleService.getAllTitles();
        assertEquals(titleDTOs1.get(0).getName(), titleDTO.getName());
        assertEquals(titleDTOs1.get(1).getName(), titleDTO1.getName());
        Mockito.verify(titleRepository, Mockito.times(1)).findAll();
    }
    @Test
    public void testUpdateTitleDepartment_Success() {
        Title title = new Title();
        title.setName("title 1");
        Mockito.when(titleRepository.findByName("title 1")).thenReturn(Optional.of(title));
        Department department = new Department();
        department.setName("dept 1");
        Mockito.when(departmentRepository.findByName("dept 1")).thenReturn(Optional.of(department));
        titleService.updateTitleDepartment("title 1","dept 1");
        assertDoesNotThrow(()->titleService.updateTitleDepartment("title 1","dept 1"));
        Mockito.verify(titleRepository, Mockito.times(2)).findByName("title 1");
    }
    @Test
    public void testUpdateTitleDepartment_TitleNotFound(){
        Mockito.when(titleRepository.findByName("title 1")).thenReturn(Optional.empty());
        Department department = new Department();
        department.setName("dept 1");
        Mockito.when(departmentRepository.findByName("dept 1")).thenReturn(Optional.of(department));
        assertThrows(ExistsException.class,()->titleService.updateTitleDepartment("title 1","dept 1"));
        Mockito.verify(titleRepository, Mockito.times(1)).findByName("title 1");
    }
    @Test
    public void testUpdateTitleDepartment_DepartmentNotFound(){
        Title title = new Title();
        title.setName("title 1");
        Mockito.when(titleRepository.findByName("title 1")).thenReturn(Optional.of(title));
        Mockito.when(departmentRepository.findByName("dept 1")).thenReturn(Optional.empty());
        assertThrows(ExistsException.class,()->titleService.updateTitleDepartment("title 1","dept 1"));
        Mockito.verify(titleRepository, Mockito.times(1)).findByName("title 1");
    }
    @Test
    public void testUpdateTitleName_TitleNotFound(){
        Mockito.when(titleRepository.findByName("title 1")).thenReturn(Optional.empty());
        assertThrows(ExistsException.class,()->titleService.updateTitleName("title 1","title 23"));
        Mockito.verify(titleRepository, Mockito.times(1)).findByName("title 1");
    }
    @Test
    public void testUpdateTitleName_Success(){
        Title title = new Title();
        Mockito.when(titleRepository.findByName("title 1")).thenReturn(Optional.of(title));
        titleService.updateTitleName("title 1","title 23");
        Mockito.verify(titleRepository, Mockito.times(1)).findByName("title 1");
    }
    @Test
    public void testDeleteTitle_Success(){
        Title title = new Title();
        title.setName("title 1");
        Mockito.when(titleRepository.findByName("title 1")).thenReturn(Optional.of(title));
        titleService.deleteTitle("title 1");
        Mockito.verify(titleRepository, Mockito.times(1)).findByName("title 1");
    }
    @Test
    public void testDeleteTitle_TitleNotFound(){
        Mockito.when(titleRepository.findByName("title 1")).thenReturn(Optional.empty());
        assertThrows(ExistsException.class,()->titleService.deleteTitle("title 1"));
        Mockito.verify(titleRepository, Mockito.times(1)).findByName("title 1");
    }
    @Test
    public void testCreateTitle_Success(){
        Title title = new Title();
        title.setName("title 1");
        Department department = new Department();
        department.setName("dept 1");
        TitleDTO titleDTO = new TitleDTO("title 1",new DepartmentDTO(),true);
        titleDTO.getDepartment().setName("dept 1");
        Mockito.when(titleRepository.findByName("title 1")).thenReturn(Optional.empty());
        Mockito.when(titleMapper.titleDTOToTitle(titleDTO)).thenReturn(title);
        Mockito.when(departmentRepository.findByName("dept 1")).thenReturn(Optional.of(department));
        titleService.createTitle(titleDTO);
    }
    @Test
    public void testCreateTitle_DepartmentDoesntExist(){
        Title title = new Title();
        title.setName("title 1");
        TitleDTO titleDTO = new TitleDTO("title 1",new DepartmentDTO(),true);
        titleDTO.getDepartment().setName("dept 1");
        Mockito.when(titleRepository.findByName("title 1")).thenReturn(Optional.empty());
        Mockito.when(titleMapper.titleDTOToTitle(titleDTO)).thenReturn(title);
        Mockito.when(departmentRepository.findByName("dept 1")).thenReturn(Optional.empty());
        assertThrows(ExistsException.class,()->titleService.createTitle(titleDTO));
    }
    @Test
    public void testCreateTitle_TitleExists(){
        Title title = new Title();
        title.setName("title 1");
        TitleDTO titleDTO = new TitleDTO("title 1",new DepartmentDTO(),true);
        titleDTO.getDepartment().setName("dept 1");
        Mockito.when(titleRepository.findByName("title 1")).thenReturn(Optional.of(title));
       assertThrows(ExistsException.class,()->titleService.createTitle(titleDTO));
    }
}
