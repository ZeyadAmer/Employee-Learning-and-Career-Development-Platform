package org.example.Repository;

import org.example.Entities.EntitiesType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntitiesTypeRepository extends JpaRepository<EntitiesType, String> {
}
