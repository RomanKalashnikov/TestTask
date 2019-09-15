package com.kalashnikov.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

class Sort {
    private Comparator comparator;
    private List<File> input;
    private File out;
    PrepareArgs prepareArgs;

    public Sort(Comparator comparator, List<File> input, File out, PrepareArgs prepareArgs) {
        this.comparator = comparator;
        this.input = input;
        this.out = out;
        this.prepareArgs = prepareArgs;
    }

    void mergeAllFiles() {
        final File reduced = input.stream().reduce(this::mergeFile).orElse(input.get(0));
        try {
            Files.copy(reduced.toPath(), out.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("Невозможно скопировать результат слияния файлов");
            e.printStackTrace();
        }
    }

    private File mergeFile(File file1, File file2) {
        try {
            File out = File.createTempFile("result", ".tmp");
            out.deleteOnExit();
            try (BufferedReader fileReader1 = new BufferedReader(new FileReader(file1));
                 BufferedReader fileReader2 = new BufferedReader(new FileReader(file2));
                 BufferedWriter fileOut = new BufferedWriter(new FileWriter(out))) {
                Comparable s1 = prepareArgs.prepare(fileReader1.readLine());
                Comparable s2 = prepareArgs.prepare(fileReader2.readLine());
                while (fileReader1.ready() && fileReader2.ready()) {
                    final int compare = comparator.compare(s1, s2);
                    if (compare <= 0) {
                        writeLine(fileOut, s1);
                        s1 = prepareArgs.prepare(fileReader1.readLine());
                    } else {
                        writeLine(fileOut, s2);
                        s2 = prepareArgs.prepare(fileReader2.readLine());
                    }
                }
                if (fileReader1.ready()) {
                    writeEndOfFile(s1, fileOut, fileReader1);
                }
                if(fileReader2.ready()){
                    writeEndOfFile(s2, fileOut, fileReader2);
                }
                return out;
            }
        } catch (IOException e) {
            throw new MyEx();
        }
    }

    private void writeEndOfFile(Comparable lastValue, BufferedWriter writer, BufferedReader fileDataReader) throws
            IOException {
        writeLine(writer, lastValue);
        Comparable value = fileDataReader.readLine();
        while (value != null) {
            writeLine(writer, value);
            value = fileDataReader.readLine();
        }
    }

    private void writeLine(BufferedWriter writer, Comparable line) {
        try {
            writer.write(line.toString());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Невозможно записать строку в файл");
        }
    }
}
