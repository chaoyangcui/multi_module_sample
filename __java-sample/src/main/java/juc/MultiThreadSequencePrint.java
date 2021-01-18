package juc;

import java.util.concurrent.Semaphore;

/**
 * <p>Created by Intellij IDEA.
 *
 * @author Eric Cui
 * @since 2019-03-19 23:47
 */
@SuppressWarnings("all")
public class MultiThreadSequencePrint {

    static int cnt = 0;

    public static void main(String[] args) throws InterruptedException {
        Semaphore first = new Semaphore(1);

        Semaphore second = new Semaphore(1);
        Semaphore third = new Semaphore(1);


        new Thread(() -> {
            while (true) {
                if (cnt >= 100) {
                    System.exit(0);
                }
                try {
                    first.acquire();
                    second.acquire();
                    System.out.println(Thread.currentThread().getName() + cnt++);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    second.release();
                    first.release();
                }
            }
        }, "thread-1----").start();
        new Thread(() -> {
            while (true) {
                if (cnt >= 100) {
                    System.exit(0);
                }
                try {
                    second.acquire();
                    third.acquire();
                    System.out.println(Thread.currentThread().getName() + cnt++);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    third.release();
                    second.release();
                }
            }
        }, "thread-2----").start();
        new Thread(() -> {
            while (true) {
                if (cnt >= 100) {
                    System.exit(0);
                }
                try {
                    third.acquire();
                    first.acquire();
                    System.out.println(Thread.currentThread().getName() + cnt++);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    first.release();
                    third.release();
                }
            }
        }, "thread-3----").start();

    }

}
