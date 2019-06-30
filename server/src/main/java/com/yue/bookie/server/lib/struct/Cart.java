package com.yue.bookie.server.lib.struct;

import javax.annotation.Generated;
import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "unpaid_carts")
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

    public Cart() {

    }
}