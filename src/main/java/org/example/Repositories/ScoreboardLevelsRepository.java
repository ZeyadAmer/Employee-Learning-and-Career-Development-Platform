package org.example.Repositories;

import org.example.Entities.ScoreboardLevels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScoreboardLevelsRepository extends JpaRepository<ScoreboardLevels, Integer> {
    Optional<ScoreboardLevels> findByLevelName(String levelName);

    @Query("SELECT s.levelName FROM ScoreboardLevels s WHERE :score BETWEEN s.minScore AND (s.minScore + 50 - 1)")
    String findLevelNameByMinScore(@Param("score") int score);
}
