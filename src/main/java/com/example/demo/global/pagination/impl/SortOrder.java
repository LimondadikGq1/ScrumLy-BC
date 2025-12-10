package com.example.demo.global.pagination.impl;

import lombok.Getter;

@Getter
public enum SortOrder {

    ASC("ASC"),

    DESC("DESC");

    private final String order;

    SortOrder(String order) {
        this.order = order;
    }
}
