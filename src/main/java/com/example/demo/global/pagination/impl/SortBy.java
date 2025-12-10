package com.example.demo.global.pagination.impl;

import com.example.demo.global.pagination.Sorter;

public enum SortBy implements Sorter {

    ID("id"),

    NAME("name");

    private final String sort;

    SortBy(String sort) {
        this.sort = sort;
    }

    @Override
    public String getSort() {
        return sort;
    }
}
