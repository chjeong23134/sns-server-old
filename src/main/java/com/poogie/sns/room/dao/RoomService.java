package com.poogie.sns.room.dao;

import com.poogie.sns.room.domain.RoomEntity;
import com.poogie.sns.room.domain.RoomParticipantEntity;
import com.poogie.sns.room.dto.RoomDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomParticipantRepository roomParticipantRepository;

    public RoomEntity add(RoomDto.CreateReq req) {
        RoomEntity roomEntity = roomRepository.save(req.toEntity());
        roomParticipantRepository.save(
                RoomParticipantEntity.builder()
                        .userId(roomEntity.getCreateUserId())
                        .roomId(roomEntity.getId())
                        .userClass(0)
                        .build()
        );
        return roomEntity;
    }

    public List<RoomEntity> findByUserId(Long userId) {
        List<RoomParticipantEntity> roomParticipantEntitys = roomParticipantRepository.findByUserId(userId);
        List<RoomEntity> roomEntitys = new ArrayList<>();

        for(RoomParticipantEntity roomParticipantEntity : roomParticipantEntitys) {
            roomEntitys.add(roomRepository.findById(roomParticipantEntity.getRoomId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "RoomEntity 조회 실패")));
        }

        return roomEntitys;
    }
}
