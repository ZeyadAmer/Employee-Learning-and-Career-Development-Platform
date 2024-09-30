package org.example.Repositories;

import org.example.Entities.ScoreboardLevels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface ScoreboardLevelsRepository extends JpaRepository<ScoreboardLevels, Integer> {
    Optional<ScoreboardLevels> findByLevelName(String levelName);
}
