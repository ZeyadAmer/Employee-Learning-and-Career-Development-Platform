package org.example.Repositories;

import org.example.Entities.LearningTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LearningTypesRepository extends JpaRepository<LearningTypes, Integer> {
    Optional<LearningTypes> findByLearningType(String learningType);
}
