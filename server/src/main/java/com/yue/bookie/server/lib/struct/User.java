package com.yue.bookie.server.lib.struct;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "s_user")
@Data
@Entity
public class User {

    @Id
    @Column
    public Integer id;

    @Column
    public String name;

    @Column
    public String password;
}
