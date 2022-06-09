package com.poogie.sns.room.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name="room_image")
public class RoomImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long roomId;
    private String path;

    public RoomImageEntity(Long id, Long roomId, String path) {
        this.id = id;
        this.roomId = roomId;
        this.path = path;
    }
}
