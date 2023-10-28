package com.ajousw.spring.domain.challenger;

import com.ajousw.spring.domain.board.Board;
import com.ajousw.spring.domain.board.BoardJpaRepository;
import com.ajousw.spring.domain.member.repository.MemberJpaRepository;
import com.ajousw.spring.web.controller.dto.challenger.ChallengerCreateDto;
import com.ajousw.spring.web.controller.dto.challenger.ChallengerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ChallengerService {
    private final ChallengerJpaRepository challengerJpaRepository;
    private final BoardJpaRepository boardJpaRepository;
    private final MemberJpaRepository memberJpaRepository;

    public void createChallenger(ChallengerCreateDto challengerCreateDto, String userEmail) {
        Board foundBoard = boardJpaRepository.findById(challengerCreateDto.getBoardId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글 ID 입니다."));

        if (!memberJpaRepository.existsByEmail(userEmail)) {
            throw new IllegalArgumentException("존재하지 않는 유저 ID 입니다.");
        }

        Challenger newChallenger = Challenger.builder()
                .board(foundBoard)
                .memberId(foundBoard.getMember().getId())
                .message(challengerCreateDto.getMessage())
                .email(userEmail)
                .build();

        challengerJpaRepository.save(newChallenger);
    }

    public List<ChallengerDto> getChallengers(Long memberId) {
        List<Challenger> challengers = challengerJpaRepository.findByMemberId(memberId);

        return challengers.stream().map((challenger) ->
                createChallengerDto(challenger)
        ).collect(Collectors.toList());
    }

    public void updateChallengerStatus(Long challengerId) {
        Challenger foundChallenger = challengerJpaRepository.findById(challengerId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 challenger ID 입니다."));

        foundChallenger.setIsChecked(true);
    }

    private ChallengerDto createChallengerDto(Challenger challenger) {
        return new ChallengerDto(challenger);
    }

}
