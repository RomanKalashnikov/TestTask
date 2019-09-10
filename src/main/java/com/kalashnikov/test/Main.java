package com.kalashnikov.test;

public class Main {
    public static void main(String[] args) {
        Editor appConfig = new Editor(args);
        new Sort(appConfig).mergeAllFiles();


    }

}
