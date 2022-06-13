package com.poogie.sns.comment.dao;

import com.poogie.sns.comment.domain.CommentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<CommentEntity, Long> {
    List<CommentEntity> findByTopicId(Long topicId);
}
