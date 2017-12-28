package juc.executor;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric Cui
 * @date 2017/12/27 15:54
 * Desc    Setting | Editor | File and Code Templates
 */
public class ForkJoinTest {

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(8);
        try {
            CountTask task = new CountTask(1, 10);
            Future<String> future = forkJoinPool.submit(task);
            System.out.println(future.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    static class CountTask extends RecursiveTask<String> {
        private int left;
        private int right;

        public CountTask(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        protected String compute() {
            StringBuilder graph = new StringBuilder();

            if (right - left <= 3) {
                System.out.println("split task.......");
                for (int i = left; i <= right; i++) {
                    appendAsterisk(graph, i).append("\n");
                }
            } else {
                int right = this.left + 3;
                right = right > this.right ? this.right : right;

                CountTask leftTask = new CountTask(this.left, right);
                ForkJoinTask<String> forkJoinTask = leftTask.fork();
                System.out.println(forkJoinTask.join());
                graph.append(leftTask.join());

                CountTask rightTask = new CountTask(right + 1, this.right);
                rightTask.fork();
                graph.append(rightTask.join());
            }

            return graph.toString();
        }
    }

    private static StringBuilder appendAsterisk(StringBuilder graph, int n) {
        for (int i = 0; i < n; i++) {
            graph.append("*\t");
        }
        return graph;
    }

    static class ProcessTask extends RecursiveAction {
        @Override
        protected void compute() {
            System.out.println("recursive action.....");
        }
    }
}
