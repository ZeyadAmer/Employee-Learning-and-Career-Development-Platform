package org.example.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Classes.Comment;
import org.example.Classes.SubmittedCareerPackage;
import org.example.Exceptions.ExistsException;
import org.example.Mappers.CommentDTO;
import org.example.Mappers.CommentMapper;
import org.example.Repository.CommentRepository;
import org.example.Repository.SubmittedCareerPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final SubmittedCareerPackageRepository submittedCareerPackageRepository;

    @Autowired
    public CommentService(CommentMapper commentMapper, CommentRepository commentRepository, SubmittedCareerPackageRepository submittedCareerPackageRepository) {
        this.commentMapper = commentMapper;
        this.commentRepository = commentRepository;
        this.submittedCareerPackageRepository = submittedCareerPackageRepository;
    }

    public void createComment(CommentDTO commentDTO){
        if(commentRepository.findById(commentDTO.getId()).isPresent()){
            throw new ExistsException("Comment already exists.");
        }
        Optional<SubmittedCareerPackage> submittedCareerPackage = submittedCareerPackageRepository.findById(commentDTO.getSubmittedCareerPackage().getId());
        if(submittedCareerPackage.isEmpty()){
            throw new ExistsException("Submitted Career Package does not exist.");
        }

        Comment comment = commentMapper.commentDTOToComment(commentDTO);
        comment.setSubmittedCareerPackage(submittedCareerPackage.get());
        System.out.println("submited career package id: "+ comment.getSubmittedCareerPackage().getId());
        commentRepository.save(comment);
    }

    public CommentDTO getComment(int id) throws JsonProcessingException {
        Optional<Comment> comment = commentRepository.findById(id);
        if(comment.isEmpty()){
            throw new ExistsException("Comment does not exist.");
        }
        System.out.println("Response DTO: " + new ObjectMapper().writeValueAsString(commentMapper.commentToCommentDTO(comment.get())));
        return commentMapper.commentToCommentDTO(comment.get());
    }

    public List<CommentDTO> getAllComments(int submittedCareerPackageId){
        Optional<SubmittedCareerPackage> submittedCareerPackage = submittedCareerPackageRepository.findById(submittedCareerPackageId);
        List<Comment> comments = commentRepository.findBySubmittedCareerPackage(submittedCareerPackage.get());
        System.out.println("Response size: " + comments.size());
        return commentMapper.commentListToCommentDTO(comments);
    }
}
