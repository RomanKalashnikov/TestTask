package com.kalashnikov.test;

class StringComp extends Comparator<String> {
    private SortType sortType;

    StringComp(SortType sortType) {
        super();
        this.sortType = sortType;
    }

    @Override
    int compare(String  a, String  b) {
        switch (sortType) {
            case DESC:
                return  b.compareTo(a);
            default:
                return a.compareTo(b);
        }
    }
}
