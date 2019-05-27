package com.yue.bookie.server.lib.struct;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Data
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

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean reduceStorage(Integer count) {
        if (storage >= count) {
            storage -= count;
            return true;
        } else {
            return false;
        }
    }

    public Book() {

    }
}
