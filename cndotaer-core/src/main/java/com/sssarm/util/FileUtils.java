package com.sssarm.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Intellij IDEA.
 * Author: Eric Cui
 * Date  : 2017/7/26 23:26
 * Desc  : 描述信息
 */
public class FileUtils {


    public static void main(String[] args) {
        int inputBuffSize = 1024 * 5;
        long lineNum = 0;
        try {
            Path path = Paths.get(new URI("file:/Users/cuiguiyang/eric/Java/test/B-200W-400W.csv"));
            Reader reader = new InputStreamReader(Files.newInputStream(path));
            BufferedReader bufferedReader = new BufferedReader(reader, inputBuffSize);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                lineNum++;
            }
            System.out.println(lineNum);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }
}
