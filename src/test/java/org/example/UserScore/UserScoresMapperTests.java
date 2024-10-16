package org.example.UserScore;

import org.example.Entities.UserScores;
import org.example.Mappers.UserScoresDTO;
import org.example.Mappers.UserScoresMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@ExtendWith({MockitoExtension.class})
public class UserScoresMapperTests {

    private UserScoresMapper userScoresMapper;

    @BeforeEach
    void setUp() {
        userScoresMapper = Mappers.getMapper(UserScoresMapper.class);
    }

    @Test
    public void testUserScoresToUserScoresDTO() {
        UserScores userScores = new UserScores();
        userScores.setId(1);
        userScores.setScore(100);

        UserScoresDTO userScoresDTO = userScoresMapper.userScoresToUserScoresDTO(userScores);

        assertEquals(userScores.getId(), userScoresDTO.getId());
        assertEquals(userScores.getScore(), userScoresDTO.getScore());
    }

    @Test
    public void testUserScoresDTOToUserScores() {
        UserScoresDTO userScoresDTO = new UserScoresDTO(2, 200);

        UserScores userScores = userScoresMapper.userScoresDTOToUserScores(userScoresDTO);

        assertEquals(userScoresDTO.getId(), userScores.getId());
        assertEquals(userScoresDTO.getScore(), userScores.getScore());
    }

    @Test
    public void testUserScoresDTOsListToUserScoresList() {
        UserScoresDTO userScoresDTO1 = new UserScoresDTO(3, 300);
        UserScoresDTO userScoresDTO2 = new UserScoresDTO(4, 400);
        List<UserScoresDTO> userScoresDTOs = Arrays.asList(userScoresDTO1, userScoresDTO2);

        List<UserScores> userScoresList = userScoresMapper.userScoresDTOsListToUserScoresList(userScoresDTOs);

        assertEquals(userScoresDTOs.size(), userScoresList.size());
        assertEquals(userScoresDTOs.get(0).getId(), userScoresList.get(0).getId());
        assertEquals(userScoresDTOs.get(0).getScore(), userScoresList.get(0).getScore());
        assertEquals(userScoresDTOs.get(1).getId(), userScoresList.get(1).getId());
        assertEquals(userScoresDTOs.get(1).getScore(), userScoresList.get(1).getScore());
    }

    @Test
    public void testUserScoresListToUserScoresDTOsList() {
        UserScores userScores1 = new UserScores();
        userScores1.setId(5);
        userScores1.setScore(500);

        UserScores userScores2 = new UserScores();
        userScores2.setId(6);
        userScores2.setScore(600);

        List<UserScores> userScoresList = Arrays.asList(userScores1, userScores2);
        List<UserScoresDTO> userScoresDTOs = userScoresMapper.userScoresListToUserScoresDTOsList(userScoresList);

        assertEquals(userScoresList.size(), userScoresDTOs.size());
        assertEquals(userScoresList.get(0).getId(), userScoresDTOs.get(0).getId());
        assertEquals(userScoresList.get(0).getScore(), userScoresDTOs.get(0).getScore());
        assertEquals(userScoresList.get(1).getId(), userScoresDTOs.get(1).getId());
        assertEquals(userScoresList.get(1).getScore(), userScoresDTOs.get(1).getScore());
    }
}
