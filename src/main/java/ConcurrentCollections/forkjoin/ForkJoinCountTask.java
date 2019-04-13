package ConcurrentCollections.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * 以一个计算方法来展示fork/join框架的用法
 *
 * 1+2+3+...+n
 *
 * @author: xiaoran
 * @date: 2019-04-13 13:51
 */
public class ForkJoinCountTask extends RecursiveTask<Integer> {

    /**
     * 分隔阈值
     */
    private static final Integer THRESHOLD = 2;

    private int start;
    private int end;

    public ForkJoinCountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        //一定要有结束条件
        if ((end - start) <= THRESHOLD) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            int middle = (start + end) / 2;
            //继续拆分 创建两个子任务
            ForkJoinCountTask leftTask = new ForkJoinCountTask(start, middle);
            ForkJoinCountTask righttask = new ForkJoinCountTask(middle + 1, end);
            //拆分成子任务执行
            leftTask.fork();
            righttask.fork();

            //等待执行完毕，返回结果
            int resultLeft = leftTask.join();
            int resultRight = righttask.join();

            sum = resultLeft + resultRight;
        }
        System.out.println("当前线程：" + Thread.currentThread().getId() + " 参数 start: " + start + "  end: " + end + " 结果：" + sum);
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        //创建计算任务
        ForkJoinCountTask countTask = new ForkJoinCountTask(1, 100);
        Future<Integer> future = forkJoinPool.submit(countTask);
        try {
            System.out.println("总计算结果: " + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
