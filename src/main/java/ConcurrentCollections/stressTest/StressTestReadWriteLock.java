package ConcurrentCollections.stressTest;

import LockPackage.demo.ReadWriteCache;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 主要针对两个数据结构进行高并发性能测试，对比在读多写少情况下的性能
 * <p>
 * 1.ConcurrentHashmap
 * 2.读写锁+hashMap
 * <p>
 * 使用CountDownLatch计算耗时
 * 使用CyclicBarrier保证并发
 *
 * @author: xiaoran
 * @date: 2019-04-09 23:52
 */
public class StressTestReadWriteLock implements Runnable {

    static AtomicInteger index = new AtomicInteger(0);

    /**
     * 保证并发执行
     */
    private CyclicBarrier barrier;

    CountDownLatch countDownLatch;

    public StressTestReadWriteLock(CyclicBarrier barrier, CountDownLatch countDownLatch) {
        this.barrier = barrier;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            barrier.await();
            index.incrementAndGet();
            if (index.get() % 4 == 0) {
                ReadWriteCache.put("" + index, index.get() + 500);
            } else {
                ReadWriteCache.get(index.get() + "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            countDownLatch.countDown();
        }
    }
}