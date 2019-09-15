package com.kalashnikov.test;

public enum SortType {
    DESC("-d"),
    ASC("-a");

    final String data;

    SortType(String args) {
        this.data = args;
    }

    public static SortType of(String data) {

        for (SortType sortType : values()) {
            if (sortType.data.equals(data)) {
                return sortType;

            }
        }
        throw new IllegalArgumentException(data);

    }
}
