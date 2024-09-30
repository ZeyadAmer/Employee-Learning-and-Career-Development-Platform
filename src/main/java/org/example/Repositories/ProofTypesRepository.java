package org.example.Repositories;

import org.example.Entities.ProofTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProofTypesRepository extends JpaRepository<ProofTypes, Integer> {
    Optional<ProofTypes> findByName(String name);
}
