package com.poogie.sns.common.security;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JwtDto {
    private String accessJwt;
}
