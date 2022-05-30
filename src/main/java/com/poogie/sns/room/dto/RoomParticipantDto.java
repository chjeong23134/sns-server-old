package com.poogie.sns.room.dto;

import com.poogie.sns.room.domain.RoomParticipantEntity;
import lombok.Getter;

public class RoomParticipantDto {
    @Getter
    public static class InviteReq {
        private Long userId;
        private Long roomId;

        public RoomParticipantEntity toEntity() {
            return RoomParticipantEntity.builder()
                    .userId(this.userId)
                    .roomId(this.roomId)
                    .userClass(0)
                    .build();
        }
    }
}
