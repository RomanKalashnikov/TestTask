package com.kalashnikov.test;

import sun.invoke.empty.Empty;

class Comparator {
    private final DataType dataType;
    private final SortType sortType;

    Comparator(DataType dataType, SortType sortType) {
        this.dataType = dataType;
        this.sortType = sortType;

    }

    int compare(String s1, String s2) {
        Comparable v1 = getValue(s1);
        Comparable v2 = getValue(s2);
        switch (sortType) {
            case UPWARD:
                return v1.compareTo(v2);
            default:
                return v2.compareTo(v1);
        }
    }

    private Comparable getValue(String s) {

        switch (dataType) {

            case INTEGER:
                return Integer.valueOf(s);
            default:
                return s.replaceAll("\\W*", "");
        }
    }
}
