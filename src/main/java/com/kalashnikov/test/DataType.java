package com.kalashnikov.test;

public enum DataType {
    STRING("-s"),
    INTEGER("-i");
    private final String data;

    DataType(String data) {
        this.data = data;
    }

    public static DataType of(String data){
        for (DataType args: values()) {
            if(args.data.equals(data)){
                return args;

            }
        }
        throw new IllegalArgumentException(data);
    }
}
