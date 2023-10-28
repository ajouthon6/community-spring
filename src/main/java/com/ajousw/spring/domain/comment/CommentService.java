package com.ajousw.spring.domain.comment;

import com.ajousw.spring.domain.board.Board;
import com.ajousw.spring.domain.board.BoardJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {
    private final BoardJpaRepository boardJpaRepository;
    private final CommentJpaRepository commentJpaRepository;


    @Transactional
    public Comment createComment(Long boardId, Long memberId, String commentBody) {
        Board board = boardJpaRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("Board not found with board id " + boardId));

        Comment comment = Comment.builder()
                .commentBody(commentBody)
                .memberId(memberId)
                .board(board)
                .build();

        return commentJpaRepository.save(comment);
    }

    @Transactional(readOnly = true)
    public List<Comment> getCommentsByBoard(Long boardId) {
        return commentJpaRepository.findByBoardId(boardId);
    }

    @Transactional(readOnly = true)
    public Comment getComment(Long commentId) {
        return commentJpaRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found with comment id " + commentId));

    }

    @Transactional
    public Comment updateComment(Long commentId, String newCommentBody) {
        Comment comment = getComment(commentId);
        comment.updateCommentBody(newCommentBody);

        return comment;
    }

    @Transactional
    public void deleteComment(Long commentId) {
        commentJpaRepository.deleteById(commentId);
    }
}
