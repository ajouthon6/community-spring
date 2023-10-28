package com.ajousw.spring.web.controller.dto;

import lombok.Data;

@Data
public class CommentCreateDto {
    private Long boardId;
    private Long memberId;
    private String commentBody;
}
