package com.yue.bookie.server.lib.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SPermission {
    public int id;
    public String permission;
    public String url;
    public String describe;
}