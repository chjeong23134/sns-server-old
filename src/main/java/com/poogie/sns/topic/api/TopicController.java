package com.poogie.sns.topic.api;

import com.poogie.sns.topic.dao.TopicService;
import com.poogie.sns.topic.domain.TopicEntity;
import com.poogie.sns.topic.dto.TopicDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topic")
@CrossOrigin("*")
public class TopicController {
    @Autowired
    private TopicService topicService;

    @PostMapping("/write")
    public ResponseEntity<TopicEntity> write(@RequestBody TopicDto.WriteReq req) {
        return new ResponseEntity<>(topicService.add(req), HttpStatus.OK);
    }

    @GetMapping("/list/{roomId}")
    public ResponseEntity<List<TopicEntity>> list(@PathVariable Long roomId) {
        return new ResponseEntity<>(topicService.findByRoomId(roomId), HttpStatus.OK);
    }
}
