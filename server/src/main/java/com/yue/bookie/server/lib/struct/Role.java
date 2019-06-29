package com.yue.bookie.server.lib.struct;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "s_role")
public class Role {
    @Id
    @Column(name = "id")
    public Integer id;

    @Column(name = "role")
    public String role;
}
