package org.example.Repositories;

import org.example.Entities.UserLearning;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserLearningRepository extends JpaRepository<UserLearning, Integer> {
    @Query("SELECT a FROM UserLearning a WHERE a.userId = :userId")
    List<UserLearning> findAll(@Param("userId") int userId);

    @Query("SELECT a FROM UserLearning a WHERE a.userId = :userId AND a.approvalStatus = 'PENDING'")
    List<UserLearning> findAllPending(@Param("userId") int userId);

    @Query("SELECT a FROM UserLearning a WHERE a.userId = :userId")
    Page<UserLearning> findAllByUserId(@Param("userId") int userId, Pageable pageable);

}
