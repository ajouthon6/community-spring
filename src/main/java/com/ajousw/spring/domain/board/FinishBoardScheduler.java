package com.ajousw.spring.domain.board;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class FinishBoardScheduler {

    private final BoardJpaRepository boardJpaRepository;

    @Transactional
    @Scheduled(cron = "50 59 23 * * ?")
    public void setFinishedBoard() {
        log.info("[ 만료된 Board Finish 처리 ]");
        boardJpaRepository.finishAllExpiredBoard(new Date());
    }

}
