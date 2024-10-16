package org.example.mapper;

import org.example.classes.Comment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentDTO commentToCommentDTO(Comment comment);
    Comment commentDTOToComment(CommentDTO commentDTO);
    List<CommentDTO> commentListToCommentDTO(List<Comment> comments);
    List<Comment> commentDTOListToCommentList(List<CommentDTO> commentDTOS);
}
