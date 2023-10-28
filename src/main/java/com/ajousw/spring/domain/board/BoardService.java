package com.ajousw.spring.domain.board;

import com.ajousw.spring.domain.comment.CommentJpaRepository;
import com.ajousw.spring.domain.member.repository.Member;
import com.ajousw.spring.domain.member.repository.MemberJpaRepository;
import com.ajousw.spring.web.controller.dto.board.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardJpaRepository boardJpaRepository;
    private final MemberJpaRepository memberJpaRepository;
    private final BoardEntityMangerRepository boardEntityMangerRepository;
    private final CommentJpaRepository commentJpaRepository;

    public void createBoard(BoardCreateDto boardCreateDto) {
        Member foundMember = memberJpaRepository.findById(boardCreateDto.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저 ID 입니다.")
                );

        String concatTag = String.join(",", boardCreateDto.getTags());

        Board newBoard = Board.builder()
                .title(boardCreateDto.getTitle())
                .body(boardCreateDto.getBody())
                .member(foundMember)
                .tag(concatTag)
                .dueDate(boardCreateDto.getDueDate())
                .viewCount(0L)
                .build();

        foundMember.addBoard(newBoard);

        boardJpaRepository.save(newBoard);
    }

    public List<BoardDto> getBoards() {
        List<Board> allBoards = boardJpaRepository.findAll();

        return allBoards.stream().map(this::createBoardDto
        ).collect(Collectors.toList());
    }

    public BoardDto getBoardById(Long id) {
        Board foundBoard = boardJpaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글 ID 입니다."));

        foundBoard.addViewCount();

        return createBoardDto(foundBoard);
    }

    // Update
    public void updateBoard(BoardUpdateDto boardUpdateDto, String userEmail) {
        Board foundBoard = boardJpaRepository.findById(boardUpdateDto.getBoardId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글 ID 입니다."));

        isOwner(userEmail, foundBoard);

        String concatTag = String.join(",", boardUpdateDto.getTags());

        foundBoard.setTitle(boardUpdateDto.getTitle());
        foundBoard.setBody(boardUpdateDto.getBody());
        foundBoard.setTag(concatTag);
        foundBoard.setDueDate(boardUpdateDto.getDueDate());
    }

    // Delete
    public void deleteBoard(BoardDeleteDto deleteDto, String userEmail) {
        Board foundBoard = boardJpaRepository.findById(deleteDto.getBoardId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글 ID 입니다.")
                );

        isOwner(userEmail, foundBoard);

        commentJpaRepository.deleteAllByBoardId(foundBoard);
        boardJpaRepository.delete(foundBoard);
    }

    public void setBoardFinished(BoardFinishDto finishDto, String userEmail) {
        Board foundBoard = boardJpaRepository.findById(finishDto.getBoardId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글 ID 입니다.")
                );

        isOwner(userEmail, foundBoard);

        foundBoard.setFinished(true);
    }

    public List<BoardDto> findBoardByTags(List<String> tags) {
        List<Board> allBoards = boardEntityMangerRepository.findBoardsWithTags(tags);

        return allBoards.stream().map((board) ->
                createBoardDto(board)
        ).collect(Collectors.toList());
    }

    private BoardDto createBoardDto(Board board) {
        return new BoardDto(board.getId(),
                board.getMember().getId(),
                board.getTitle(),
                board.getBody(),
                Arrays.stream(board.getTag().split(",")).toList(),
                board.getDueDate(),
                board.isFinished(),
                board.getViewCount());
    }

    private void isOwner(String userEmail, Board foundBoard) {
        String email = foundBoard.getMember().getEmail();
        if (!email.equals(userEmail)) {
            throw new IllegalArgumentException("게시글은 본인만 수정할 수 있습니다.");
        }
    }
}
