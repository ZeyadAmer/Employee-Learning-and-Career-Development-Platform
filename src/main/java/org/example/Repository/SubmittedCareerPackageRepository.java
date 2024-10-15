package org.example.Repository;

import org.example.Classes.SubmittedCareerPackage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubmittedCareerPackageRepository extends JpaRepository<SubmittedCareerPackage, Integer> {
    Optional<List<SubmittedCareerPackage>> findByEmployeeId(int employeeId);
    Optional<List<SubmittedCareerPackage>> findByManagerId(int managerId);
    Page<SubmittedCareerPackage> findByEmployeeId(int employeeId, Pageable pageable);
}
