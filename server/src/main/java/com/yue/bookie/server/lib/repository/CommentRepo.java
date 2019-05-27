package com.yue.bookie.server.lib.repository;

import com.yue.bookie.server.lib.struct.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, String> {

    public List<Comment> findAllByUserId(Integer userId);

    @Query(value = "SELECT comm_uuid, isbn, user_id, s_user.name, `time`, purchased, comment_content FROM comments STRAIGHT_JOIN s_user ON comments.user_id = s_user.id WHERE isbn = ?1", nativeQuery = true)
    public List<Comment> findAllByIsbn(String isbn);

    public Comment save(Comment comment);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO comments(comm_uuid, user_id, time, purchased, isbn, comment_content) VALUES (UUID(), ?1, NOW(), ?2, ?3, ?4)", nativeQuery = true)
    public void addComments(Integer userId, Boolean purchased, String isbn, String content);
}
