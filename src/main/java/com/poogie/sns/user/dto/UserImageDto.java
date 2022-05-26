package com.poogie.sns.user.dto;

import com.poogie.sns.user.domain.UserImageEntity;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserImageDto {
    private Long id;
    private Long userId;
    private String path;

    public UserImageEntity toEntity() {
        return UserImageEntity.builder()
                .id(this.id)
                .userId(this.userId)
                .path(this.path)
                .build();
    }
}
