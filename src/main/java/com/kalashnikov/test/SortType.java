package com.kalashnikov.test;

public enum SortType {
    DESC("-d"),
    ASC("-a");

    final String key;

    SortType(String args) {
        this.key = args;
    }


}
