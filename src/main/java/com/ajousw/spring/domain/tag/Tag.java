package com.ajousw.spring.domain.tag;

import com.ajousw.spring.domain.member.repository.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long id;

    @Column(unique = true)
    private String tagName;

    private Long tagRefCount;

    private LocalDateTime lastAccessTime;

    @Builder
    public Tag(String tagName) {
        this.tagName = tagName;
        this.tagRefCount = 1L;
        this.lastAccessTime = LocalDateTime.now();
    }

    public void recordTagAccess() {
        this.tagRefCount++;
        this.lastAccessTime = LocalDateTime.now();
    }


}
