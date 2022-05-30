package com.poogie.sns.room.dao;

import com.poogie.sns.common.response.ResponseDto;
import com.poogie.sns.common.response.ResponseStatusEnum;
import com.poogie.sns.room.dto.RoomParticipantDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomParticipantService {
    @Autowired
    RoomParticipantRepository roomParticipantRepository;

    public ResponseDto add(RoomParticipantDto.InviteReq req) {
        ResponseDto res = new ResponseDto();

        res.setData(roomParticipantRepository.save(req.toEntity()));
        res.setStatus(ResponseStatusEnum.OK);
        res.setMessage("생성 성공");

        return res;
    }
}
