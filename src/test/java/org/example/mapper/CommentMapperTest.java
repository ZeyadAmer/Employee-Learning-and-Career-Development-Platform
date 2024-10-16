package org.example.mapper;

import org.example.classes.Comment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CommentMapperTest {

    @InjectMocks
    private CommentMapper commentMapper = Mappers.getMapper(CommentMapper.class);

    @Test
    public void testCommentToCommentDTO() {
        Comment comment = new Comment();
        comment.setId(1);
        comment.setCommentText("Test comment");

        CommentDTO commentDTO = commentMapper.commentToCommentDTO(comment);
        assertEquals(comment.getCommentText(), commentDTO.getCommentText());
    }

    @Test
    public void testCommentDTOToComment() {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(1);
        commentDTO.setCommentText("Test comment DTO");

        Comment comment = commentMapper.commentDTOToComment(commentDTO);
        assertEquals(commentDTO.getCommentText(), comment.getCommentText());
        assertEquals(commentDTO.getId(), comment.getId());
    }

    @Test
    public void testCommentListToCommentDTO() {
        Comment comment1 = new Comment();
        comment1.setId(1);
        comment1.setCommentText("First comment");

        Comment comment2 = new Comment();
        comment2.setId(2);
        comment2.setCommentText("Second comment");

        List<Comment> comments = Arrays.asList(comment1, comment2);
        List<CommentDTO> commentDTOs = commentMapper.commentListToCommentDTO(comments);

        assertEquals(comments.size(), commentDTOs.size());
        assertEquals(comments.get(0).getCommentText(), commentDTOs.get(0).getCommentText());
        assertEquals(comments.get(1).getCommentText(), commentDTOs.get(1).getCommentText());
    }

    @Test
    public void testCommentDTOListToCommentList() {
        CommentDTO commentDTO1 = new CommentDTO();
        commentDTO1.setId(1);
        commentDTO1.setCommentText("First comment DTO");

        CommentDTO commentDTO2 = new CommentDTO();
        commentDTO2.setId(2);
        commentDTO2.setCommentText("Second comment DTO");

        List<CommentDTO> commentDTOS = Arrays.asList(commentDTO1, commentDTO2);
        List<Comment> comments = commentMapper.commentDTOListToCommentList(commentDTOS);

        assertEquals(commentDTOS.size(), comments.size());
        assertEquals(commentDTOS.get(0).getCommentText(), comments.get(0).getCommentText());
        assertEquals(commentDTOS.get(1).getCommentText(), comments.get(1).getCommentText());
    }
}
