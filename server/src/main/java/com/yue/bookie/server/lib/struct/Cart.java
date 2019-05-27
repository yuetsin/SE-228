package com.yue.bookie.server.lib.struct;

import javax.annotation.Generated;
import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "carts")
@IdClass(MultiPrimKey.class)
public class Cart implements Serializable {

    @Id
    @Column(name = "user_id")
    public Integer userId;

    @Id
    @Column(name = "isbn")
    public String isbn;

    @Column(name = "count")
    public Integer count;

    @Column(name = "title")
    public String title;

    @Column(name = "author")
    public String author;

    public Cart() {

    }
}