package ConcurrentUtils;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: xiaoran
 * @date: 2018-09-01 17:34
 *
 * CyclicBarrier
 * 让⼀组线程到达⼀个屏障（也可以 叫同步点）时被阻塞，直到最后⼀个线程到达屏障时，屏障才会开 门，所有被屏障拦截的线程才会继续运⾏。
 * 有点类似中国式过马路，人凑齐了，就可以开始动了
 *
 *
 */
public class CyclicBarrierDemo implements Runnable{
    private CyclicBarrier barrier;
    private String name;

    public CyclicBarrierDemo(CyclicBarrier barrier, String name) {
        this.barrier = barrier;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000 * (new Random()).nextInt(5));
            System.out.println(name + " 准备OK.");
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(name + " Go!!");
    }

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(3);

        ExecutorService executor = Executors.newFixedThreadPool(3);

        executor.submit(new Thread(new CyclicBarrierDemo(barrier, "张全蛋")));
        executor.submit(new Thread(new CyclicBarrierDemo(barrier, "二狗子")));
        executor.submit(new Thread(new CyclicBarrierDemo(barrier, "拉布拉多")));

        executor.shutdown();

    }
}
