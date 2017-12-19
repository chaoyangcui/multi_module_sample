package com.sssarm.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;

/**
 * Created by cuiguiyang on 2017/1/29 23:38.
 * Desc
 */
public class Util implements Runnable {
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public void run() {
        System.out.println();
    }

    public static void main(String[] args) {
        Util util = new Util();
        util.setKey("key");
        System.out.println(String.format("thread %s print: ", Thread.currentThread().getId()) + util.getKey());

        Runnable r = () -> {
            String result = util.getKey();
            System.out.println(String.format("thread %s print: ", Thread.currentThread().getId()) + result);
        };
        for (int i = 0; i < 5; i++) {
            new Thread(r).start();
        }
    }

    public static final ObjectMapper OBJECT_MAPPER;
    static {
        OBJECT_MAPPER = new ObjectMapper();
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        OBJECT_MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, true);
    }

}
