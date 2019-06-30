package com.yue.bookie.server.lib.struct;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Data
@Table(name = "book_library")
public class Book {

    @Column
    public Boolean disabled;

    @Column(name = "cover_url")
    public String coverUrl;

    @Column
    public String title;

    @Id
    @Column
    public String isbn;

    @Column
    public String author;

    @Column
    public String description;

    @Column
    public Integer storage;

    @Column
    public BigDecimal price;

    @Column(name = "coupon_price")
    public BigDecimal couponPrice;

    @Column
    public String type;

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
