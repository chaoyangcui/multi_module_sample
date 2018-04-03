package juc.semaphore;

import java.util.concurrent.Semaphore;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric
 * @since 2018/4/3 16:07
 */
public class SemaphoreTest {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(0);
        Runnable release =
                () -> {
                    try {
                        Thread.sleep(500L);
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                };
        new Thread(release).start();

        try {
            semaphore.acquire();
            System.out.println("acquire success");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
