package com.yue.bookie.server.lib.repository;

import com.yue.bookie.server.lib.struct.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Long> {
    List<Comment> findAllByUserId(Integer userId);
    /* Foolish JPA reserved underscore */

    List<Comment> findAllByIsbn(String isbn);
}
