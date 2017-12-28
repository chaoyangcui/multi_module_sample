package juc.semaphore;

import java.util.concurrent.Semaphore;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric
 * Date    2017/9/5 10:01
 * 哲学家用餐问
 */
public class _Semaphore {

    public static void main(String[] args) {
        // 5位哲学家的名字
        String[] names = {"骆昊", "王大锤", "张三丰", "杨过", "李莫愁"};
        /*
        // 创建固定大小的线程池
        ExecutorService es = Executors.newFixedThreadPool(AppContext.NUM_OF_PHILO);
        for (int i = 0, len = names.length; i < len; ++i) {
            // 启动线程
            es.execute(new Philosopher(i, names[i]));
        }
        es.shutdown();*/
        for (int i = 0, len = names.length; i < len; ++i) {
            new Thread(new Philosopher(i, names[i])).start();
        }
    }


    /**
     * 存放线程共享信号量的上下问
     *
     * @author 骆昊
     */
    static final class AppContext {
        public static final int NUM_OF_FORKS = 5;   // 叉子数量(资源)
        public static final int NUM_OF_PHILO = 5;   // 哲学家数量(线程)

        public static Semaphore[] forks;    // 叉子的信号量
        public static Semaphore counter;    // 哲学家的信号量

        static {
            forks = new Semaphore[NUM_OF_FORKS];
            for (int i = 0, len = forks.length; i < len; ++i) {
                forks[i] = new Semaphore(1);    // 每个叉子的信号量为1
            }

            counter = new Semaphore(NUM_OF_PHILO - 1);  // 如果有N个哲学家，最多只允许N-1人同时取叉子
        }

        /**
         * 取得叉子
         *
         * @param index     第几个哲学家
         * @param leftFirst 是否先取得左边的叉子
         * @throws InterruptedException
         */
        public static void takeUpFork(int index, boolean leftFirst) throws InterruptedException {
            if (leftFirst) {
                forks[index].acquire();
                forks[(index + 1) % NUM_OF_PHILO].acquire();
            } else {
                forks[(index + 1) % NUM_OF_PHILO].acquire();
                forks[index].acquire();
            }
        }

        /**
         * 放回叉子
         *
         * @param index     第几个哲学家
         * @param leftFirst 是否先放回左边的叉子
         * @throws InterruptedException
         */
        public static void putDownFork(int index, boolean leftFirst) throws InterruptedException {
            if (leftFirst) {
                forks[index].release();
                forks[(index + 1) % NUM_OF_PHILO].release();
            } else {
                forks[(index + 1) % NUM_OF_PHILO].release();
                forks[index].release();
            }
        }
    }

    /**
     * 哲学家
     *
     * @author 骆昊
     */
    static class Philosopher implements Runnable {
        private int index;      // 编号
        private String name;    // 名字

        public Philosopher(int index, String name) {
            this.index = index;
            this.name = name;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);

                    AppContext.counter.acquire();
                    boolean leftFirst = index % 2 == 0;
                    AppContext.takeUpFork(index, leftFirst);
                    System.out.println(name + "正在吃意大利面（通心粉）...");   // 取到两个叉子就可以进食
                    AppContext.putDownFork(index, leftFirst);
                    AppContext.counter.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }

}

