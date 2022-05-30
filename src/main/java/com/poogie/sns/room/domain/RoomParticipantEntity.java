package com.poogie.sns.room.domain;

import com.poogie.sns.common.time.AutoDate;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name="room_participant")
public class RoomParticipantEntity extends AutoDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long roomId;
    private int userClass;

    @Builder
    public RoomParticipantEntity(Long id, Long userId, Long roomId, int userClass,
                                 LocalDateTime createDate, LocalDateTime updateDate) {
        super(createDate, updateDate);

        this.id = id;
        this.userId = userId;
        this.roomId = roomId;
        this.userClass = userClass;
    }
}
