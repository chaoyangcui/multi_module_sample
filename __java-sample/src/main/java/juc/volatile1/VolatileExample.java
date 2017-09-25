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
class VolatileBarrierExample {
    int a;
    volatile int v1 = 1;
    volatile int v2 = 2;

    void readAndWrite() {
        int i = v1;           // 第一个volatile读
        int j = v2;           // 第二个volatile读
        a = i + j;            // 普通写
        v1 = i + 1;           // 第一个volatile写
        v2 = j * 2;           // 第二个 volatile写
    }

    //其他方法
}