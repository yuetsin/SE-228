package com.yue.bookie.server.lib.struct;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Data;
import org.apache.ibatis.annotations.One;

import javax.annotation.Generated;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data
@Table(name = "comments")
public class Comment implements Serializable {
    @Id
    @Column(name = "comm_uuid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String commUuid;

    @Column
    String isbn;

    @Column(name = "user_id")
    Integer userId;

    @Column
    Timestamp time;

    @Column(name = "comment_content")
    String commentContent;

    @Column
    Boolean purchased;

    public String getCommUuid() {
        return commUuid;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public Comment() {

    }
}
