package com.ajousw.spring.domain.presentation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PresentationJpaRepository extends JpaRepository<Presentation, Long> {

    boolean existsByEmail(String email);

    Optional<Presentation> findByEmail(String email);
    
}
