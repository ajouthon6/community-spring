package com.ajousw.spring.web.controller.dto;

import com.ajousw.spring.domain.member.enums.LoginType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberDto {
    private Long id;

    private String email;

    private String username;

    private LoginType loginType;

    private LocalDateTime createdDate;
}
