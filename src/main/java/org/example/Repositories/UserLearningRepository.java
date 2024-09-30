package org.example.Repositories;

import org.example.Entities.UserLearning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserLearningRepository extends JpaRepository<UserLearning, Integer> {
}
