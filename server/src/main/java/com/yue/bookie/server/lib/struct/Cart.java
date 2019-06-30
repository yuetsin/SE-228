package com.yue.bookie.server.lib.struct;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;

import javax.annotation.Generated;
import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;


@Data
@Entity
@Table(name = "unpaid_carts")
@IdClass(CartPrimaryKey.class)
@DynamicUpdate
public class Cart {

    @Id
    @Column(name = "user_id", nullable = false)
    public Integer userId;

    @Id
    @Column(name = "isbn", nullable = false)
    public String isbn;

    @Column(name = "count")
    public Integer count;

    public Cart() {

    }

}