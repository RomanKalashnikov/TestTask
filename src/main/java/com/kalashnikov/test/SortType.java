package com.kalashnikov.test;

public enum SortType {
    UPWARD("-a"),
    DOWNWARD("-d");

    public final String args ;

    SortType(String args) {
       this.args = args;
    }

    public static SortType of(String s){
        for (SortType sortType : values()) {
                if(sortType.args.equals(s)){
                    return sortType;

                }
        }
        throw new IllegalArgumentException(s);
    }
}
