package com.kalashnikov.test;

public enum DataType {
    INTEGER("-i"),
    STRING("-s");


    final String data;

    DataType(String args) {
        this.data = args;
    }

    public static DataType  of(String data) {

        for (DataType dataType : values()) {
            if (dataType.data.equals(data)) {
                return dataType;
            }
        }
      throw  new IllegalArgumentException(data);
    }
}
