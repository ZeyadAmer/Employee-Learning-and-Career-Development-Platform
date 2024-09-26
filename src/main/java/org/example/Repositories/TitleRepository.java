package org.example.Repositories;

import org.example.Entities.Title;
import org.example.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TitleRepository extends JpaRepository<Title, Integer> {
    Optional<Title> findByName(String name);
}
