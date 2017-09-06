package juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Intellij IDEA.
 * Author: Eric Cui
 * Date  : 2017/9/5 22:07
 * Desc  : 描述信息
 */
public class JUC {
    private static Lock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    public static void main(String[] args) {

        for (int i = 0; i < 20; i++) {
            int tmp = i;
            new Thread(() -> {
                String threadName = Thread.currentThread().getName();
                try {
                    lock.lock();
                    System.out.println(threadName + " lock.");
                    System.out.println(threadName + " condition await.");
                    if (tmp + 1 < 20) {
                        condition.await();
                        System.out.println(threadName + " after condition await.");
                    }
                    Thread.sleep(1000 * 3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (tmp + 1 == 20) {
                        condition.signal();
                        condition.signalAll();
                    }
                    lock.unlock();
                }
            }, "Thread Sub-" + i).start();
        }

        try {
            lock.lock();
            System.out.println("main lock.");
            condition.await();
            System.out.println("main after condition await.");
            Thread.sleep(1000 * 2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
