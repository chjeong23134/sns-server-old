package com.poogie.sns.user.domain;

import com.poogie.sns.common.time.AutoDate;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Table(name="user_image")
public class UserImageEntity extends AutoDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String path;

    public void pathModify(String path) {
        this.path = path;
    }

    @Builder
    public UserImageEntity(Long id, Long userId, String path,
                             LocalDateTime createDate, LocalDateTime updateDate) {
        super(createDate, updateDate);

        this.id = id;
        this.userId = userId;
        this.path = path;
    }
}
