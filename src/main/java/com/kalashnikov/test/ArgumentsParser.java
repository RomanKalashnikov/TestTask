package com.kalashnikov.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ArgumentsParser {
    DataType dataType;
    SortType sortType;

    static File fileResult;

    private static List<File> inputFiles = new ArrayList<>();
    private ArrayList<SortType> sortTypeArray = new ArrayList<>();

    private ArrayList<DataType> dataTypeArray = new ArrayList<>();
    private ArrayList<String> keysList = new ArrayList<>();


    ArgumentsParser() {


    }

    public void parsingArg(String... args) {
        if (args.length != 0 & args.length > 3) {
            for (String s : args) {
                if (s.matches("-?\\w")) {
                    keysList.add(s);

                } else {
                    inputFiles.add(new File(s));
                }
            }
        }
        setFileResult();
        setTypes();
        checkFile(inputFiles);

    }

    private void checkFile(List<File> files) {
        for (File f : files) {
            if (!f.exists()) {
                System.err.println("Неправильный путь входного файла " + f + " и он не будет добавлен в конечный" +
                        " отсортированный файл ");
                files.remove(f);
            }

        }
    }


    public void setTypes() {
        for (String s : keysList) {
            for (SortType type : SortType.values()) {
                if(s.equals(type.data)){
                    this.sortType = type;
                }
            }
            for (DataType type : DataType.values()) {
                if(s.equals(type.data)){
                    this.dataType = type;
                }
            }
        }
    }

    private void setFileResult() {
        fileResult = new File(String.valueOf(inputFiles.get(0)));
        inputFiles.remove(0);
    }

    public static File getFileResult() {
        return fileResult;
    }


    public ArrayList<String> getKeysList() {
        return keysList;
    }

    public static List<File> getInputFiles() {
        return inputFiles;
    }

    public ArrayList<SortType> getSortTypeArray() {
        return sortTypeArray;
    }

    public ArrayList<DataType> getDataTypeArray() {
        return dataTypeArray;
    }
}





