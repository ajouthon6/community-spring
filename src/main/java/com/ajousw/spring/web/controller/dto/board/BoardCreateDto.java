package com.ajousw.spring.web.controller.dto.board;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardCreateDto {

    private Long memberId;

    @NotEmpty
    private String title;

    @NotEmpty
    private String body;

    @NotNull
    private List<String> tags = new ArrayList<>();

    @NotNull
    private Date dueDate;
}
