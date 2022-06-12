package com.poogie.sns.topic.dto;

import com.poogie.sns.topic.domain.TopicEntity;
import lombok.Getter;

public class TopicDto {
    @Getter
    public static class WriteReq {
        private Long roomId;
        private Long createUserId;
        private String title;
        private String content;

        public TopicEntity toEntity() {
            return TopicEntity.builder()
                    .roomId(this.roomId)
                    .createUserId(this.createUserId)
                    .title(title)
                    .content(content)
                    .isDeleted("N")
                    .build();
        }
    }
}
