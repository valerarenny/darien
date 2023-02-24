package com.test.darien.repositories;

import com.test.darien.entities.Player;
import com.test.darien.entities.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Optional<Team> findByName(String teamName);
    Page<Team> findAll(Specification<Team> spec, Pageable pageable);
}
