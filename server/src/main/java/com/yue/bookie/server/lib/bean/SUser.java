package com.yue.bookie.server.lib.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SUser {
    public int id;
    public String name;
    public String password;

    public SUser(SUser sUser) {
        this.id = sUser.id;
        this.name = sUser.name;
        this.password = sUser.password;
    }
}
