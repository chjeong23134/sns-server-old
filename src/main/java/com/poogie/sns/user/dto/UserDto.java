package com.poogie.sns.user.dto;

import com.poogie.sns.user.domain.UserEntity;
import lombok.Getter;

public class UserDto {
    @Getter
    public static class SignUpReq {
        private String email;
        private String password;
        private String name;

        public UserEntity toEntity() {
            return UserEntity.builder()
                    .email(this.email)
                    .password(this.password)
                    .name(this.name)
                    .role("ROLE_USER")
                    .isDeleted("N")
                    .build();
        }
    }

    @Getter
    public static class SignInReq {
        private String email;
        private String password;

//        public UsernamePasswordAuthenticationToken toAuthentication() {
//            return new UsernamePasswordAuthenticationToken(this.email, this.password);
//        }
    }
}
