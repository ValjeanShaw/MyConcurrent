package ConcurrentCollections.stressTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xiaoran
 * @date 2019/05/27
 */
public class MainTest {

//    public static void main(String[] args) throws InterruptedException {
//        //使用500个并发实现
//        int initThreadNum = 1000000;
//
//        Long startTime = System.currentTimeMillis();
//        CyclicBarrier barrier = new CyclicBarrier(50);
//        CountDownLatch countDownLatch = new CountDownLatch(initThreadNum);
//
//        ExecutorService executorService = Executors.newFixedThreadPool(50);
//        for (int i = 0; i < initThreadNum; i++) {
//            StressTestConcurrentHashMap concurrentWrite = new StressTestConcurrentHashMap(barrier,countDownLatch);
//            executorService.submit(concurrentWrite);
//        }
//        countDownLatch.await();
//        System.out.println("concurrentHashMap执行时间:" + (System.currentTimeMillis() - startTime));
//        executorService.shutdown();
//
//    }

    public static void main(String[] args) throws InterruptedException {
        int initThreadNum = 1000000;
        Long startTime = System.currentTimeMillis();
        CyclicBarrier barrier = new CyclicBarrier(50);
        CountDownLatch countDownLatch = new CountDownLatch(initThreadNum);
        ExecutorService executorService = Executors.newFixedThreadPool(50);
        for (int i = 0; i < initThreadNum; i++) {
            StressTestReadWriteLock readWriteLock = new StressTestReadWriteLock(barrier, countDownLatch);
            executorService.submit(readWriteLock);
        }
        countDownLatch.await();
        System.out.println("读写锁+hashMap 执行时间:" + (System.currentTimeMillis() - startTime));
        executorService.shutdown();
    }

}
