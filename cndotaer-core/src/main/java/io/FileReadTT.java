package io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Created by cuiguiyang on 2017/3/11 11:59.
 * Desc
 */
public class FileReadTT {

    public static final String filePath = "/Users/cuiguiyang/workspace/IdeaProjects/multimodule/core/src/main/java/note.txt";
    public static void main(String[] args) {
        File file = new File(filePath);
        try {
            byte[] bytes = new byte[1024];
            InputStream inputStream = new FileInputStream(file);
            StringBuffer line = new StringBuffer();
            while (inputStream.read(bytes) != -1) {
                line.append(bytes);
            }
            System.out.println(line.toString());

            InputStreamReader reader = new FileReader(file);
            /*BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }*/

            /*OutputStream outputStream = new FileOutputStream(file);
            String newLine = "Hello world";
            outputStream.write(newLine.getBytes());*/
            Path paths = Paths.get(filePath);
            Files.write(paths, "\nHello".getBytes(), StandardOpenOption.APPEND);
            Files.write(paths, "\nHello".getBytes());
            Files.readAllLines(Paths.get(filePath));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
