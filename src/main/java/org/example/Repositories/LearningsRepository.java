package org.example.Repositories;

import org.example.Entities.Learnings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearningsRepository extends JpaRepository<Learnings, Integer> {
}
