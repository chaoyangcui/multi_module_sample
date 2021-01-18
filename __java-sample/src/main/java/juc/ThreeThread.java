package juc;

import java.util.concurrent.Semaphore;

/**
 * <p>Created by Intellij IDEA.
 *
 * @author Eric Cui
 * @since 2019-03-19 23:31
 */
@SuppressWarnings("all")
public class ThreeThread {
    static volatile int a = 0;
    static final int max = 100;
    static final Object lock = new Object();

    public static void main(String[] args) {
        final Semaphore semaphore = new Semaphore(2, true);

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                for (; a < max; a++) {
                    try {
                        semaphore.acquire();
                        if (a >= max) {
                            break;
                        }
                        System.out.println(Thread.currentThread().getName() + a);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        semaphore.release();
                    }
                    sleep();
                }
            }, "thread-" + i + "----").start();

            sleep();
        }
    }

    public static void sleep() {
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
