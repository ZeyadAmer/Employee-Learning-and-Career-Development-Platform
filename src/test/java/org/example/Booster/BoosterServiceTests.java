package org.example.Services;

import org.example.Entities.Booster;
import org.example.Entities.BoosterType;
import org.example.Exceptions.ExistsException;
import org.example.Mappers.BoosterDTO;
import org.example.Mappers.BoosterMapper;
import org.example.Repositories.BoosterRepository;
import org.example.Repositories.BoosterTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import org.example.Mappers.BoosterTypeDTO;

@ExtendWith(MockitoExtension.class)
public class BoosterServiceTests {

    @Mock
    private BoosterRepository boosterRepository;

    @Mock
    private BoosterTypeRepository boosterTypeRepository;

    @Mock
    private BoosterMapper boosterMapper;

    @InjectMocks
    private BoosterService boosterService;

    private BoosterType boosterType;
    private Booster booster;
    private BoosterDTO boosterDTO;

    @BeforeEach
    public void setUp() {
        boosterType = new BoosterType();
        boosterType.setId(1);
        boosterType.setName("Speed");

        booster = new Booster();
        booster.setId(1);
        booster.setName("Booster1");
        booster.setActive(true);
        booster.setBoosterType(boosterType);

        boosterDTO = new BoosterDTO();
        boosterDTO.setName("Booster1");
        boosterDTO.setActive(true);
        BoosterTypeDTO boosterTypeDTO = new BoosterTypeDTO( "Speed",1 );
        boosterDTO.setBoosterType(boosterTypeDTO);
    }

    @Test
    public void testCreateBoosterSuccessfully() {
        when(boosterRepository.findByName(anyString())).thenReturn(Optional.empty());
        when(boosterTypeRepository.findByName(anyString())).thenReturn(Optional.of(boosterType));
        when(boosterMapper.boosterDTOTObooster(any(BoosterDTO.class))).thenReturn(booster);

        boosterService.createBooster(boosterDTO);

        verify(boosterRepository, times(1)).save(booster);
    }

    @Test
    public void testCreateBoosterExistsException() {
        when(boosterRepository.findByName(anyString())).thenReturn(Optional.of(booster));

        ExistsException exception = assertThrows(ExistsException.class, () -> {
            boosterService.createBooster(boosterDTO);
        });

        assertEquals("Booster Name already exists.", exception.getMessage());
    }

    @Test
    public void testDeleteBoosterSuccessfully() {
        when(boosterRepository.findByName(anyString())).thenReturn(Optional.of(booster));

        boosterService.deleteBooster("Booster1");

        verify(boosterRepository, times(1)).delete(booster);
    }

    @Test
    public void testDeleteBoosterNotFound() {
        when(boosterRepository.findByName(anyString())).thenReturn(Optional.empty());

        ExistsException exception = assertThrows(ExistsException.class, () -> {
            boosterService.deleteBooster("NonExistentBooster");
        });

        assertEquals("Boost Name does not exist", exception.getMessage());
    }

    @Test
    public void testUpdateBoosterNameSuccessfully() {
        when(boosterRepository.findByName(anyString())).thenReturn(Optional.of(booster));

        boosterService.updateBoosterName("Booster1", "NewBooster1");

        verify(boosterRepository, times(1)).save(booster);
        assertEquals("NewBooster1", booster.getName());
    }

    @Test
    public void testUpdateBoosterNameNotFound() {
        when(boosterRepository.findByName(anyString())).thenReturn(Optional.empty());

        ExistsException exception = assertThrows(ExistsException.class, () -> {
            boosterService.updateBoosterName("NonExistentBooster", "NewName");
        });

        assertEquals("Booster Name does not exist", exception.getMessage());
    }

    @Test
    public void testUpdateBoosterActivitySuccessfully() {
        BoosterDTO[] boostersToUpdate = new BoosterDTO[]{
                new BoosterDTO("Booster1", false, new BoosterTypeDTO( "Speed",1)),
                new BoosterDTO("Booster2", true, new BoosterTypeDTO("Speed",1))
        };

        when(boosterRepository.findByName("Booster1")).thenReturn(Optional.of(booster));
        when(boosterRepository.findByName("Booster2")).thenReturn(Optional.of(new Booster()));

        boosterService.updateBoosterActivity(boostersToUpdate);

        verify(boosterRepository, times(1)).save(booster);
        verify(boosterRepository, times(2)).save(any(Booster.class)); 
    }

    @Test
    public void testUpdateBoosterActivityNotFound() {
        BoosterDTO[] boostersToUpdate = new BoosterDTO[]{
                new BoosterDTO("Booster1", false, new BoosterTypeDTO("Speed",1))
        };

        when(boosterRepository.findByName(anyString())).thenReturn(Optional.empty());

        ExistsException exception = assertThrows(ExistsException.class, () -> {
            boosterService.updateBoosterActivity(boostersToUpdate);
        });

        assertEquals("Booster Name does not exist", exception.getMessage());
    }

    @Test
    public void testUpdateBoosterTypeSuccessfully() {
        when(boosterRepository.findByName(anyString())).thenReturn(Optional.of(booster));
        when(boosterTypeRepository.findByName(anyString())).thenReturn(Optional.of(boosterType));

        boosterService.updateBoosterType("Booster1", "Speed");

        verify(boosterRepository, times(1)).save(booster);
        assertEquals(boosterType, booster.getBoosterType());
    }

    @Test
    public void testUpdateBoosterTypeNotFound() {
        when(boosterRepository.findByName(anyString())).thenReturn(Optional.empty());

        ExistsException exception = assertThrows(ExistsException.class, () -> {
            boosterService.updateBoosterType("NonExistentBooster", "Speed");
        });

        assertEquals("Booster does not exist", exception.getMessage());
    }

    @Test
    public void testGetBoosterSuccessfully() {
        when(boosterRepository.findByName(anyString())).thenReturn(Optional.of(booster));
        when(boosterMapper.boosterToBoosterDTO(any(Booster.class))).thenReturn(boosterDTO);

        BoosterDTO result = boosterService.getBooster("Booster1");

        assertNotNull(result);
        assertEquals(boosterDTO.getName(), result.getName());
    }

    @Test
    public void testGetBoosterNotFound() {
        when(boosterRepository.findByName(anyString())).thenReturn(Optional.empty());

        ExistsException exception = assertThrows(ExistsException.class, () -> {
            boosterService.getBooster("NonExistentBooster");
        });

        assertEquals("Booster does not exist", exception.getMessage());
    }

    @Test
    public void testGetAllBoosters() {
        when(boosterRepository.findAll()).thenReturn(Collections.singletonList(booster));
        when(boosterMapper.boostersListToBoosterDTOsList(any())).thenReturn(Collections.singletonList(boosterDTO));

        List<BoosterDTO> boosters = boosterService.getAllBoosters();

        assertNotNull(boosters);
        assertEquals(1, boosters.size());
        assertEquals(boosterDTO.getName(), boosters.get(0).getName());
    }
}
