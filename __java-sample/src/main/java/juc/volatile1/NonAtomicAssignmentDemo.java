package juc.volatile1;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * 本Demo必须使用32位Java虚拟机才能看到非原子操作的效果. <br>
 * <p>
 * 运行本Demo时也可以指定虚拟机参数“-client”
 * <p>
 * Created by Intellij IDEA.
 * Date  : 2017/12/17 16:34
 *
 * @author Eric Cui
 * Desc  : 描述信息
 */
public class NonAtomicAssignmentDemo implements Runnable {

    static long value = 0;

    private final long valueToSet;

    public NonAtomicAssignmentDemo(long valueToSet) {
        this.valueToSet = valueToSet;
    }

    public static void main(String[] args) {

        // 线程updateThread1将data更新为0
        Thread updateThread1 = new Thread(new NonAtomicAssignmentDemo(0L));
        // 线程updateThread2将data更新为-1
        Thread updateThread2 = new Thread(new NonAtomicAssignmentDemo(-1L));

        updateThread1.start();
        updateThread2.start();

        // 不进行实际输出的OutputStream
        final DummyOutputStream dos = new DummyOutputStream();

        try (PrintStream dummyPrintSteam = new PrintStream(dos)) {
            // 共享变量value的快照（即瞬间值）
            long snapshot;
            while (0 == (snapshot = value) || -1 == snapshot) {
                // 不进行实际的输出，仅仅是为了阻止JIT编译器做循环不变表达式外提优化
                dummyPrintSteam.print(snapshot);
            }

            System.err.printf("Unexpected data: %d(0x%016x)", snapshot, snapshot);
        }

        System.exit(0);
    }

    static class DummyOutputStream extends OutputStream {
        @Override
        public void write(int b) throws IOException {
            // 不实际进行输出
        }
    }

    @Override
    public void run() {
        for (; ; ) {
            value = valueToSet;
        }
    }
}
