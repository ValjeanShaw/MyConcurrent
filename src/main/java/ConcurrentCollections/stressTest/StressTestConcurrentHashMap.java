package ConcurrentCollections.stressTest;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 主要针对两个数据结构进行高并发性能测试，对比在读多写少情况下的性能
 * <p>
 * 1.ConcurrentHashmap
 * 2.读写锁+hashMap
 *
 * 使用CountDownLatch计算耗时
 * 使用CyclicBarrier保证并发
 *
 * @author: xiaoran
 * @date: 2019-04-09 23:52
 */
public class StressTestConcurrentHashMap implements Runnable {
    //使用500个并发实现
    private static int initThreadNum = 100000;

    static ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>(1000);
    static AtomicInteger index = new AtomicInteger(0);

    /**
     * 保证执行完毕后再计算总时间
     */
    static CountDownLatch countDownLatch = new CountDownLatch(initThreadNum);

    /**
     * 保证并发执行
     */
    private CyclicBarrier barrier;

    public StressTestConcurrentHashMap(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            barrier.await();
            //执行20ms耗时操作
            Thread.sleep(20);
            index.incrementAndGet();
            if (index.get() % 10 == 0) {
                concurrentHashMap.put("" + index, index.get() + 500);
            } else {
                concurrentHashMap.get(index);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            countDownLatch.countDown();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            concurrentHashMap.put("" + i, i);
        }
        Long startTime = System.currentTimeMillis();
        CyclicBarrier barrier = new CyclicBarrier(50);

        ExecutorService executorService = Executors.newFixedThreadPool(50);
        for (int i = 0; i < initThreadNum; i++) {
            StressTestConcurrentHashMap concurrentWrite = new StressTestConcurrentHashMap(barrier);
            executorService.submit(concurrentWrite);
        }
        countDownLatch.await();
        System.out.println("concurrentHashMap执行时间:" + (System.currentTimeMillis() - startTime));
        executorService.shutdown();
    }
}
