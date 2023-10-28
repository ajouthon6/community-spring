package com.ajousw.spring.web.controller;

import com.ajousw.spring.domain.challenger.ChallengerService;
import com.ajousw.spring.domain.member.security.UserPrinciple;
import com.ajousw.spring.web.controller.dto.challenger.ChallengerCreateDto;
import com.ajousw.spring.web.controller.dto.challenger.ChallengerDto;
import com.ajousw.spring.web.controller.json.ApiResponseJson;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ChallengerController {
    private final ChallengerService challengerService;

    @PostMapping("/challenger/create")
    public ApiResponseJson createChallenger(@Valid @RequestBody ChallengerCreateDto createDto, @AuthenticationPrincipal UserPrinciple user) {
        log.info("[NEW-CHALLENGER] : {}", createDto);

        challengerService.createChallenger(createDto, user.getEmail());

        return new ApiResponseJson(HttpStatus.OK, "Challenger Created Successfully");
    }

    @GetMapping("/challenger/list/{memberId}")
    public ApiResponseJson getChallengers(@PathVariable Long memberId) {
        log.info("[GET-ALL-CHALLENGERS");

        List<ChallengerDto> challengers = challengerService.getChallengers(memberId);

        log.info("{}", challengers);

        return new ApiResponseJson(HttpStatus.OK, challengers);
    }

    @PostMapping("/challenger/updateStatus/{challengerId}")
    public ApiResponseJson UpdateChallengerStatus(@PathVariable Long challengerId) {
        log.info("[UPDATE-CHALLENGER-STATUS] : Challenger ID = {}", challengerId);

        challengerService.updateChallengerStatus(challengerId);

        return new ApiResponseJson(HttpStatus.OK, "Challenger Status Updated Successfully");
    }
}