package com.poogie.sns.comment.api;

import com.poogie.sns.comment.dao.CommentService;
import com.poogie.sns.comment.domain.CommentEntity;
import com.poogie.sns.comment.dto.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@CrossOrigin("*")
public class CommentController {
    @Autowired
    private CommentService commentService;

    /*
        [ 댓글작성 ]
        - topicId (Long)
        - createUserId (Long)
        - content (Long)
     */
    @PostMapping("/write")
    public ResponseEntity<CommentEntity> write(@RequestBody CommentDto.WriteReq req) {
        return new ResponseEntity<>(commentService.add(req), HttpStatus.OK);
    }

    /*
        [ 댓글리스트 ]
        - topicId (Long)
     */
    @GetMapping("/list/{topicId}")
    public ResponseEntity<List<CommentEntity>> list(@PathVariable Long topicId) {
        return new ResponseEntity<>(commentService.findByTopicId(topicId), HttpStatus.OK);
    }

    /*
        [ 최근 댓글 ]
        - topicId (Long)
     */
    @GetMapping("/last-comment/{topicId}")
    public ResponseEntity<CommentEntity> lastComment(@PathVariable Long topicId) {
        return new ResponseEntity<>(commentService.findTop1ByTopicIdOrderByCreateDateDesc(topicId), HttpStatus.OK);
    }
}
