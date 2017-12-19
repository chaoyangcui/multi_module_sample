package base;

import java.util.concurrent.TimeUnit;

/**
 * Created by cuiguiyang on 2017/3/8 21:47.
 * Desc
 */

public class ThreadTest {
    private static boolean stopRequested;

    public static void main(String[] args) throws InterruptedException {
        Thread backgroundThread = new Thread(() -> {
            int i = 0;
            while (!stopRequested){
                i++;
                //这段System.out语句会导致线程结束，原因？
                System.out.println(i);
            }
        });
        backgroundThread.start();
        TimeUnit.SECONDS.sleep(1);
        stopRequested = true;
    }

}