package com.kalashnikov.test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

class Sort {
    private Editor editor;
    private Comparator comparator;


    Sort(Editor editor) {
        this.editor = editor;
        this.comparator = new Comparator(editor.getDataType(), editor.getSortType());
    }

    void mergeAllFiles() {
        final File reduced = editor.getInputFiles().stream().reduce(this::mergeFiles).orElse(editor.getInputFiles().get(0));
        try {
            Files.copy(reduced.toPath(), editor.getFileResult().toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("Невозможно скопировать результат слияния файлов");
            e.printStackTrace();

        }

    }

    private File mergeFiles(File file1, File file2) {
        try {
            File out = File.createTempFile("result", ".tmp");
            out.deleteOnExit();
            try (BufferedReader fileReader1 = new BufferedReader(new FileReader(file1));
                 BufferedReader fileReader2 = new BufferedReader(new FileReader(file2));
                 BufferedWriter fileOut = new BufferedWriter(new FileWriter(out))) {
                String s1 = fileReader1.readLine();
                String s2 = fileReader2.readLine();
                while (s1 != null && s2 != null) {
                    final int compare = comparator.compare(s1, s2);
                    if (compare <= 0) {
                        writeLine(fileOut, s1);
                        s1 = fileReader1.readLine();
                    } else {
                        writeLine(fileOut, s2);
                        s2 = fileReader2.readLine();
                    }
                }
                if (s1 == null) {
                    writeEndOfFile(s2, fileOut, fileReader2);
                } else {
                    writeEndOfFile(s1, fileOut, fileReader1);
                }
                return out;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);


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
            System.err.println("can't write line to file ");
        }
    }
}
