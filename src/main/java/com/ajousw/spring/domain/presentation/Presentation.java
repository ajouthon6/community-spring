package com.ajousw.spring.domain.presentation;

import com.ajousw.spring.domain.member.repository.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
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

    @Column(unique = true)
    private String email;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String body;

    @Builder
    public Presentation(String email, String body) {
        this.email = email;
        this.body = body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
