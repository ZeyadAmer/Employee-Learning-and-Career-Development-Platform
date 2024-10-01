package org.example.Repository;

import org.aspectj.apache.bcel.classfile.Module;
import org.example.Entities.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActionRepository extends JpaRepository<Action, Integer> {
    Optional<Action> findByName(String name);
}
