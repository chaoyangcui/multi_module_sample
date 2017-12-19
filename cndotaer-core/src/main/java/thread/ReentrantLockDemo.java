package thread;


import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by cuiguiyang on 2017/4/11 20:58.
 * Desc
 */
public class ReentrantLockDemo {

    private static final int _1s = 1000;
    private static final ReentrantLock _LOCK = new ReentrantLock();

    public static void main(String[] args) {
        System.out.println("isLock0:" + _LOCK.isLocked());
        if (_LOCK.tryLock()) {
            _LOCK.lock();
        }

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(_1s);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                boolean bool = _LOCK.tryLock();
                if (bool) {
                    System.out.println("a new thread1 tryLock01:" + bool);
                    break;
                }
            }
        }).start();
        _LOCK.unlock();
        new Thread(() -> {
            boolean bool = _LOCK.tryLock();
            if (bool) {
                // _LOCK.lock();
            }
            System.out.println("a new thread2 tryLock02:" + _LOCK.isHeldByCurrentThread());
            try {
                Thread.sleep(_1s * 3);
                if (_LOCK.isHeldByCurrentThread()) {
                    System.out.println("HoldCount  :" + _LOCK.getHoldCount());
                    System.out.println("QueueLength:" + _LOCK.getQueueLength());
                    _LOCK.unlock();
                    System.out.println("HoldCount  :" + _LOCK.getHoldCount());
                    System.out.println("QueueLength:" + _LOCK.getQueueLength());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();



        /*new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(_1s / 2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("isLock:" + _LOCK.isLocked());
                boolean tryLock = _LOCK.tryLock();
                System.out.println("tryLock:" + tryLock);
                if (tryLock) {
                    System.out.println(Thread.currentThread().getId() + ": " + tryLock);
                    break;
                }
            }
        }).start();*/

        try {

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("isLock1:" + _LOCK.isLocked());
            if (_LOCK.isHeldByCurrentThread()) {
                _LOCK.unlock();
            }
            System.out.println("isLock2:" + _LOCK.isLocked());
        }
    }

}
