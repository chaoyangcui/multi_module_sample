package juc.volatile1;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric
 * Date    2017/9/25 16:09
 * Desc    Setting | Editor | File and Code Templates
 */
public class VolatileExample1 {
    public volatile int a = 1;

    public void increase() {
        a++;
    }

    public int getA() {
        return a;
    }

    public static void main(String[] args) {
        VolatileExample1 volatileExample = new VolatileExample1();
        for (int i = 0; i < 200; i++) {
            new Thread(volatileExample::increase).start();
            System.out.println(volatileExample.getA());
        }
    }

}
