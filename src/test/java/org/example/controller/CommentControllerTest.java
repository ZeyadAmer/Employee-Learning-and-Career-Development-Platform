package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.mapper.CommentDTO;
import org.example.service.CommentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CommentControllerTest {

    @Mock
    private CommentService commentService;
    @InjectMocks
    private CommentController commentController;

    @Test
    public void testCreateComment(){
        CommentDTO commentDTO = new CommentDTO();
        Mockito.doNothing().when(commentService).createComment(commentDTO);
        ResponseEntity<String> response = commentController.createComment(commentDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("{\"message\": \"Comment created.\"}", response.getBody());

        Mockito.verify(commentService, Mockito.times(1))
                .createComment(commentDTO);
    }

    @Test
    public void testGetComment() throws JsonProcessingException {
        CommentDTO mockComment = new CommentDTO();
        Mockito.when(commentService.getComment(1)).thenReturn(mockComment);

        ResponseEntity<CommentDTO> response = commentController.getComment(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockComment, response.getBody());

        Mockito.verify(commentService, Mockito.times(1)).getComment(1);
    }

    @Test
    public void testGetAllComments(){
        List<CommentDTO> mockComments = List.of(new CommentDTO(), new CommentDTO()); // Populate with test data
        Mockito.when(commentService.getAllComments(1)).thenReturn(mockComments);

        ResponseEntity<List<CommentDTO>> response = commentController.getAllComments(1);

        // Assert the response
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockComments, response.getBody());

        Mockito.verify(commentService, Mockito.times(1)).getAllComments(1);
    }
}
