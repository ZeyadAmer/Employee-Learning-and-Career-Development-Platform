package org.example.Repository;

import org.example.Classes.SubmittedCareerPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmittedCareerPackageRepository extends JpaRepository<SubmittedCareerPackage, Integer> {
}
