package com.ajousw.spring.domain.comment;

import com.ajousw.spring.domain.board.Board;
import com.ajousw.spring.domain.member.repository.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTimeEntity {

    @Lob
    String commentBody;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private Long memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Builder
    public Comment(String commentBody, Long id, Long memberId, Board board) {
        this.commentBody = commentBody;
        this.id = id;
        this.memberId = memberId;
        this.board = board;
    }

    public void updateCommentBody(String newCommentBody) {
        this.commentBody = newCommentBody;
    }
}
