package com.poogie.sns.room.dao;

import com.poogie.sns.room.domain.RoomEntity;
import org.springframework.data.repository.CrudRepository;

public interface RoomRepository extends CrudRepository<RoomEntity, Long> {
}
