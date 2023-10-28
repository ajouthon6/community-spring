package com.ajousw.spring.web.controller.dto.challenger;

import com.ajousw.spring.domain.challenger.Challenger;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChallengerDto {
    Long challengerId;
    String email;
    String message;
    Boolean isChecked;

    public ChallengerDto(Challenger challenger) {
        this.challengerId = challenger.getId();
        this.email = challenger.getEmail();
        this.message = challenger.getMessage();
        this.isChecked = challenger.getIsChecked();
    }
}
