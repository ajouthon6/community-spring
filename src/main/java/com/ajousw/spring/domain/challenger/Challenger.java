package com.ajousw.spring.domain.challenger;

import com.ajousw.spring.domain.board.Board;
import com.ajousw.spring.domain.member.repository.BaseTimeEntity;
import com.ajousw.spring.domain.member.repository.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Challenger extends BaseTimeEntity {


    @JoinColumn(name = "member_id")
    private Board board;

    private Member member;

    private String message;

    private Boolean isChecked;

}
