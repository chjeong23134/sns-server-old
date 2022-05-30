package com.poogie.sns.room.dao;

import com.poogie.sns.room.domain.RoomParticipantEntity;
import org.springframework.data.repository.CrudRepository;

public interface RoomParticipantRepository extends CrudRepository<RoomParticipantEntity, Long> {
}
