package com.poogie.sns.room.dto;

import com.poogie.sns.room.domain.RoomEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

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

    @Getter
    @Builder
    public static class ListRes {
        private Long id;
        private Long createUserId;
        private String name;
        private String isDeleted;
        private LocalDateTime createDate;
        private LocalDateTime updateDate;

        private Long imageId;
    }
}
