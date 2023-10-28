package com.ajousw.spring.web.controller.dto.comment;

import com.ajousw.spring.domain.comment.Comment;
import com.ajousw.spring.domain.member.repository.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentDto {
    private Long id;
    private Member member;
    private String commentBody;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.member = comment.getMember();
        this.commentBody = comment.getCommentBody();
        this.createdDate = comment.getCreatedDate();
        this.modifiedDate = comment.getModifiedDate();
    }

}
