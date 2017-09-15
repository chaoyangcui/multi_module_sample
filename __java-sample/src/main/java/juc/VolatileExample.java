package juc;

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

    public void write() {
        a = 1;
        flag = true;
    }

    public void read() {
        if (flag) {
            int i = a;
            System.out.println(i);
        }
    }

}
class VolatileBarrierExample {
    int a;
    volatile int v1 = 1;
    volatile int v2 = 2;

    void readAndWrite() {
        int i = v1;           //第一个volatile读
        int j = v2;           // 第二个volatile读
        a = i + j;            //普通写
        v1 = i + 1;          // 第一个volatile写
        v2 = j * 2;          //第二个 volatile写
    }

    //其他方法
}