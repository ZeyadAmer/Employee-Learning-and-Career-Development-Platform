package org.example.UserScore;

import org.example.Entities.UserScores;
import org.example.Exceptions.ExistsException;
import org.example.Mappers.UserScoresDTO;
import org.example.Mappers.UserScoresMapper;
import org.example.Repositories.UserScoresRepository;
import org.example.Services.UserScoresService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class UserScoresServiceTest {
    @Mock
    private UserScoresMapper userScoresMapper;

    @Mock
    private UserScoresRepository userScoresRepository;

    @InjectMocks
    private UserScoresService userScoresService;


    @Test
    void createUserScore_ShouldSaveUserScore_WhenNotExists() {
        UserScoresDTO userScoresDTO = new UserScoresDTO(1, 100);
        UserScores userScores = new UserScores();
        userScores.setId(userScoresDTO.getId());
        userScores.setScore(userScoresDTO.getScore());

        when(userScoresRepository.findById(userScoresDTO.getId())).thenReturn(Optional.empty());
        when(userScoresMapper.userScoresDTOToUserScores(userScoresDTO)).thenReturn(userScores);

        userScoresService.createUserScore(userScoresDTO);

        verify(userScoresRepository, times(1)).save(userScores);
    }

    @Test
    void createUserScore_ShouldThrowExistsException_WhenUserScoreExists() {
        UserScoresDTO userScoresDTO = new UserScoresDTO(1, 100);
        when(userScoresRepository.findById(userScoresDTO.getId())).thenReturn(Optional.of(new UserScores()));

        assertThrows(ExistsException.class, () -> userScoresService.createUserScore(userScoresDTO));
        verify(userScoresRepository, never()).save(any());
    }

    @Test
    void deleteUserScore_ShouldDeleteUserScore_WhenExists() {
        UserScores userScores = new UserScores();
        userScores.setId(1);
        when(userScoresRepository.findById(1)).thenReturn(Optional.of(userScores));

        userScoresService.deleteUserScore(1);

        verify(userScoresRepository, times(1)).delete(userScores);
    }

    @Test
    void deleteUserScore_ShouldThrowExistsException_WhenNotExists() {
        when(userScoresRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ExistsException.class, () -> userScoresService.deleteUserScore(1));
        verify(userScoresRepository, never()).delete(any());
    }

    @Test
    void updateUserScore_ShouldUpdateScore_WhenExists() {
        UserScores userScores = new UserScores();
        userScores.setId(1);
        userScores.setScore(50);
        when(userScoresRepository.findById(1)).thenReturn(Optional.of(userScores));

        userScoresService.updateUserScore(1, 100);

        verify(userScoresRepository, times(1)).save(userScores);
        assertEquals(100, userScores.getScore());
    }

    @Test
    void updateUserScore_ShouldThrowExistsException_WhenNotExists() {
        when(userScoresRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ExistsException.class, () -> userScoresService.updateUserScore(1, 100));
    }

}
