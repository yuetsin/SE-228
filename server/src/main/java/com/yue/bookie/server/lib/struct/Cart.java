package com.yue.bookie.server.lib.struct;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@Table(name = "bills")
public class Cart implements Serializable {

    @Id
    @Column
    public Integer userId;

    @Column
    public Integer count;

    @Id
    @Column
    public String isbn;
}