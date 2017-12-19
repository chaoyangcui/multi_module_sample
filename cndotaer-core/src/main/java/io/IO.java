package io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sssarm.entity.SssarmEntity;
import common.ConstantEnum;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.*;

import static common.ConstantEnum.OFFSET;
import static common.ConstantEnum.UTF8;

/**
 * Created by cuiguiyang on 2017/3/11 16:53.
 * Desc
 */
public class IO {
    public static final boolean autoFlush = true;
    public static final Charset CHARSET_UTF8 = Charset.forName("UTF-8");
    public static final Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss.SSS").create();

    public static void main(String[] args) {
//        printWriter();
//        printStream();
//        serialize();
        socket();


//        new IO().loadProperties();
    }

    // 读取配置文件
    public void loadProperties() {
        final String propertyPath = "/config/project.properties";
        final String sysNameEnKey = "system.name.en", defaultSysName = "雄州雾列，俊采星驰";

        Properties properties = new Properties();
        InputStream is = getClass().getResourceAsStream(propertyPath);
        byte[] bytes = new byte[2048];
        try {
            if (is != null) {
                properties.load(is);

                while (is.read(bytes) != -1) {
                    System.out.println(new String(bytes, CHARSET_UTF8));
                }
            }
            System.out.println(properties.getProperty(sysNameEnKey, defaultSysName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*public static void serialize() {
        SssarmEntity sssarmEntity = new SssarmEntity();
        sssarmEntity.setName("sssarm.Eric");
        sssarmEntity.setAge(33);
        sssarmEntity.setAddress("天钥桥333");
        sssarmEntity.setModifyDate(new Date());
        List<String> externals = new ArrayList<>();
        externals.add("first");
        externals.add("two");
        externals.add("three");
        Map<String, List<String>> externalAttr = new HashMap<>();
        externalAttr.put("external", externals);
        sssarmEntity.setExternalAttr(externalAttr);

        final String serializeTempName = "serialize";
        try {
            // write Object
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(serializeTempName));
            output.writeObject(sssarmEntity);
            output.flush();

            ObjectOutputStream outputNoAccessible = null;
            Constructor[] constructors = ObjectOutputStream.class.getDeclaredConstructors();
            for (Constructor constructor : constructors) {
                if (constructor.getParameterCount() == 0) {
                    System.out.println(constructors);
                    constructor.setAccessible(true);
                    outputNoAccessible = (ObjectOutputStream) constructor.newInstance();
                }
            }
            outputNoAccessible.writeObject(sssarmEntity);
            //outputNoAccessible.flush();

            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(serializeTempName));
            Object obj = objectInputStream.readObject();
            System.out.println(obj);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(GSON.toJson(sssarmEntity));

        String gsonStr = "{\"name\":\"sssarm.Eric\",\"age\":33,\"address\":\"天钥桥333\",\"modifyDate\":\"2017-03-11 21:31:17.44\",\"externalAttr\":{\"external\":[\"first\",\"two\",\"three\"]}}";
        SssarmEntity entity = GSON.fromJson(gsonStr, SssarmEntity.class);
        System.out.println(entity);
    }*/

    public static void printStream() {
        final String tfilePath = "/Users/cuiguiyang/test-printstream.txt";
        try {

            File file = Paths.get(tfilePath).toFile();
            String content = new String("test-printstream.txt + 哈喽，世界".getBytes(), CHARSET_UTF8);
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.getChannel();
            PrintStream printStream = new PrintStream(file);

            printStream.write(content.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void printWriter() {
        StringWriter stringWriter = new StringWriter();

        File file = new File("/Users/cuiguiyang/test-printwriter.txt");
        try {
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(file);
            //PrintWriter filePrintWriter = new PrintWriter(file);
            PrintWriter filePrintWriter = new PrintWriter(fileWriter, autoFlush);
            filePrintWriter.write("test content.");
            filePrintWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        PrintWriter printWriter = new PrintWriter(stringWriter, autoFlush);
        printWriter.write("Hello");

        System.out.println(stringWriter.toString());
    }

    public static void fileOperator() {
        final String fileName = "---------------.txt";
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            fileInputStream.getChannel();
            fileInputStream.getFD();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void socket() {
        StringBuffer msgBuffer;
        try {
            ServerSocket server = new ServerSocket();
            server.bind(new InetSocketAddress(ConstantEnum.HOST, ConstantEnum.PORT));
            Socket socket;
            byte[] bytes = new byte[1024];
            System.out.println("socket server start run.");
            while ((socket = server.accept()) != null) {
                System.out.println("socket server receive msg.");
                msgBuffer = new StringBuffer();
                InputStream is = socket.getInputStream();
                if (is != null) {
                    int len;
                    while ((len = is.read(bytes)) != -1) {
                        String msg = new String(bytes, OFFSET, len);
                        msgBuffer.append(msg);
                    }
                }
                System.out.print("receive msg is: \n" + URLDecoder.decode(msgBuffer.toString(), UTF8));
                System.out.println("\n-----end.\n");

                // answer the request
                final String answerMsg = "i have receive your msg.";
                socket.getOutputStream().write(URLEncoder.encode(answerMsg, UTF8).getBytes(UTF8));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
