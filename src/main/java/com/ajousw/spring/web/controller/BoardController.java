package com.ajousw.spring.web.controller;

import com.ajousw.spring.domain.board.BoardService;
import com.ajousw.spring.web.controller.dto.BoardCreateDto;
import com.ajousw.spring.web.controller.dto.BoardDeleteDto;
import com.ajousw.spring.web.controller.dto.BoardDto;
import com.ajousw.spring.web.controller.dto.BoardUpdateDto;
import com.ajousw.spring.web.controller.json.ApiResponseJson;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public ApiResponseJson updateBoard(@Valid @RequestBody BoardUpdateDto updateDto) {
        log.info("[UPDATE-BOARD] : {}", updateDto);

        boardService.updateBoard(updateDto);

        return new ApiResponseJson(HttpStatus.OK, "Update Success");
    }

    @PostMapping("/board/delete")
    public ApiResponseJson deleteBoard(@Valid @RequestBody BoardDeleteDto deleteDto) {
        log.info("[DELETE-BOARD] : {}", deleteDto);

        boardService.deleteBoard(deleteDto);

        return new ApiResponseJson(HttpStatus.OK, "Delete Success");
    }

    @GetMapping("/board/all")
    public ApiResponseJson getAllBoards() {
        log.info("[GET-ALL_BOARD]");

        List<BoardDto> boards = boardService.getBoards();

        log.info("{}", boards);

        return new ApiResponseJson(HttpStatus.OK, boards);
    }

    @GetMapping("/board/{boardId}")
    public ApiResponseJson getBoardById(@PathVariable String boardId) {
        log.info("[GET-BOARD] id={}", boardId);

        if (!isNumber(boardId)) {
            throw new NumberFormatException();
        }

        BoardDto boardById = boardService.getBoardById(Long.valueOf(boardId));

        return new ApiResponseJson(HttpStatus.OK, boardById);
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
