package com.ajousw.spring.web.controller.dto.challenger;

import com.ajousw.spring.domain.challenger.Challenger;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChallengerDto {
    Long challengerId;
    Long challengerWriterId;  //email의 memberId. Board owne의 memberId 아님
    String email;
    String message;
    Boolean isChecked;

    public ChallengerDto(Challenger challenger) {
        this.challengerId = challenger.getId();
        this.email = challenger.getEmail();
        this.challengerWriterId = challenger.getChallengerWriterId();
        this.message = challenger.getMessage();
        this.isChecked = challenger.getIsChecked();
    }
}
