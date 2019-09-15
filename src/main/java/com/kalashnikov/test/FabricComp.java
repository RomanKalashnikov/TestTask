package com.kalashnikov.test;

public class FabricComp {

    Comparator getComp(DataType dataType, SortType sortType) {
        switch (dataType) {
            case INTEGER:
                return new IntComp(sortType);
            case STRING:
                return new StringComp(sortType);
            default:
                throw new MyEx();
        }
    }
}
