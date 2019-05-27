package com.yue.bookie.server.lib.struct;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "book_library")
public class Book {

    @Column(name = "cover_url")
    String coverUrl;

    @Column
    String title;

    @Id
    @Column
    String isbn;

    @Column
    String author;

    @Column
    String description;

    @Column
    Integer storage;

    @Column
    BigInteger price;

    @Column(name = "coupon_price")
    BigInteger couponPrice;

    @Column
    String type;
}
