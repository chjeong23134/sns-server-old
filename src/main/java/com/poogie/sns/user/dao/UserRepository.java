package com.poogie.sns.user.dao;

import com.poogie.sns.user.domain.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
}
