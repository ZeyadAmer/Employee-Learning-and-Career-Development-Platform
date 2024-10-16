package org.example.repository;

import org.example.classes.Comment;
import org.example.classes.SubmittedCareerPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findBySubmittedCareerPackage(SubmittedCareerPackage submittedCareerPackage);
}
