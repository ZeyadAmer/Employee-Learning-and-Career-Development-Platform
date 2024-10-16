package org.example.Titles;

import org.example.Entities.Department;
import org.example.Entities.Title;
import org.example.Mappers.DepartmentDTO;
import org.example.Mappers.TitleDTO;
import org.example.Mappers.TitleMapper;
import org.example.Mappers.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TitlesMapperTests {
    TitleMapper titleMapper;
    @BeforeEach
    public void setUp() {
        titleMapper = Mappers.getMapper(TitleMapper.class);
    }


    @Test
    public void testTitleToTitleDTO() {
        Title title = new Title();
        title.setName("Software Engineer");
        title.setDepartment(new Department("Engineering"));

        TitleDTO titleDTO = titleMapper.titleToTitleDTO(title);

        assertEquals("Software Engineer", titleDTO.getName());
        assertEquals("Engineering", titleDTO.getDepartment().getName());
    }

    @Test
    public void testTitleDTOToTitle() {
        TitleDTO titleDTO = new TitleDTO();
        titleDTO.setName("Software Engineer");
        titleDTO.setDepartment(new DepartmentDTO("Engineering"));

        Title title = titleMapper.titleDTOToTitle(titleDTO);

        assertEquals("Software Engineer", title.getName());
        assertEquals("Engineering", title.getDepartment().getName());
    }

    @Test
    public void testTitleListToTitleDTOList() {
        Title title = new Title();
        title.setName("Software Engineer");
        title.setDepartment(new Department("Engineering"));

        List<TitleDTO> titleDTOs = titleMapper.titleListToTitleDTOList(Collections.singletonList(title));

        assertEquals(1, titleDTOs.size());
        assertEquals("Software Engineer", titleDTOs.get(0).getName());
        assertEquals("Engineering", titleDTOs.get(0).getDepartment().getName());
    }

    @Test
    public void testTitleDTOListToTitleList() {
        TitleDTO titleDTO = new TitleDTO();
        titleDTO.setName("Software Engineer");
        titleDTO.setDepartment(new DepartmentDTO("Engineering"));

        List<Title> titles = titleMapper.titleDTOListToTitleList(Collections.singletonList(titleDTO));

        assertEquals(1, titles.size());
        assertEquals("Software Engineer", titles.get(0).getName());
        assertEquals("Engineering", titles.get(0).getDepartment().getName());
    }
}
