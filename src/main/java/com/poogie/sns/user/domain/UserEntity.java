package com.poogie.sns.user.domain;

import com.poogie.sns.common.time.AutoDate;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Table(name="user")
public class UserEntity extends AutoDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String name;
    private String isDeleted;

    @Builder
    public UserEntity(Long id, String email, String password, String name, String isDeleted) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.isDeleted = isDeleted;
    }
}
