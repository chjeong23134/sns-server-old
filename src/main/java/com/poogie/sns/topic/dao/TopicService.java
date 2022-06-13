package com.poogie.sns.topic.dao;

import com.poogie.sns.topic.domain.TopicEntity;
import com.poogie.sns.topic.dto.TopicDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;

    public TopicEntity add(TopicDto.WriteReq req) {
        return topicRepository.save(req.toEntity());
    }

    public List<TopicEntity> findByRoomId(Long roomId) {
        return topicRepository.findByRoomId(roomId);
    }
}
