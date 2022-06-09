package com.poogie.sns.room.dao;

import com.poogie.sns.common.response.ResponseDto;
import com.poogie.sns.common.response.ResponseStatusEnum;
import com.poogie.sns.room.domain.RoomEntity;
import com.poogie.sns.room.domain.RoomImageEntity;
import com.poogie.sns.room.domain.RoomParticipantEntity;
import com.poogie.sns.room.dto.RoomDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomImageRepository roomImageRepository;
    @Autowired
    private RoomParticipantRepository roomParticipantRepository;

    public ResponseDto add(RoomDto.CreateReq req) {
        ResponseDto res = new ResponseDto();

        res.setData(roomRepository.save(req.toEntity()));
        res.setStatus(ResponseStatusEnum.OK);
        res.setMessage("생성 성공");

        return res;
    }

    public ResponseDto findByUserId(Long userId) {
        ResponseDto res = new ResponseDto();
        List<RoomParticipantEntity> roomParticipantEntitys = roomParticipantRepository.findByUserId(userId);
        List<RoomDto.ListRes> data = new ArrayList<>();

        RoomEntity roomEntity;
        RoomImageEntity roomImageEntity;
        RoomDto.ListRes roomDto;
        for(RoomParticipantEntity roomParticipantEntity : roomParticipantEntitys) {
            roomEntity = roomRepository.findById(roomParticipantEntity.getRoomId()).orElseThrow();

            roomImageEntity = roomImageRepository.findById(roomParticipantEntity.getId()).orElse(null);

            if(roomImageEntity == null) {
                roomDto = RoomDto.ListRes.builder()
                        .id(roomEntity.getId())
                        .createUserId(roomEntity.getCreateUserId())
                        .name(roomEntity.getName())
                        .isDeleted(roomEntity.getIsDeleted())
                        .createDate(roomEntity.getCreateDate())
                        .updateDate(roomEntity.getUpdateDate())
                        .build();
            } else {
                roomDto = RoomDto.ListRes.builder()
                        .id(roomEntity.getId())
                        .createUserId(roomEntity.getCreateUserId())
                        .name(roomEntity.getName())
                        .isDeleted(roomEntity.getIsDeleted())
                        .createDate(roomEntity.getCreateDate())
                        .updateDate(roomEntity.getUpdateDate())
                        .imageId(roomImageEntity.getRoomId())
                        .build();
            }

            data.add(roomDto);
        }

        res.setData(data);
        res.setStatus(ResponseStatusEnum.OK);
        res.setMessage("조회 성공");

        return res;
    }
}
