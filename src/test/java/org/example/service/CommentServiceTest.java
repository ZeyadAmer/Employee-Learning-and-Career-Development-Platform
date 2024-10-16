package org.example.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.classes.Comment;
import org.example.classes.SubmittedCareerPackage;
import org.example.exception.ExistsException;
import org.example.mapper.CommentDTO;
import org.example.mapper.CommentMapper;
import org.example.mapper.SubmittedCareerPackageDTO;
import org.example.repository.CommentRepository;
import org.example.repository.SubmittedCareerPackageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {

    @Mock
    CommentMapper commentMapper;
    @Mock
    CommentRepository commentRepository;
    @Mock
    SubmittedCareerPackageRepository submittedCareerPackageRepository;
    @InjectMocks
    CommentService commentService;

    @Test
    public void testCreateComment_Success() {
        Comment comment = new Comment();
        comment.setCommentText("Test");
        comment.setId(1);
        SubmittedCareerPackage submittedCareerPackage = new SubmittedCareerPackage();
        submittedCareerPackage.setId(1);
        comment.setSubmittedCareerPackage(submittedCareerPackage);
        Mockito.when(commentRepository.findById(1)).thenReturn(Optional.empty());
        Mockito.when(submittedCareerPackageRepository.findById(1)).thenReturn(Optional.of(submittedCareerPackage));
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setCommentText("Test");
        commentDTO.setId(1);
        SubmittedCareerPackageDTO submittedCareerPackageDTO = new SubmittedCareerPackageDTO();
        submittedCareerPackageDTO.setId(1);
        commentDTO.setSubmittedCareerPackage(submittedCareerPackageDTO);
        Mockito.when(commentMapper.commentDTOToComment(commentDTO)).thenReturn(comment);
        commentService.createComment(commentDTO);
        Mockito.verify(commentRepository, Mockito.times(1)).findById(1);
        Mockito.verify(submittedCareerPackageRepository, Mockito.times(1)).findById(1);
    }

    @Test
    public void testCreateComment_CommentAlreadyExists(){
        Comment comment = new Comment();
        comment.setId(1);
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(1);
        Mockito.when(commentRepository.findById(1)).thenReturn(Optional.of(comment));
        assertThrows(ExistsException.class, () -> commentService.createComment(commentDTO));
    }

    @Test
    public void testCreateComment_SubmittedCareerPackageNotFound(){
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(1);
        SubmittedCareerPackageDTO submittedCareerPackageDTO = new SubmittedCareerPackageDTO();
        submittedCareerPackageDTO.setId(1);
        commentDTO.setSubmittedCareerPackage(submittedCareerPackageDTO);
        Mockito.when(commentRepository.findById(1)).thenReturn(Optional.empty());
        Mockito.when(submittedCareerPackageRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(ExistsException.class, () -> commentService.createComment(commentDTO));
    }

    @Test
    public void testGetComment_Success() throws JsonProcessingException {
        Comment comment = new Comment();
        comment.setId(1);
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(1);
        Mockito.when(commentRepository.findById(1)).thenReturn(Optional.of(comment));
        Mockito.when(commentMapper.commentToCommentDTO(comment)).thenReturn(commentDTO);
        CommentDTO result = commentService.getComment(1);
        assertEquals(1, result.getId());
    }

    @Test
    public void testGetComment_NotFound(){
        Mockito.when(commentRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(ExistsException.class, () -> commentService.getComment(1));
    }

    @Test
    public void testGetAllComments_Success() {
        SubmittedCareerPackage submittedCareerPackage = new SubmittedCareerPackage();
        submittedCareerPackage.setId(1);

        List<Comment> comments = new ArrayList<>();
        Comment comment = new Comment();
        comment.setId(1);
        comment.setSubmittedCareerPackage(submittedCareerPackage);
        comments.add(comment);

        SubmittedCareerPackageDTO submittedCareerPackageDTO = new SubmittedCareerPackageDTO();
        submittedCareerPackageDTO.setId(1);

        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(1);
        commentDTO.setSubmittedCareerPackage(submittedCareerPackageDTO);
        List<CommentDTO> commentDTOS = new ArrayList<>();
        commentDTOS.add(commentDTO);

        Mockito.when(submittedCareerPackageRepository.findById(1))
                .thenReturn(Optional.of(submittedCareerPackage));
        Mockito.when(commentRepository.findBySubmittedCareerPackage(submittedCareerPackage))
                .thenReturn(comments);
        Mockito.when(commentMapper.commentListToCommentDTO(comments))
                .thenReturn(commentDTOS);

        List<CommentDTO> result = commentService.getAllComments(1);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getId());
        Mockito.verify(submittedCareerPackageRepository, Mockito.times(1)).findById(1);
        Mockito.verify(commentRepository, Mockito.times(1)).findBySubmittedCareerPackage(submittedCareerPackage);
    }

}
