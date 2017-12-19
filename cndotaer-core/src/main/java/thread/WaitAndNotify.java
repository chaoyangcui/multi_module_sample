package thread;

/**
 * Created by Intellij IDEA.
 * Author: Eric Cui
 * Date  : 2017/8/8 21:48
 * Desc  : 描述信息
 */
public class WaitAndNotify {

    private static final Object MONITOR = new Object();
    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            synchronized (MONITOR) {
                System.out.println("t1 get MONITOR. @" + System.currentTimeMillis());
                try {
                    Thread.sleep(2000);
                    MONITOR.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t1 finally line.@" + System.currentTimeMillis());
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            synchronized (MONITOR) {
                System.out.println("t2 get MONITOR. @" + System.currentTimeMillis());

                try {
                    Thread.sleep(3000);
                    MONITOR.notify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t2");

        t1.start();
        t2.start();

    }

}
