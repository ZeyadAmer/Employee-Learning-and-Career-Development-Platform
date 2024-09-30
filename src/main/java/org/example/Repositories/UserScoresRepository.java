package org.example.Repositories;

import org.example.Entities.UserScores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserScoresRepository extends JpaRepository<UserScores, Integer> {
}
