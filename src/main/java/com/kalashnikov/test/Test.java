package com.kalashnikov.test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;

public class Test {
    static void sort(){
        String[] arrString = new String [0];
        File file = new File("C:\\Users\\Romerro\\IdeaProjects\\testTask\\src\\main\\resources\\files\\s2.txt");
        try {
            arrString = Files.lines(file.toPath(), StandardCharsets.UTF_8).toArray(String[]::new);
            Arrays.sort(arrString);
            for (String s:arrString) {
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
