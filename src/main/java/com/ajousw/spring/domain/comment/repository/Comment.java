package com.ajousw.spring.domain.comment.repository;

import com.ajousw.spring.domain.member.repository.BaseTimeEntity;
import com.ajousw.spring.domain.member.repository.Member;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.Id;

@Entity
@Getter
public class Comment extends BaseTimeEntity {

    String comment_body;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


}
