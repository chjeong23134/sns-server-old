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
@Table(name="room")
public class RoomEntity extends AutoDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long createUserId;
    private String name;
    private String isDeleted;

    @Builder
    public RoomEntity(Long id, Long createUserId, String name, String isDeleted,
                        LocalDateTime createDate, LocalDateTime updateDate) {
        super(createDate, updateDate);

        this.id = id;
        this.createUserId = createUserId;
        this.name = name;
        this.isDeleted = isDeleted;
    }
}
