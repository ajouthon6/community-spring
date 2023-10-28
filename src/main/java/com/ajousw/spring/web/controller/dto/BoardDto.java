package com.ajousw.spring.web.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class BoardDto {

    private Long boardId;

    private Long memberId;

    private String title;

    private String body;

    private List<String> tags = new ArrayList<>();

    private Long viewCount;
}
