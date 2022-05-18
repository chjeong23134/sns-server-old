package com.poogie.sns.user.dto;

import com.poogie.sns.user.domain.UserEntity;
import lombok.Getter;

public class UserDto {
    @Getter
    public static class AddReq {
        private String email;
        private String password;
        private String name;

        public UserEntity toEntity() {
            return UserEntity.builder()
                    .email(this.email)
                    .password(this.password)
                    .name(this.name)
                    .isDeleted("N")
                    .build();
        }
    }
}
