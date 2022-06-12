package com.poogie.sns.topic.dao;

import com.poogie.sns.common.response.ResponseDto;
import com.poogie.sns.common.response.ResponseStatusEnum;
import com.poogie.sns.topic.dto.TopicDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;

    public ResponseDto add(TopicDto.WriteReq req) {
        ResponseDto res = new ResponseDto();

        res.setData(topicRepository.save(req.toEntity()));
        res.setStatus(ResponseStatusEnum.OK);
        res.setMessage("생성 성공");

        return res;
    }

    public ResponseDto findByRoomId(Long roomId) {
        ResponseDto res = new ResponseDto();

        res.setData(topicRepository.findByRoomId(roomId));
        res.setStatus(ResponseStatusEnum.OK);
        res.setMessage("찾기 성공");

        return res;
    }
}
