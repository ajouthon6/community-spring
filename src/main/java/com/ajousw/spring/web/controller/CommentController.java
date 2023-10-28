package com.ajousw.spring.web.controller;

import com.ajousw.spring.domain.comment.CommentService;
import com.ajousw.spring.domain.member.security.UserPrinciple;
import com.ajousw.spring.web.controller.dto.comment.CommentCreateDto;
import com.ajousw.spring.web.controller.dto.comment.CommentDeleteDto;
import com.ajousw.spring.web.controller.dto.comment.CommentDto;
import com.ajousw.spring.web.controller.dto.comment.CommentUpdateDto;
import com.ajousw.spring.web.controller.json.ApiResponseJson;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comments/create")
    public ApiResponseJson createComment(@Valid @RequestBody CommentCreateDto createDto) {
        log.info("[NEW-COMMENT] : {}", createDto);

        commentService.createComment(createDto);

        return new ApiResponseJson(HttpStatus.OK, "Comment Created Successfully");
    }

//    @GetMapping("/comments/{commentId}")
//    public ApiResponseJson getComment(@PathVariable Long commentId) {
//        Comment comment = commentService.getCommentById(commentId);
//
//        return new ApiResponseJson(HttpStatus.OK, new CommentDto(comment));
//    }

    @GetMapping("/comments/{boardId}")
    public ApiResponseJson getAllComments(@PathVariable Long boardId) {
        log.info("[GET-ALL_COMMENTS]");

        List<CommentDto> comments = commentService.getComments(boardId);

        log.info("{}", comments);

        return new ApiResponseJson(HttpStatus.OK, comments);
    }

    @PostMapping("/comments/update")
    public ApiResponseJson updateComment(@Valid @RequestBody CommentUpdateDto updateDto, @AuthenticationPrincipal UserPrinciple user) {
        log.info("[UPDATE-BOARD] : {}", updateDto);

        commentService.updateComment(updateDto, user.getEmail());

        return new ApiResponseJson(HttpStatus.OK, "Comment Updated Successfully");
    }

    @PostMapping("/comments/delete")
    public ApiResponseJson deleteComment(@Valid @RequestBody CommentDeleteDto deleteDto, @AuthenticationPrincipal UserPrinciple user) {
        log.info("[DELETE-COMMENT] : {}", deleteDto);

        commentService.deleteComment(deleteDto, user.getEmail());

        return new ApiResponseJson(HttpStatus.OK, "Comment Deleted Successfully");
    }


}
