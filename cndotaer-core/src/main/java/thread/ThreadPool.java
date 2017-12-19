package thread;

import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by cuiguiyang on 2017/3/18 13:47.
 * Desc
 */
public class ThreadPool {

    static final int corePoolSize = 4;
    static final int maximumPoolSize = 10;
    static final long keepAliveTime = 1000;
    static final TimeUnit unit = TimeUnit.MILLISECONDS;
    static final int capacity = 10;


    static final ThreadPoolExecutor executor = new ThreadPoolExecutor(
            corePoolSize,
            maximumPoolSize,
            keepAliveTime,
            unit,
            new ArrayBlockingQueue<>(capacity));

    static final ReentrantLock REENTRANT_LOCK = new ReentrantLock();
    static final ReentrantReadWriteLock READ_WRITE_LOCK = new ReentrantReadWriteLock();

    public static void main(String[] args) {

        Map<String, String> map;

        final ReentrantReadWriteLock.ReadLock readLock = READ_WRITE_LOCK.readLock();
        readLock.lock();

        System.out.println(1);
        final int a = 10;
        final int b = 20;
        Runnable runnable = () -> {
            System.out.println("a+b=" + (a + b));
        };
        Future future = executor.submit(runnable);
        System.out.println(2);
        try {
            System.out.println("future: " + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        readLock.unlock();
        System.out.println(3);
    }

}
