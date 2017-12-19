package com.sssarm.util;

import com.monitorjbl.xlsx.StreamingReader;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.bson.Document;
import org.springframework.util.Assert;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by cuiguiyang on 2017/2/7 01:01.
 * Desc 读取大Excel文件
 */
public class ExcelUtil {

    private static final String separator = ",";
    private static final String CHARSET_UTF8 = "UTF-8";
    private static final String BIG_EXCEL_FILE_PATH = "/Users/cuiguiyang/eric/Java/test/A-0001-200W.csv";
    /*
    load excel cost: 14s
    sheet name is: A-0001-200W
    start:1500991235783
    ent  :1500991468138
     */

    private static final String ROOM_USER_DEFORMITY_COLLECTION = "room_user_info_deformity";

    public static void main(String[] args) {
        // xlsxRead(BIG_EXCEL_FILE_PATH);
        // multiThread();
        final String filePath = "/Users/cuiguiyang/eric/Java/test/A-0001-200W.csv";
        bigFileRead(filePath);
    }

    /**
     * 直接读取csv文件内容
     * @param filePath csv文件路径
     */
    private static void bigFileRead(final String filePath) {
        System.out.println(Thread.currentThread().getId() + " start: " + System.currentTimeMillis());
        Assert.hasText(filePath, "文件路径不正确");
        boolean isFirstLine = true;
        Map<Integer, String> indexKeyMap = new HashMap<>();

        FileInputStream inputStream = null;
        Scanner sc = null;
        try {
            inputStream = new FileInputStream(filePath);
            sc = new Scanner(inputStream, CHARSET_UTF8);

            Document document;
            int maxColumn = 0;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] row = line.split(separator);
                if (row.length != 33) {
                    document = new Document();
                    for (int i = 0; i < row.length; i++) {
                        document.append("key_" + i, row[i]);
                    }
                    MongoUtil.insertOne(ROOM_USER_DEFORMITY_COLLECTION, document);
                    continue;
                }

                int keyIndex = 0;
                document = new Document();
                String cellValue;
                if (isFirstLine) {
                    for (String cell : row) {
                        cellValue = cell;
                        indexKeyMap.put(keyIndex, cellValue);
                        keyIndex++;
                        maxColumn++;
                    }
                } else {
                    for (int i = 0; i < maxColumn; i++) {
                        cellValue = (row[i] == null) ? "" : row[i];
                        document.append(indexKeyMap.get(keyIndex), cellValue);
                        keyIndex++;
                    }
                }

                // 保存数据到MongoDB
                MongoUtil.insertOne(document);

                isFirstLine = false;
            }
            // note that Scanner suppresses exceptions
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (sc != null) {
                sc.close();
            }
        }
        System.out.println(Thread.currentThread().getId() + " end  : " + System.currentTimeMillis());
    }

    public static void multiThread() {
        String[] excelFilePaths = {
                "/Users/cuiguiyang/eric/Java/test/B-200W-400W.csv",
                "/Users/cuiguiyang/eric/Java/test/C-400W-600W.csv",
                "/Users/cuiguiyang/eric/Java/test/D-600W-800W.csv",
                "/Users/cuiguiyang/eric/Java/test/E-800W-1000W.csv",
                "/Users/cuiguiyang/eric/Java/test/F-1000W-1200W.csv",
                "/Users/cuiguiyang/eric/Java/test/G-1200W-1400W.csv",
                "/Users/cuiguiyang/eric/Java/test/H-1400W-1600W.csv",
                "/Users/cuiguiyang/eric/Java/test/I-1600W-1800W.csv",
                "/Users/cuiguiyang/eric/Java/test/J-1800W-2000W.csv",
                "/Users/cuiguiyang/eric/Java/test/K-2000W-lasted-5W.csv"
        };
        /*
        11 start: 1501001302546
        15 start: 1501001302546
        16 start: 1501001302546
        14 start: 1501001302546
        13 start: 1501001302546
        12 start: 1501001302546
        18 start: 1501001302546
        17 start: 1501001302546
        19 start: 1501001302547
        20 start: 1501001302547
        20 end  : 1501001323629
        13 end  : 1501001872456
        16 end  : 1501001872498
        14 end  : 1501001872552
        19 end  : 1501001872606
        11 end  : 1501001872817
        12 end  : 1501001873027
        18 end  : 1501001873187
        15 end  : 1501001873208
        17 end  : 1501001873589
         */
        for (final String filePath : excelFilePaths) {
            new Thread(() -> bigFileRead(filePath)).start();
        }
    }

    /**
     * 读取大xlsx文件
     * @param filePath
     */
    private static void xlsxRead(final String filePath) {
        Assert.hasText(filePath, "文件路径不正确");
        boolean isFirstLine = true;
        Map<Integer, String> indexKeyMap = new HashMap<>();
        try {
            StopWatch watch = new StopWatch();
            watch.start();
            // Path path = Paths.get(filePath);
            // File file = Paths.get(filePath).toFile();
            InputStream is = new FileInputStream(new File(filePath));
            Workbook workbook = StreamingReader.builder()
                    .rowCacheSize(100)
                    .bufferSize(4096)
                    .open(is);
            watch.stop();
            System.out.println("load excel cost: " + (watch.getTime() / 1000) + "s");

            int firstSheetIndex = 0;
            Sheet sheet = workbook.getSheetAt(firstSheetIndex);
            System.out.println("sheet name is: " + sheet.getSheetName());
            Document document;
            int maxColumn = 0;
            System.out.println("start:" + System.currentTimeMillis());
            for (Row row : sheet) {
                int keyIndex = 0;
                document = new Document();
                String cellValue;
                if (isFirstLine) {
                    for (Cell cell : row) {
                        cellValue = cell.getStringCellValue();
                        indexKeyMap.put(keyIndex, cellValue);
                        keyIndex++;
                        maxColumn++;
                    }
                } else {
                    for (int i = 0; i < maxColumn; i++) {
                        cellValue = (row.getCell(i) == null) ? "" : row.getCell(i).getStringCellValue();
                        document.append(indexKeyMap.get(keyIndex), cellValue);
                        keyIndex++;
                    }
                }

                // 保存数据到MongoDB
                // MongoUtil.insertOne(document);
                System.out.println("total rows: " + keyIndex);

                isFirstLine = false;
            }
            System.out.println("ent  :" + System.currentTimeMillis());
            System.out.println("\n end.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
