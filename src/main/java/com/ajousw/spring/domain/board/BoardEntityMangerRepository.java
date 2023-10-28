package com.ajousw.spring.domain.board;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BoardEntityMangerRepository {

    private final EntityManager entityManager;

    public List<Board> findBoardsWithTags(List<String> tags) {
        String query = "SELECT b FROM Board b WHERE ";
        for (int i = 0; i < tags.size(); i++) {
            query += "b.tag LIKE '%" + tags.get(i) + "%' ";
            if (i < tags.size() - 1) {
                query += "AND ";
            }
        }
        query += " order by b.isFinished asc, b.id desc";
        log.info("Query {}", query);

        return entityManager.createQuery(query, Board.class).getResultList();
    }
}
