package com.ajousw.spring.web.controller;

import com.ajousw.spring.domain.comment.Comment;
import com.ajousw.spring.domain.comment.CommentService;
import com.ajousw.spring.web.controller.dto.CommentCreateDto;
import com.ajousw.spring.web.controller.dto.CommentDto;
import com.ajousw.spring.web.controller.dto.CommentUpdateDto;
import com.ajousw.spring.web.controller.json.ApiResponseJson;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comments/create")
    public ApiResponseJson createComment(@Valid @RequestBody CommentCreateDto createDto) {
        log.info("[NEW-COMMENT] : {}", createDto);

        Comment comment = commentService.createComment(createDto.getBoardId(), createDto.getMemberId(), createDto.getCommentBody());

        return new ApiResponseJson(HttpStatus.OK, "Comment Created Successfully");
    }

    @GetMapping("/comments/{commentId}")
    public ApiResponseJson getComment(@PathVariable Long commentId) {
        Comment comment = commentService.getComment(commentId);

        return new ApiResponseJson(HttpStatus.OK, new CommentDto(comment));
    }

    @PostMapping("/comments/{commentId}")
    public ApiResponseJson updateComment(@PathVariable Long commentId, @Valid @RequestBody CommentUpdateDto updateDto) {
        Comment comment = commentService.updateComment(commentId, updateDto.getCommentBody());

        return new ApiResponseJson(HttpStatus.OK, "Comment Updated Successfully");
    }


}
