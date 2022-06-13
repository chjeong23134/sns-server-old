package com.poogie.sns.comment.domain;

import com.poogie.sns.common.time.AutoDate;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name="comment")
public class CommentEntity extends AutoDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long topicId;
    private Long createUserId;
    private String content;
    private String isDeleted;

    @Builder
    public CommentEntity(Long id, Long topicId, Long createUserId, String content, String isDeleted,
                            LocalDateTime createDate, LocalDateTime updateDate) {
        super(createDate, updateDate);

        this.id = id;
        this.topicId = topicId;
        this.createUserId = createUserId;
        this.content = content;
        this.isDeleted = isDeleted;
    }
}
