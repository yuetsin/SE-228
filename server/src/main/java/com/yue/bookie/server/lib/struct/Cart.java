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
    @Column
    public Integer userId;

    @Id
    @Column
    public String isbn;

    @Column
    public Integer count;


    public String title;
    public String author;
}