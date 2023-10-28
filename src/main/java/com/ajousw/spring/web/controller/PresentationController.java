package com.ajousw.spring.web.controller;

import com.ajousw.spring.domain.presentation.PresentationService;
import com.ajousw.spring.web.controller.dto.presentation.CreatePresentationDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PresentationController {

    private final PresentationService presentationService;

    @PostMapping("/presentation/create")
    public void createPresentation(@RequestBody @Valid CreatePresentationDto createPresentationDto) {
        log.info("[CREATE-PRESENTATION]");


    }
}
