package com.ajousw.spring.web.controller;

import com.ajousw.spring.domain.member.security.UserPrinciple;
import com.ajousw.spring.domain.presentation.PresentationService;
import com.ajousw.spring.web.controller.dto.presentation.CreatePresentationDto;
import com.ajousw.spring.web.controller.dto.presentation.GetPresentationDto;
import com.ajousw.spring.web.controller.dto.presentation.PresentationDto;
import com.ajousw.spring.web.controller.dto.presentation.UpdatePresentationDto;
import com.ajousw.spring.web.controller.json.ApiResponseJson;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PresentationController {

    private final PresentationService presentationService;

    @PostMapping("/presentation/create")
    public void createPresentation(@RequestBody @Valid CreatePresentationDto createPresentationDto,
                                   @AuthenticationPrincipal UserPrinciple user) {
        log.info("[CREATE-PRESENTATION]");

        presentationService.createPresentation(createPresentationDto.getBody(), user.getEmail());
    }

    @PostMapping("/presentation/update")
    public ApiResponseJson updatePresentation(@RequestBody @Valid UpdatePresentationDto createPresentationDto,
                                              @AuthenticationPrincipal UserPrinciple user) {
        log.info("[UPDATE-PRESENTATION]");

        presentationService.updatePresentation(createPresentationDto.getBody(), user.getEmail());
        return new ApiResponseJson(HttpStatus.OK, "OK");
    }

    @GetMapping("/presentation/get")
    public ApiResponseJson getPresentation(@RequestBody @Valid GetPresentationDto getPresentationDto) {
        log.info("[GET-PRESENTATION]");

        PresentationDto presentationDto = presentationService.getPresentation(getPresentationDto.getEmail());

        return new ApiResponseJson(HttpStatus.OK, presentationDto);
    }

}
