package org.example.Repository;

import org.example.Classes.Comment;
import org.example.Classes.SubmittedCareerPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findBySubmittedCareerPackage(SubmittedCareerPackage submittedCareerPackage);
}
