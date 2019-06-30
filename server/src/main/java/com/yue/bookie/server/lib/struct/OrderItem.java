package com.yue.bookie.server.lib.struct;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
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
