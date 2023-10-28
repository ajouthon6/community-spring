package com.ajousw.spring.domain.challenger;


import com.ajousw.spring.domain.board.Board;
import com.ajousw.spring.domain.member.repository.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Challenger extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "challenger_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    private Long memberId;

    private String message;

    private String email;

    private Boolean isChecked;

    @Builder
    public Challenger(Long id, Board board, Long memberId, String message, String email) {
        this.id = id;
        this.board = board;
        this.memberId = memberId;
        this.message = message;
        this.email = email;
        this.isChecked = false;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }
}
