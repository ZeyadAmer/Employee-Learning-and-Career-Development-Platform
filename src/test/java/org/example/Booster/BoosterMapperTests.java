package org.example.Booster;

import org.example.Entities.Booster;
import org.example.Entities.BoosterType;
import org.example.Mappers.BoosterDTO;
import org.example.Mappers.BoosterMapper;
import org.example.Mappers.BoosterTypeDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class BoosterMapperTests {

    private BoosterMapper boosterMapper;

    @BeforeEach
    public void setUp() {
        boosterMapper = Mappers.getMapper(BoosterMapper.class);
    }

    @Test
    public void testBoosterToBoosterDTO() {
        BoosterType boosterType = new BoosterType();
        boosterType.setId(1);
        boosterType.setName("Speed");

        Booster booster = new Booster();
        booster.setId(1);
        booster.setName("Booster1");
        booster.setActive(true);
        booster.setBoosterType(boosterType);

        BoosterDTO boosterDTO = boosterMapper.boosterToBoosterDTO(booster);

        assertNotNull(boosterDTO);
        assertEquals(booster.getId(), boosterDTO.getId());
        assertEquals(booster.getName(), boosterDTO.getName());
        assertEquals(booster.isActive(), boosterDTO.isActive());
        assertEquals(booster.getBoosterType().getId(), boosterDTO.getBoosterType().getId());
        assertEquals(booster.getBoosterType().getName(), boosterDTO.getBoosterType().getName());
    }

    @Test
    public void testBoosterDTOTObooster() {
        BoosterTypeDTO boosterTypeDTO = new BoosterTypeDTO("Speed",1);
        BoosterDTO boosterDTO = new BoosterDTO("Booster1", true, boosterTypeDTO);

        Booster booster = boosterMapper.boosterDTOTObooster(boosterDTO);

        assertNotNull(booster);
        assertEquals(boosterDTO.getName(), booster.getName());
        assertEquals(boosterDTO.isActive(), booster.isActive());
        assertEquals(boosterDTO.getBoosterType().getId(), booster.getBoosterType().getId());
        assertEquals(boosterDTO.getBoosterType().getName(), booster.getBoosterType().getName());
    }

    @Test
    public void testBoostersListToBoosterDTOsList() {
        BoosterType boosterType = new BoosterType();
        boosterType.setId(1);
        boosterType.setName("Speed");

        Booster booster1 = new Booster();
        booster1.setId(1);
        booster1.setName("Booster1");
        booster1.setActive(true);
        booster1.setBoosterType(boosterType);

        Booster booster2 = new Booster();
        booster2.setId(2);
        booster2.setName("Booster2");
        booster2.setActive(false);
        booster2.setBoosterType(boosterType);

        List<Booster> boosterList = Arrays.asList(booster1, booster2);

        List<BoosterDTO> boosterDTOList = boosterMapper.boostersListToBoosterDTOsList(boosterList);

        assertNotNull(boosterDTOList);
        assertEquals(2, boosterDTOList.size());
        assertEquals(boosterList.get(0).getName(), boosterDTOList.get(0).getName());
        assertEquals(boosterList.get(1).getName(), boosterDTOList.get(1).getName());
    }

    @Test
    public void testBoosterDTOsListToBoostersList() {
        BoosterTypeDTO boosterTypeDTO = new BoosterTypeDTO( "Speed",1);
        BoosterDTO boosterDTO1 = new BoosterDTO("Booster1", true, boosterTypeDTO);
        BoosterDTO boosterDTO2 = new BoosterDTO("Booster2", false, boosterTypeDTO);

        List<BoosterDTO> boosterDTOList = Arrays.asList(boosterDTO1, boosterDTO2);

        List<Booster> boosterList = boosterMapper.boosterDTOsListToBoostersList(boosterDTOList);

        assertNotNull(boosterList);
        assertEquals(2, boosterList.size());
        assertEquals(boosterDTOList.get(0).getName(), boosterList.get(0).getName());
        assertEquals(boosterDTOList.get(1).getName(), boosterList.get(1).getName());
    }
}
