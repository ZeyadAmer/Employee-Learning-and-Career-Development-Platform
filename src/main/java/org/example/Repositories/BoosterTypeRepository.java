package org.example.Repositories;

import org.example.Entities.BoosterType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoosterTypeRepository extends JpaRepository<BoosterType, Integer> {
    Optional<BoosterType> findByName(String name);
}
