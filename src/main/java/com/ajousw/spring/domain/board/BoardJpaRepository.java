package com.ajousw.spring.domain.board;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface BoardJpaRepository extends JpaRepository<Board, Long> {

    @Query("select b from Board b order by b.isFinished asc, b.id desc")
    List<Board> findAll();

    @Modifying(clearAutomatically = true)
    @Query("update Board set isFinished=true where dueDate < :now")
    void finishAllExpiredBoard(@Param("now") Date expireDate);
}
