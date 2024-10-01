package org.example.Repositories;

import org.example.Entities.BoosterFilter;
import org.example.Entities.BoosterFilterId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoosterFilterRepository extends JpaRepository<BoosterFilter, BoosterFilterId> {
}
