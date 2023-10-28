package com.ajousw.spring.domain.presentation;

import com.ajousw.spring.domain.member.repository.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Presentation extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "presentation_id")
    private Long id;

    private String email;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String body;

    public Presentation(String email, String body) {
        this.email = email;
        this.body = body;
    }
}
