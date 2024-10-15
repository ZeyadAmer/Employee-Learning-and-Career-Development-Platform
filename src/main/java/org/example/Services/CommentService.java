package org.example.Services;

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
            throw new ExistsException("Submiited Career Package does not exist.");
        }

        Comment comment = commentMapper.commentDTOToComment(commentDTO);
        comment.setSubmittedCareerPackage(submittedCareerPackage.get());
        commentRepository.save(comment);
    }

    public CommentDTO getComment(int id){
        Optional<Comment> comment = commentRepository.findById(id);
        if(comment.isEmpty()){
            throw new ExistsException("Comment does not exist.");
        }
        return commentMapper.commentToCommentDTO(comment.get());
    }

    public List<CommentDTO> getAllComments(SubmittedCareerPackage submittedCareerPackage){
        Optional<List<Comment>> comments = commentRepository.findBySubmittedCareerPackage(submittedCareerPackage);
        return commentMapper.commentListToCommentDTO(comments.get());
    }
}
