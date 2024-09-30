package org.example.Repositories;

import org.example.Entities.LearningSubjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Optional;

@Repository
public interface LearningSubjectsRepository extends JpaRepository<LearningSubjects, Integer>{
    Optional<LearningSubjects> findBySubject(String subject);
}
