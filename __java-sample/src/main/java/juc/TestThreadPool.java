package juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * <p>Created by Intellij IDEA.
 *
 * @author Eric Cui
 * @since 2019-03-24 23:40
 */
@SuppressWarnings("all")
public class TestThreadPool {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(
                () -> {
                    System.out.println(Thread.currentThread() + "============================1");
                });
        TimeUnit.SECONDS.sleep(1);

        executorService.submit(
                new Runnable() {
                    @Override
                    public void run() {
                        int a = 0;
                        while (true) {
                            a++;
                            System.out.println(Thread.currentThread() + "============================" + a);
                            if (a == 10) {
                                // System.out.println(a / 0);
                                try {
                                    System.out.println(a / 0);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    System.out.println(Thread.currentThread() + "============================" + e);
                                }
                            }
                            if (a == 20) {
                                break;
                            }
                        }
                    }
                });
    }
}
