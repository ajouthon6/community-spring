package com.ajousw.spring.web.controller.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardUpdateDto {
    private Long boardId;

    @NotEmpty
    private String title;

    @NotEmpty
    private String body;

    @NotNull
    private List<String> tags = new ArrayList<>();
}
