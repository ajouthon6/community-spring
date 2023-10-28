package com.ajousw.spring.web.controller;

import com.ajousw.spring.domain.board.BoardService;
import com.ajousw.spring.domain.member.security.UserPrinciple;
import com.ajousw.spring.web.controller.dto.board.*;
import com.ajousw.spring.web.controller.json.ApiResponseJson;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/board/create")
    public ApiResponseJson createBoard(@Valid @RequestBody BoardCreateDto createDto) {
        log.info("[NEW-BOARD] : {}", createDto);

        boardService.createBoard(createDto);

        return new ApiResponseJson(HttpStatus.OK, "Create Success");
    }

    @PostMapping("/board/update")
    public ApiResponseJson updateBoard(@Valid @RequestBody BoardUpdateDto updateDto, @AuthenticationPrincipal UserPrinciple user) {
        log.info("[UPDATE-BOARD] : {}", updateDto);

        boardService.updateBoard(updateDto, user.getEmail());

        return new ApiResponseJson(HttpStatus.OK, "Update Success");
    }

    @PostMapping("/board/finish")
    public ApiResponseJson finishBoard(@Valid @RequestBody BoardFinishDto finishDto, @AuthenticationPrincipal UserPrinciple user) {
        log.info("[FINISH-BOARD] : {}", finishDto);

        boardService.setBoardFinished(finishDto, user.getEmail());

        return new ApiResponseJson(HttpStatus.OK, "Finish Success");
    }

    @PostMapping("/board/delete")
    public ApiResponseJson deleteBoard(@Valid @RequestBody BoardDeleteDto deleteDto, @AuthenticationPrincipal UserPrinciple user) {
        log.info("[DELETE-BOARD] : {}", deleteDto);

        boardService.deleteBoard(deleteDto, user.getEmail());

        return new ApiResponseJson(HttpStatus.OK, "Delete Success");
    }

    @GetMapping("/board/all")
    public ApiResponseJson getAllBoards() {
        log.info("[GET-ALL_BOARD]");

        List<BoardDto> boards = boardService.getBoards();

        log.info("{}", boards);

        return new ApiResponseJson(HttpStatus.OK, boards);
    }

    @GetMapping("/board/find/{boardId}")
    public ApiResponseJson getBoardById(@PathVariable String boardId) {
        log.info("[GET-BOARD] id={}", boardId);

        if (!isNumber(boardId)) {
            throw new NumberFormatException();
        }

        BoardDto boardById = boardService.getBoardById(Long.valueOf(boardId));

        return new ApiResponseJson(HttpStatus.OK, boardById);
    }

    @GetMapping("/board/tags/{tags}")
    public ApiResponseJson getBoardByTags(@PathVariable String tags) {
        log.info("[GET-BY-TAG] tags={}", tags);

        List<String> parsedTags = Arrays.stream(tags.split(",")).toList();

        if (parsedTags.size() == 0) {
            throw new IllegalArgumentException("태그 개수는 1개 이상이어야 합니다.");
        }

        List<BoardDto> boardByTags = boardService.findBoardByTags(parsedTags);

        return new ApiResponseJson(HttpStatus.OK, boardByTags);
    }

    private boolean isNumber(String boardId) {
        try {
            Integer.valueOf(boardId);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

}
