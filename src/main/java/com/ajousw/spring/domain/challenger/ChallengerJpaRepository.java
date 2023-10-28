package com.ajousw.spring.domain.challenger;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ChallengerJpaRepository extends JpaRepository<Challenger, Long> {
    List<Challenger> findByMemberId(Long memberId);
}
