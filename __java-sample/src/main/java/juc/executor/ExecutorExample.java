package juc.executor;

import java.util.concurrent.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric
 * Date    2017/9/15 15:20
 * Desc    Setting | Editor | File and Code Templates
 */
public class ExecutorExample {

    public static void main(String[] args) {
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        // executor.execute(() -> {});

        /*Future<String> future = executor.submit(() -> {
        }, "result");
        try {
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }*/

        int t = 0;
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
        }
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
