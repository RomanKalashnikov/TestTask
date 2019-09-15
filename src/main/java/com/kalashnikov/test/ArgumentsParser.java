package com.kalashnikov.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

class ArgumentsParser {
    private static final String KEY_PATTREN = "^-\\w";
    private final String[] args;
    private DataType dataType;
    private SortType sortType;
    private File fileResult;
    private  List<File> inputFiles = new ArrayList<>();
    private List<String> keysList = new ArrayList<>();

    ArgumentsParser(String... args) {
        this.args = args;
    }

    void parsingArg() {
        checkValidArgsCount(args.length);

        for (String s : args) {
            parseOneArg(s);
        }

        setFileResult();
        checkFile(inputFiles);
        setTypes();
    }

    private void checkValidArgsCount(int countArgs) {
        if (countArgs == 0 & countArgs < 3) {
            throw new MyEx("Отсутсвует необходимое количество аргументов командной строки");
        }
    }

    private void parseOneArg(String s) {
        if (s.matches(KEY_PATTREN)) {
            keysList.add(s);
        } else {
            inputFiles.add(new File(s));
        }
    }

    private void checkFile(List<File> files) {
            for (File f : files) {
                if (!f.exists()) {
                    System.err.printf("Файл отсутствует или указан неправильный путь %s " +
                            "и он не будет добавлен в конечный отсортированный файл %n", f);
                    files.remove(f);
                }
            }
        }


    private void setFileResult() {
        if (inputFiles.size() < 3) {
            throw new MyEx("Количество переданных файлов меньше трёх");
        } else {
            fileResult = inputFiles.get(0);
            inputFiles.remove(fileResult);
        }
    }


    private void setTypes() {
        if (keysList.isEmpty()) {
            throw new MyEx("Отсутствуют какие либо ключи указывающие на Тип данных или Тип сортировки");
        } else {
            this.dataType = getDataType(keysList);
            this.sortType = getSortType(keysList);
        }
    }

    private SortType getSortType(List<String> keysList) {
        final SortType[] values = SortType.values();
        for (SortType type : values) {
            if (keysList.contains(type.key)) {
                return type;
            }
        }
        return SortType.ASC;
    }

    private DataType getDataType(List<String> keysList) {
        final DataType[] values = DataType.values();
        for (DataType dataType : values) {
            if (keysList.contains(dataType.type)) {
                return dataType;
            }
        }
        throw new MyEx("Отсутствует корректный ключ Типа Данных");
    }


    File getFileResult() {
        return fileResult;
    }

    List<File> getInputFiles() {
        return inputFiles;
    }

    DataType getDataType() {
        return dataType;
    }

    SortType getSortType() {
        return sortType;
    }
}





