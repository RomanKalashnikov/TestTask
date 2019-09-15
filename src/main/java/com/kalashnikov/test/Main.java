package com.kalashnikov.test;

public class Main {
    public static void main(String[] args) {
//        Configurator appConfig = new Configurator(args);
//        new Sort(appConfig).mergeAllFiles();
        ArgumentsParser parser = new ArgumentsParser();
        parser.parsingArg(args);
        new Sort(parser).mergeAllFiles();

        System.out.println(parser.getInputFiles());
        System.out.println(parser.getDataTypeArray());
        System.out.println(parser.getSortTypeArray());
        System.out.println(parser.getKeysList());
        System.out.println(parser.getFileResult());

        System.out.println(parser.sortType + " тип сортировки");
        System.out.println(parser.dataType + " тип данных");
    }

}
