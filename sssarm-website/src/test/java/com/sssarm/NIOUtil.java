package com.sssarm;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;

/**
 * @Author Eric
 * @Date 2017/5/11 13:59
 * @Desc
 */
public class NIOUtil {
    private static String HOME = System.getProperty("user.home");

    @Test
    public void givenExistentPath_whenConfirmsFileExists_thenCorrect() {
        Path p = Paths.get(HOME);

        System.out.println(p.toString());

        assertTrue(Files.exists(p));
    }
}
