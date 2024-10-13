package org.example.Repository;

import org.example.Classes.EmployeeCareerPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeCareerPackageRepository extends JpaRepository<EmployeeCareerPackage, Integer> {
    Optional<List<EmployeeCareerPackage>> findByEmployeeId(int employeeId);
}
