package com.poogie.sns.user.dao;

import com.poogie.sns.user.domain.UserImageEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserImageRepository extends CrudRepository<UserImageEntity, Long> {
    UserImageEntity findByUserId(Long userId);
}
