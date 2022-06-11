package com.poogie.sns.room.dao;

import com.poogie.sns.common.response.ResponseDto;
import com.poogie.sns.common.response.ResponseStatusEnum;
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

    public ResponseDto add(RoomDto.CreateReq req) {
        ResponseDto res = new ResponseDto();
        RoomEntity roomEntity = roomRepository.save(req.toEntity());
        roomParticipantRepository.save(
                RoomParticipantEntity.builder()
                        .userId(roomEntity.getCreateUserId())
                        .roomId(roomEntity.getId())
                        .userClass(0)
                        .build()
        );

        res.setData(roomEntity);
        res.setStatus(ResponseStatusEnum.OK);
        res.setMessage("생성 성공");

        return res;
    }

    public ResponseDto findByUserId(Long userId) {
        ResponseDto res = new ResponseDto();
        List<RoomParticipantEntity> roomParticipantEntitys = roomParticipantRepository.findByUserId(userId);
        List<RoomEntity> roomEntitys = new ArrayList<>();

        for(RoomParticipantEntity roomParticipantEntity : roomParticipantEntitys) {
            roomEntitys.add(roomRepository.findById(roomParticipantEntity.getRoomId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "RoomEntity 조회 실패")));
        }

        res.setData(roomEntitys);
        res.setStatus(ResponseStatusEnum.OK);
        res.setMessage("조회 성공");

        return res;
    }
}
