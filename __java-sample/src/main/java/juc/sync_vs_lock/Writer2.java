package juc.sync_vs_lock;

/**
 * @author Eric Cui
 * <p>
 * Created by Intellij IDEA.
 * Date  : 2018/2/26 21:02
 * Desc  : 描述信息
 */
public class Writer2 extends Thread {
    private BufferInterruptibly buff;

    public Writer2(BufferInterruptibly buff) {
        this.buff = buff;
    }

    @Override
    public void run() {
        buff.write();
    }
}
