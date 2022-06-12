package com.poogie.sns.topic.domain;

import com.poogie.sns.common.time.AutoDate;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name="topic")
public class TopicEntity extends AutoDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long roomId;
    private Long createUserId;
    private String title;
    private String content;
    private String isDeleted;

    @Builder
    public TopicEntity(Long id, Long roomId, Long createUserId, String title, String content,
                       String isDeleted, LocalDateTime createDate, LocalDateTime updateDate) {
        super(createDate, updateDate);

        this.id = id;
        this.roomId = roomId;
        this.createUserId = createUserId;
        this.title = title;
        this.content = content;
        this.isDeleted = isDeleted;
    }
}
