package com.ajousw.spring.domain.presentation;

import com.ajousw.spring.domain.member.repository.Member;
import com.ajousw.spring.domain.member.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PresentationService {
    private final PresentationJpaRepository presentationJpaRepository;
    private final MemberJpaRepository memberJpaRepository;

    public void createPresentation(String presentation, String email) {
        Member member = memberJpaRepository.findByEmail(email).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 유저입니다."));


    }
}
