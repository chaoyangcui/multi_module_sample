package juc;

import java.util.concurrent.Semaphore;

/**
 * <p>Created by Intellij IDEA.
 *
 * @author Eric Cui
 * @since 2019-03-18 21:59
 */
@SuppressWarnings("all")
public class SemaphoreDemo {

    public static void main(String[] args) throws Exception {
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + ": 是我的啦😁");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                    System.out.println(Thread.currentThread().getName() + ": 我不要了😋");
                }
            }, "thread-" + i).start();
        }

        // print();
    }

    static int result = 0;
    public static void print() throws InterruptedException {
        int N = 3;
        Thread[] threads = new Thread[N];
        final Semaphore[] syncObjects = new Semaphore[N];
        for (int i = 0; i < N; i++) {
            syncObjects[i] = new Semaphore(1);
            if (i != N - 1) {
                syncObjects[i].acquire();
            }
        }
        for (int i = 0; i < N; i++) {
            final Semaphore lastSemphore = i == 0 ? syncObjects[N - 1] : syncObjects[i - 1];
            final Semaphore curSemphore = syncObjects[i];
            final int index = i;
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            lastSemphore.acquire();
                            System.out.println("thread" + index + ": " + result++);
                            if (result > 100) {
                                System.exit(0);
                            }
                            curSemphore.release();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            threads[i].start();
        }
    }
}
