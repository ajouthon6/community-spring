package com.ajousw.spring.domain.presentation;

import com.ajousw.spring.domain.member.repository.MemberJpaRepository;
import com.ajousw.spring.web.controller.dto.presentation.PresentationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PresentationService {
    private final PresentationJpaRepository presentationJpaRepository;
    private final MemberJpaRepository memberJpaRepository;

    public void createPresentation(String presentationBody, String email) {
        if (!memberJpaRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("존재하지 않는 이메일입니다.");
        }

        if (presentationJpaRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("자기 소개는 계정당 하나만 생성 가능합니다.");
        }

        Presentation newPresentation = Presentation.builder()
                .body(presentationBody)
                .email(email)
                .build();

        presentationJpaRepository.save(newPresentation);
    }

    public void updatePresentation(String presentationBody, String email) {
        Presentation presentation = presentationJpaRepository.findByEmail(email).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 게시글입니다."));

        presentation.setBody(presentationBody);
    }

    public PresentationDto getPresentation(String email) {
        Optional<Presentation> optionalPresentation = presentationJpaRepository.findByEmail(email);

        Presentation presentation = null;

        if (optionalPresentation.isEmpty()) {
            presentation = Presentation.builder()
                    .body("")
                    .email(email)
                    .build();
            presentationJpaRepository.save(presentation);
        }

        if (optionalPresentation.isPresent()) {
            presentation = optionalPresentation.get();
        }

        return new PresentationDto(presentation.getEmail(), presentation.getBody());
    }
}
