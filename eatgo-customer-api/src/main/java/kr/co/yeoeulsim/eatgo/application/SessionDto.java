package kr.co.yeoeulsim.eatgo.application;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SessionDto {

    private String accessToken;
}