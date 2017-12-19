package base;

import java.util.concurrent.CountDownLatch;

/**
 * Created by cuiguiyang on 2017/2/19 18:21.
 * Desc
 */
public class CountDownLatchDemo {
    static final int count = 5;
    static CountDownLatch countDownLatch = new CountDownLatch(count);

    static boolean whetherContinue = true;

    public static void main(String[] args) {

        CountDownThread.CountDownHandler.COUNT_DOWN_THREAD.start();

        try {
            countDownLatch.await();
            whetherContinue = false;
            System.out.println("≈≈≈≈≈≈≈≈≈≈≈≈over.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class CountDownThread extends Thread {
        final int second = 1;
        static int loopTimes = 0;
        @Override
        public void run() {
            System.out.println("thread start.");
            try {
                for (;;) {
                    loopTimes++;
                    if (!whetherContinue) {
                        break;
                    }
                    countDownLatch.countDown();
                    final int millis = second * 1000;
                    //Thread.sleep(millis);
                }
                System.out.println("loop times: " + loopTimes);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static final class CountDownHandler {
            public static final CountDownThread COUNT_DOWN_THREAD = new CountDownThread();
        }
    }
}
