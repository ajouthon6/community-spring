package com.ajousw.spring.domain.comment;

import com.ajousw.spring.domain.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentJpaRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByBoardId(Long boardId);

    @Query("delete from Comment where board=:board")
    void deleteAllByBoardId(@Param("board") Board board);
}
