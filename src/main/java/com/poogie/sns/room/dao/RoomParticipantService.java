package com.poogie.sns.room.dao;

import com.poogie.sns.room.domain.RoomParticipantEntity;
import com.poogie.sns.room.dto.RoomParticipantDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomParticipantService {
    @Autowired
    RoomParticipantRepository roomParticipantRepository;

    public RoomParticipantEntity add(RoomParticipantDto.InviteReq req) {
        return roomParticipantRepository.save(req.toEntity());
    }
}
