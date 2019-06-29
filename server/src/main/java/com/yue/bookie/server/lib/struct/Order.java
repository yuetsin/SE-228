package com.yue.bookie.server.lib.struct;


import com.sun.tools.corba.se.idl.constExpr.Times;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "bill_uuid")
    public String billUuid;

    @Column(name = "user_id")
    public Integer userId;

    @Column
    public Timestamp time;

    @Column
    public String receiver;

    @Column(name = "phone_no")
    public String phoneNo;

    @Column
    public String address;
}
