package juc.volatile1;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric
 * Date    2017/9/25 16:09
 * Desc    Setting | Editor | File and Code Templates
 */
public class VolatileExample1 {
    public int a = 0;

    public void increase() {
        a++;
    }

    public int getA() {
        return a;
    }

    public static void main(String[] args) {
        // final CountDownLatch countDownLatch = new CountDownLatch(2000);
        VolatileExample1 volatileExample = new VolatileExample1();
        for (int i = 0; i < 3000; i++) {
            new Thread(() -> {
                /*countDownLatch.countDown();
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                volatileExample.increase();
            }).start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("volatileExample.a: " + volatileExample.a);
        // System.out.println("volatileExample.getA: " + volatileExample.getA());
    }

}
