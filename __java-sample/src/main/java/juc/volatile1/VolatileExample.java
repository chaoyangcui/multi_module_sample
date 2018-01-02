package juc.volatile1;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric
 * Date    2017/9/14 17:20
 * Desc    Setting | Editor | File and Code Templates
 */
public class VolatileExample {
    int a = 0;
    volatile boolean flag = false;
    // boolean flag = false;

    public void write() {
        a = 1;
        flag = true;
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void read() {
        if (flag) {
            int i = a;
            System.out.println(i);
        } else {
            System.out.print("flag has not been set. ");
            int i = a;
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            VolatileExample volatileExample = new VolatileExample();
            new Thread(volatileExample::write).start();
            new Thread(volatileExample::read).start();
        }
    }

}
