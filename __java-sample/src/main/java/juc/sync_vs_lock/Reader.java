package juc.sync_vs_lock;

/**
 * @author Eric Cui
 * <p>
 * Created by Intellij IDEA.
 * Date  : 2018/2/26 21:03
 * Desc  : 描述信息
 */
public class Reader extends Thread {
    private Buffer buff;

    public Reader(Buffer buff) {
        this.buff = buff;
    }

    @Override
    public void run() {

        buff.read();//这里估计会一直阻塞

        System.out.println("读结束");

    }
}
