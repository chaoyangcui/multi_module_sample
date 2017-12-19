package jvmtool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by cuiguiyang on 2017/3/25 21:12.
 * Desc
 */
public class JConsoleThread {

    /**
     * 线程死循环
     */
    public static void createBustThread() {
        Thread thread = new Thread(() -> {
            while (true) {

            }
        }, "testBustThread");

        thread.start();
    }

    public static void createLockThread(final Object lock) {
        Thread thread = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "testLockThread");

        thread.start();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        bufferedReader.readLine();
        createBustThread();
        bufferedReader.readLine();
        Object lock = new Object();
        createLockThread(lock);
    }

}
