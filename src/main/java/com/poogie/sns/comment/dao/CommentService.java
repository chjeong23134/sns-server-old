package com.poogie.sns.comment.dao;

import com.poogie.sns.comment.domain.CommentEntity;
import com.poogie.sns.comment.dto.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public CommentEntity add(CommentDto.WriteReq req) {
        return commentRepository.save(req.toEntity());
    }

    public List<CommentEntity> findByTopicId(Long topicId) {
        return commentRepository.findByTopicId(topicId);
    }
}
