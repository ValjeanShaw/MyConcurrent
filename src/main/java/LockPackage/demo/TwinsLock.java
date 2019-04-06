package LockPackage.demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 自定义同步工具
 * <p>
 * 该工具在同一时刻，只允许最多两个线程同时访问，超过两个线程的访问将被阻塞
 *
 * @author: xiaoran
 * @date: 2019-04-06 17:00
 */
public class TwinsLock implements Lock {
    private final Sync sync = new Sync(2);

    /**
     *  重写获取和释放锁的两个方法，改造成允许多个线程
     */
    private static final class Sync extends AbstractQueuedSynchronizer {

        Sync(int count) {
            if (count <= 0) {
                throw new IllegalArgumentException("count不能小于0");
            }
            setState(count);
        }

        /**
         * 尝试获取锁
         *
         * @param reduceCount
         * @return
         */
        @Override
        public int tryAcquireShared(int reduceCount) {
            for (; ; ) {
                int current = getState();
                if(current == 0){
                    System.out.println("资源用光了");
                }
                int newCount = current - reduceCount;
                if (newCount < 0 || compareAndSetState(current, newCount)) {
                    System.out.println("当前线程"+Thread.currentThread().getName()+"自旋，正在等待资源释放。。。"+getState());
                    return newCount;
                }
            }
        }

        /**
         * 释放锁
         *
         * @param returnCount
         * @return
         */
        @Override
        public boolean tryReleaseShared(int returnCount) {
            for (; ; ) {
                int current = getState();
                int newCount = current + returnCount;
                //一直自旋，直到设置成功
                if (compareAndSetState(current, newCount)) {
                    return true;
                }
            }
        }
    }


    @Override
    public void lock() {
        sync.tryAcquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        sync.tryReleaseShared(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
