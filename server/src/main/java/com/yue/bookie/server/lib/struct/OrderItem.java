package com.yue.bookie.server.lib.struct;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@IdClass(OrderItem.class)
@Table(name = "order_items")
public class OrderItem implements Serializable {

    @Id
    @Column(name = "bill_uuid")
    public String billUuid;

    @Column
    public Integer count;

    @Id
    @Column
    public String isbn;
}
