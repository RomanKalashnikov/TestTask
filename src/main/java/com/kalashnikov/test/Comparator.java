package com.kalashnikov.test;

class Comparator {
    private final DataType dataType;
    private final SortType sortType;

    Comparator(DataType dataType, SortType sortType) {
        this.dataType = dataType;
        this.sortType = sortType;

    }
//    \D+

    int compare(String s1, String s2) {
        Comparable v1 = getValue(s1);
        Comparable v2 = getValue(s2);
        switch (sortType) {
            case ASC:
                return v1.compareTo(v2);
            case DESC:
                return v2.compareTo(v1);
            default:
                return 0;
        }

    }

    private Comparable getValue(String s) {

        switch (dataType) {

            case INTEGER:
                //                return Integer.valueOf(s.replaceAll("[^0-9]", "0"));
               return Integer.valueOf(s);

            case STRING:
//                return s.replaceAll("\\W*", "");
                return s;
        }

        return 0;
    }
    String checkLine(String s){
        switch (dataType) {

            case INTEGER:
//                return Integer.valueOf(s.replaceAll("[^0-9]", "0"));
                return s.replaceAll("\\D+$", "0");

            case STRING:
//                return s.replaceAll("\\W*", "");
                return s;
        }
        return s;

    }
}
