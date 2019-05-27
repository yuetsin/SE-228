package com.yue.bookie.server.lib.struct;

import lombok.Data;

import javax.persistence.*;

@Table(name = "s_user")
@Data
@Entity
public class User {

    @Id
    @Column
    @GeneratedValue
    public Integer id;

    @Column(name = "`name`")
    public String name;

    @Column
    public String password;
}
