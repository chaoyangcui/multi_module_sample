package thread;

/**
 * Created by cuiguiyang on 2017/2/19 16:42.
 * Desc
 */
public class SynchronizedTest {

    class Lock {
        private boolean isLocked = true;

        public void lock() {
            synchronized (this) {
                while (isLocked) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        //do nothing, keep waiting
                    }
                }
            }
            synchronized (this) {
                isLocked = true;
            }
        }

        public synchronized void unlock() {
            isLocked = false;
            this.notify();
        }
    }

    public static void main(String[] args) {
        /*ReentrantLock fairLock = new ReentrantLock(true);
        fairLock.tryLock();
        ReentrantLock nonfairLock = new ReentrantLock(false);
        nonfairLock.tryLock();*/


        FairLock lock = new FairLock();
        try {
            lock.lock();

            new Thread(() -> {
                try {
                    Thread.sleep(500);
                    lock.lock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "demo").start();

            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        /*new Thread(() -> {
            try {
                lock.lock2();

                Thread.sleep(1000 * 10);
                lock.unlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "111").start();

        new Thread(() -> {
            try {
                Thread.sleep(5000);
                lock.lock2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "222").start();*/

    }

}
