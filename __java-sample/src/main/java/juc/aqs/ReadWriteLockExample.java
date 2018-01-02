package juc.aqs;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric
 * Date    2017/9/6 15:04
 * Desc    Setting | Editor | File and Code Templates
 */
public class ReadWriteLockExample {
    private static final ReentrantReadWriteLock LOCK = new ReentrantReadWriteLock();
    private static final ReentrantReadWriteLock.WriteLock WRITE_LOCK = LOCK.writeLock();
    private static final ReentrantReadWriteLock.ReadLock READ_LOCK = LOCK.readLock();
    private static final ReentrantLock REENTRANT_LOCK = new ReentrantLock();

    public static void main(String[] args) {
        WRITE_LOCK.lock();
        Condition condition = WRITE_LOCK.newCondition();

        new Thread(() -> {
            WRITE_LOCK.lock();
            System.out.println(Thread.currentThread().getName() + " get LOCK.");
            condition.signal();
            // WRITE_LOCK.unlock();
        }, "sub thread").start();

        try {
            Thread.sleep(1000 * 2);
            condition.await();
            System.out.println("the main thread continues to running.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    static class CachedData {
        private static Object data;
        private static volatile boolean cacheValid;
        private static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
        private static final ReentrantReadWriteLock.ReadLock READ_LOCK = rwl.readLock();
        private static final ReentrantReadWriteLock.WriteLock WRITE_LOCK = rwl.writeLock();

        public static void main(String[] args) {
            // processData("...");
            for (int i = 0; i < 10; i++) {
                int p = i;
                new Thread(() -> processData(p), "thread-" + i).start();
            }
        }

        private static void processData(Object p) {
            READ_LOCK.lock();
            if (!cacheValid) {
                READ_LOCK.unlock();
                WRITE_LOCK.lock();
                if (!cacheValid) {
                    data = p;
                    cacheValid = true;
                }
                // Downgrade by acquiring read LOCK before releasing write LOCK
                READ_LOCK.lock();
                WRITE_LOCK.unlock();
            }
            use(data);
            READ_LOCK.unlock();
        }

        private static void processCachedData() {
            rwl.readLock().lock();
            if (!cacheValid) {
                // Must release read LOCK before acquiring write LOCK
                rwl.readLock().unlock();
                rwl.writeLock().lock();
                // Recheck state because another thread might have acquired
                //   write LOCK and changed state before we did.
                if (!cacheValid) {
                    data = "...";
                    cacheValid = true;
                }
                // Downgrade by acquiring read LOCK before releasing write LOCK
                rwl.readLock().lock();
                rwl.writeLock().unlock(); // Unlock write, still hold read
            }

            use(data);
            rwl.readLock().unlock();
        }

        private static void use(Object data) {
            System.out.println("Thread:" + Thread.currentThread().getName() + "'s data:" + data);
        }
    }
}

