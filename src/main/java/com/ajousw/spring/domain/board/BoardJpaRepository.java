package com.ajousw.spring.domain.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardJpaRepository extends JpaRepository<Board, Long> {

    @Query("select b from Board b order by b.isFinished asc, b.id desc")
    List<Board> findAll();
}
