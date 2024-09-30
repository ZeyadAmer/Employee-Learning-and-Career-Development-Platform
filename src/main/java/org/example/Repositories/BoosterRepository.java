package org.example.Repositories;

import org.example.Entities.Booster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoosterRepository extends JpaRepository<Booster, Integer> {
    Optional<Booster> findByName(String name);
}
