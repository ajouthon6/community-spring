package com.ajousw.spring.domain.comment;

import com.ajousw.spring.domain.board.Board;
import com.ajousw.spring.domain.member.repository.BaseTimeEntity;
import com.ajousw.spring.domain.member.repository.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Lob
    private String commentBody;

    @Builder
    public Comment(String commentBody, Long id, Member member, Board board) {
        this.commentBody = commentBody;
        this.id = id;
        this.member = member;
        this.board = board;
    }

    public void updateCommentBody(String newCommentBody) {
        this.commentBody = newCommentBody;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
