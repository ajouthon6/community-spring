package com.ajousw.spring.domain.board;

import com.ajousw.spring.domain.member.repository.BaseTimeEntity;
import com.ajousw.spring.domain.member.repository.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(length = 255)
    private String title;

    @Lob
    private String body;

    @Column(length = 1000)
    private String tag;

    private Long viewCount;

    @Builder
    public Board(Member member, String title, String body, String tag, Long viewCount) {
        this.member = member;
        this.title = title;
        this.body = body;
        this.tag = tag;
        this.viewCount = viewCount;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
