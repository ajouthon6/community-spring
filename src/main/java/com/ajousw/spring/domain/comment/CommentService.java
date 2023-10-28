package com.ajousw.spring.domain.comment;

import com.ajousw.spring.domain.board.Board;
import com.ajousw.spring.domain.board.BoardJpaRepository;
import com.ajousw.spring.domain.member.repository.Member;
import com.ajousw.spring.domain.member.repository.MemberJpaRepository;
import com.ajousw.spring.web.controller.dto.CommentCreateDto;
import com.ajousw.spring.web.controller.dto.CommentDeleteDto;
import com.ajousw.spring.web.controller.dto.CommentDto;
import com.ajousw.spring.web.controller.dto.CommentUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
    private final BoardJpaRepository boardJpaRepository;
    private final CommentJpaRepository commentJpaRepository;
    private final MemberJpaRepository memberJpaRepository;


    private void isOwner(String userEmail, String email) {
        if (!email.equals(userEmail)) {
            throw new IllegalArgumentException("댓글은 본인만 수정 및 삭제할 수 있습니다.");
        }
    }

    public void createComment(CommentCreateDto commentCreateDto) {
        Board foundBoard = boardJpaRepository.findById(commentCreateDto.getBoardId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글 ID 입니다."));

        Member foundMember = memberJpaRepository.findById(commentCreateDto.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저 ID 입니다."));

        Comment newComment = Comment.builder()
                .commentBody(commentCreateDto.getCommentBody())
                .member(foundMember)
                .board(foundBoard)
                .build();

        foundBoard.addComment(newComment);

        commentJpaRepository.save(newComment);
    }

    @Transactional(readOnly = true)
    public List<CommentDto> getComments(Long boardId) {
        List<Comment> allComments = commentJpaRepository.findAll();

        return allComments.stream().map((comment) ->
                createCommentDto(comment)
        ).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CommentDto getCommentById(Long commentId) {
        Comment foundComment = commentJpaRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글 ID 입니다."));

        return createCommentDto(foundComment);
    }

    public void updateComment(CommentUpdateDto commentUpdateDto, String userEmail) {
        Comment foundComment = commentJpaRepository.findById(commentUpdateDto.getCommentId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글 ID 입니다."));

        String email = foundComment.getMember().getEmail();
        isOwner(userEmail, email);

        foundComment.updateCommentBody(commentUpdateDto.getCommentBody());
    }

    public void deleteComment(CommentDeleteDto deleteDto, String userEmail) {
        Comment foundComment = commentJpaRepository.findById(deleteDto.getCommentId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글 ID 입니다."));

        String email = foundComment.getMember().getEmail();
        isOwner(userEmail, email);

        commentJpaRepository.delete(foundComment);
    }

    private CommentDto createCommentDto(Comment comment) {
        return new CommentDto(comment);
    }
}
