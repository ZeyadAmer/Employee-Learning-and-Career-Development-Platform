package org.example.Booster;

import org.example.Controllers.BoosterController;
import org.example.Mappers.BoosterDTO;
import org.example.Services.BoosterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class BoosterControllerTests {

    private MockMvc mockMvc;

    @Mock
    private BoosterService boosterService;

    @InjectMocks
    private BoosterController boosterController;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(boosterController).build();
    }

    @Test
    public void testCreateBooster() throws Exception {
        BoosterDTO boosterDTO = new BoosterDTO("Booster1", true, null);

        mockMvc.perform(post("/boosters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Booster1\", \"active\":true, \"boosterType\":null}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"Response\":\"Booster created\"}"));

        verify(boosterService, times(1)).createBooster(any(BoosterDTO.class));
    }

    @Test
    public void testDeleteBooster() throws Exception {
        String boosterName = "Booster1";

        mockMvc.perform(delete("/boosters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(boosterName)) // Directly use boosterName without additional quotes
                .andExpect(status().isOk())
                .andExpect(content().string("Booster deleted"));

        verify(boosterService, times(1)).deleteBooster(boosterName);
    }


    @Test
    public void testUpdateBoosterName() throws Exception {
        String oldName = "OldBooster";
        String newName = "NewBooster";

        mockMvc.perform(put("/boosters/updateName/{oldName}", oldName)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newName))
                .andExpect(status().isOk())
                .andExpect(content().string("Booster name updated."));

        verify(boosterService, times(1)).updateBoosterName(oldName, newName);
    }


    @Test
    public void testUpdateBoosterActivity() throws Exception {
        BoosterDTO[] boosterDTOS = new BoosterDTO[]{
                new BoosterDTO("Booster1", true, null),
                new BoosterDTO("Booster2", false, null)
        };

        mockMvc.perform(put("/boosters/updateBoosterActivity")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[{\"name\":\"Booster1\", \"active\":true, \"boosterType\":null}," +
                                "{\"name\":\"Booster2\", \"active\":false, \"boosterType\":null}]"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"updateBoosterActivity\":\"Booster activity updated\"}"));

        verify(boosterService, times(1)).updateBoosterActivity(any(BoosterDTO[].class));
    }

    @Test
    public void testUpdateBoosterType() throws Exception {
        String boosterName = "Booster1";
        String boosterTypeName = "Speed";

        mockMvc.perform(put("/boosters/updateBoosterType/{boosterTypeName}", boosterTypeName)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(boosterName))
                .andExpect(status().isOk())
                .andExpect(content().string("Booster Type updated"));

        verify(boosterService, times(1)).updateBoosterType(boosterName, boosterTypeName);
    }

    @Test
    public void testGetBooster() throws Exception {
        String boosterName = "Booster1";
        BoosterDTO boosterDTO = new BoosterDTO(boosterName, true, null);

        // Set up the mock to return this BoosterDTO when called with boosterName
        when(boosterService.getBooster(boosterName)).thenReturn(boosterDTO);

        mockMvc.perform(get("/boosters/{name}", boosterName))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"name\":\"Booster1\", \"active\":true, \"boosterType\":null}")); // Ensure this matches the DTO structure

        verify(boosterService, times(1)).getBooster(boosterName);
    }


    @Test
    public void testGetAllBoosters() throws Exception {
        BoosterDTO boosterDTO1 = new BoosterDTO("Booster1", true, null);
        BoosterDTO boosterDTO2 = new BoosterDTO("Booster2", false, null);

        when(boosterService.getAllBoosters()).thenReturn(List.of(boosterDTO1, boosterDTO2));

        mockMvc.perform(get("/boosters/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[{\"name\":\"Booster1\", \"active\":true, \"boosterType\":null}," +
                        "{\"name\":\"Booster2\", \"active\":false, \"boosterType\":null}]"));

        verify(boosterService, times(1)).getAllBoosters();
    }

}
