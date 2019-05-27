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

    public static void main(String[] args) throws InterruptedException {

        int taskNum = 10000000;
        int threadNum = 50;

        Long startTime = System.currentTimeMillis();
        CyclicBarrier barrier = new CyclicBarrier(threadNum);
        CountDownLatch countDownLatch = new CountDownLatch(taskNum);

        ExecutorService executorService = Executors.newFixedThreadPool(threadNum);
        for (int i = 0; i < taskNum; i++) {
            StressTestConcurrentHashMap concurrentWrite = new StressTestConcurrentHashMap(barrier,countDownLatch);
            executorService.submit(concurrentWrite);
        }
        countDownLatch.await();
        System.out.println("concurrentHashMap执行时间:" + (System.currentTimeMillis() - startTime));
        executorService.shutdown();
    }

//    public static void main(String[] args) throws InterruptedException {
//        int taskNum = 10000000;
//        int threadNum = 50;
//
//        Long startTime = System.currentTimeMillis();
//        CyclicBarrier barrier = new CyclicBarrier(threadNum);
//        CountDownLatch countDownLatch = new CountDownLatch(taskNum);
//        ExecutorService executorService = Executors.newFixedThreadPool(threadNum);
//        for (int i = 0; i < taskNum; i++) {
//            StressTestReadWriteLock readWriteLock = new StressTestReadWriteLock(barrier, countDownLatch);
//            executorService.submit(readWriteLock);
//        }
//        countDownLatch.await();
//        System.out.println("读写锁+hashMap 执行时间:" + (System.currentTimeMillis() - startTime));
//        executorService.shutdown();
//    }

}
