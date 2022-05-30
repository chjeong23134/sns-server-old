package com.poogie.sns.room.dto;

import com.poogie.sns.room.domain.RoomEntity;
import lombok.Getter;

public class RoomDto {
    @Getter
    public static class CreateReq {
        private Long createUserId;
        private String name;

        public RoomEntity toEntity() {
            return RoomEntity.builder()
                    .createUserId(this.createUserId)
                    .name(this.name)
                    .isDeleted("N")
                    .build();
        }
    }
}
