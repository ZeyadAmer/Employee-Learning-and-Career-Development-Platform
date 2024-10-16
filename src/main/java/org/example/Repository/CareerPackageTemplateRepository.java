package org.example.Repository;

import org.example.Classes.CareerPackageTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CareerPackageTemplateRepository extends JpaRepository<CareerPackageTemplate, Integer> {
    Optional<CareerPackageTemplate> findByTitle(String title);
}
