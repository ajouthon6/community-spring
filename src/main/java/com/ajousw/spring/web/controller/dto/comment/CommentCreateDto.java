package com.ajousw.spring.web.controller.dto.comment;

import com.ajousw.spring.domain.member.repository.Member;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentCreateDto {

    private Long boardId;

    private Member member;

    @NotEmpty
    private String commentBody;
}
