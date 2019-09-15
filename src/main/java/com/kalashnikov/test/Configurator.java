package com.kalashnikov.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Configurator {
    private DataType dataType;

    private SortType sortType;
    private List<File> inputFiles = new ArrayList<>();
    private File fileResult;

    public Configurator(DataType dataType, SortType sortType, List<File> inputFiles, File fileResult) {
        this.dataType = dataType;
        this.sortType = sortType;
        this.inputFiles = inputFiles;
        this.fileResult = fileResult;

    }


    Configurator(String[] args) {
        boolean hasTypeSort = false;
        try {
            this.sortType = SortType.of(args[0]);
            hasTypeSort = true;
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            this.sortType = SortType.DESC;
        }
        if ((hasTypeSort && args.length < 4) || (!hasTypeSort && args.length < 3)) {
            throw new IllegalArgumentException("Неверное количество аргументов");
        }
        this.dataType = DataType.of(hasTypeSort ? args[1] : args[0]);
        this.fileResult = hasTypeSort ? new File(args[2]) : new File(args[1]);
        for (int i = hasTypeSort ? 3 : 2; i < args.length; i++) {
            final File inFile = new File(args[i]);
            if (inFile.exists()) {
                inputFiles.add(inFile);
            }
            else {
                System.err.println("Неправильный путь входного файла " + args[i] + " и он не будет добавлен в конечный" +
                        " отсортированный файл ");


            }
        }
    }

    DataType getDataType() {
        return dataType;
    }

    SortType getSortType() {
        return sortType;
    }

    List<File> getInputFiles() {
        for (File Arr: inputFiles) {
            System.out.println(Arr);
        }
        return inputFiles;
    }

    File getFileResult() {
        return fileResult;
    }
}
