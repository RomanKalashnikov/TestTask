package com.kalashnikov.test;

public enum DataType {
    INTEGER("-i"),
    STRING("-s");

    final String type;

    DataType(String args) {
        this.type = args;
    }
}
