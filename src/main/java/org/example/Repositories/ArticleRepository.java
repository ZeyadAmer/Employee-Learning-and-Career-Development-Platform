package org.example.Repositories;

import org.example.Entities.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
    @Query("SELECT a FROM Article a WHERE a.approvalStatus = 'APPROVED' ORDER BY a.id DESC ")
    Page<Article> findAllTitleApproved(Pageable pageable); // Return a Page instead of List
    @Query("SELECT a FROM Article a WHERE a.approvalStatus = 'PENDING' ORDER BY a.id ASC")
    Page<Article> findAllTitlePending(Pageable pageable); // Return a Page instead of List
}
