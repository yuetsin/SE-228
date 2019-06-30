package com.yue.bookie.server.lib.struct;

import lombok.Data;

import java.io.Serializable;

@Data
public class CartPrimaryKey implements Serializable {

    private Integer userId;

    private String isbn;
}
