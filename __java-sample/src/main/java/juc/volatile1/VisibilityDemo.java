package juc.volatile1;

/**
 * Created by Intellij IDEA.
 * Date  : 2017/12/17 16:51
 *
 * @author Eric Cui
 * Desc  : 描述信息
 */
public class VisibilityDemo {
    public static void main(String[] args) throws InterruptedException {

        CountingThread backgroundThread = new CountingThread();
        backgroundThread.start();

        Thread.sleep(1000);

        backgroundThread.cancel();
        backgroundThread.join();

        System.out.printf("count:%s", backgroundThread.count);
    }


    static class CountingThread extends Thread {
        //线程停止标志
        private volatile boolean ready = false;

        public int count = 0;

        @Override
        public void run() {
            while (!ready) {
                count++;
            }
        }

        public void cancel() {
            ready = true;
        }
    }
}

