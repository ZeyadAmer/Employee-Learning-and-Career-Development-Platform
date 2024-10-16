package org.example.UserScore;

import org.example.Controllers.UserScoresController;
import org.example.Mappers.UserScoresDTO;
import org.example.Services.UserScoresService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;



import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@ExtendWith(MockitoExtension.class)
class UserScoresControllerTest {
    @Mock
    private UserScoresService userScoresService;

    @InjectMocks
    private UserScoresController userScoresController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userScoresController).build();
    }

    @Test
    void deleteUserScore_Successful() throws Exception {
        int id = 1;

        mockMvc.perform(delete("/userScores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(id)))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"response\":\"User Score deleted.\"}"));

        verify(userScoresService, times(1)).deleteUserScore(id);
    }

    @Test
    void updateUserScore_Successful() throws Exception {
        int id = 1;
        int newScore = 150;

        mockMvc.perform(put("/userScores/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(newScore)))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"response\":\"User Score updated.\"}"));

        verify(userScoresService, times(1)).updateUserScore(id, newScore);
    }

    @Test
    void getUserScore_Successful() throws Exception {
        int id = 1;
        UserScoresDTO userScoresDTO = new UserScoresDTO(id, 100);
        when(userScoresService.getUserScore(id)).thenReturn(userScoresDTO);

        mockMvc.perform(get("/userScores/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"id\":1,\"score\":100}"));

        verify(userScoresService, times(1)).getUserScore(id);
    }

    @Test
    void getAllUserScores_Successful() throws Exception {
        // Create a list of UserScoresDTOs to return
        List<UserScoresDTO> userScoresList = Arrays.asList(new UserScoresDTO(1, 100), new UserScoresDTO(2, 150));
        when(userScoresService.getAllUserScores()).thenReturn(userScoresList);

        mockMvc.perform(get("/userScores/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[{\"id\":1,\"score\":100},{\"id\":2,\"score\":150}]"));

        verify(userScoresService, times(1)).getAllUserScores();
    }

    @Test
    void addUserScore_Successful() throws Exception {
        int userId = 1;
        int score = 50;

        mockMvc.perform(put("/userScores/add")
                        .param("userId", String.valueOf(userId))
                        .param("score", String.valueOf(score)))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"response\":\"User Score added.\"}"));

        verify(userScoresService, times(1)).addUserScore(userId, score);
    }
}
