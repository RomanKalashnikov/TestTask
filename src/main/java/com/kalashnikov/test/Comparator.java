package com.kalashnikov.test;

abstract class Comparator<T> {

    Comparator() {

    }

    abstract int compare(T a, T b);
}
