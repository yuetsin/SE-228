package com.yue.bookie.server.lib.struct;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
public class Receipt {


    Boolean paid;
    Book[] items;
    User user;
    Integer money;
    String orderTime;
    String payTime;
}
