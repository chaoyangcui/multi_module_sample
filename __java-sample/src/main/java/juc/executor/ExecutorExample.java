package juc.executor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric
 * Date    2017/9/15 15:20
 * Desc    Setting | Editor | File and Code Templates
 */
public class ExecutorExample {
    private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

    // runState is stored in the high-order bits
    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;

    // Packing and unpacking ctl
    private static int runStateOf(int c)     { return c & ~CAPACITY; }
    private static int workerCountOf(int c)  { return c & CAPACITY; }
    private static int ctlOf(int rs, int wc) { return rs | wc; }

    public static void main(String[] args) {
        // System.out.println(Integer.toBinaryString(~CAPACITY));
        // System.out.println(Integer.toBinaryString(CAPACITY));
        // System.out.println(Integer.toBinaryString(-1));
        // System.out.println(Integer.toBinaryString(RUNNING));

        printBinary(1);
        // -1
        // 原码
        // 10000000000000000000000000000001
        // 反码
        // 11111111111111111111111111111110
        // 补码
        // 11111111111111111111111111111111
    }

    public static void printBinary(int i) {
        String binary = Integer.toBinaryString(i);
        System.out.println(String.format("%32s", binary).replace(" ", "0"));
    }

    private static final int corePoolSize = 3;
    private static final int maximumPoolSize = 6;
    private static final long keepAliveTime = 0L;
    private static final BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(10);
    private static final ThreadFactory threadFactory = Executors.defaultThreadFactory();
    public static void main1(String[] args) {
        // ThreadPoolExecutor executor =
        //         new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
        //                 keepAliveTime, TimeUnit.MILLISECONDS, workQueue, threadFactory);
        // int tmp = 0;
        // try {
        //     for (int i = 1; i <= 20; i++) {
        //         final int a = i;
        //         tmp = i;
        //         executor.execute(() -> System.out.println("ThreadPoolExecutor.executor(Runnable) " + a));
        //     }
        // } catch (Exception ignore) {
        //     try {
        //         Thread.sleep(1000);
        //         final int a = tmp;
        //         executor.execute(() -> System.out.println("ThreadPoolExecutor.executor(Runnable) " + a));
        //     } catch (InterruptedException e) {
        //         e.printStackTrace();
        //     }
        // }
        ThreadPoolExecutor executor =
                new ThreadPoolExecutor(1, 1,
                        keepAliveTime, TimeUnit.MILLISECONDS, workQueue, threadFactory);
        executor.execute(() -> System.out.println("ThreadPoolExecutor.executor(Runnable) 1"));
        executor.execute(() -> System.out.println("ThreadPoolExecutor.executor(Runnable) 2"));
        /*Future<String> future = executor.submit(() -> {
        }, "result");
        try {
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }*/

        /*int t = 0;
        for (;;) {
            Future<String> future1 = executor.submit(new CallableExample("result-2"));
            try {
                System.out.println(future1.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            if (t > 10) {
                break;
            }
            t++;
        }*/
    }

    static class CallableExample implements Callable<String> {
        private String result;
        public CallableExample(String result) {
            this.result = result;
        }

        @Override
        public String call() throws Exception {
            Thread.sleep(3000);
            return this.result;
        }
    }
}
