package com.example.SpringGradlePostAndComents.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.nullpointerexception.restapi.model.Comment;

import java.util.list;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List <Comment> findAllByPostIdIn(List<Long> ids);
}