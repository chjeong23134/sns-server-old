package com.poogie.sns.topic.api;

import com.poogie.sns.common.response.ResponseDto;
import com.poogie.sns.topic.dao.TopicService;
import com.poogie.sns.topic.dto.TopicDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topic")
@CrossOrigin("*")
public class TopicController {
    @Autowired
    private TopicService topicService;

    @PostMapping("/write")
    public ResponseEntity<ResponseDto> write(@RequestBody TopicDto.WriteReq req) {
        ResponseDto res = topicService.add(req);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/list/{roomId}")
    public ResponseEntity<ResponseDto> list(@PathVariable Long roomId) {
        ResponseDto res = topicService.findByRoomId(roomId);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
