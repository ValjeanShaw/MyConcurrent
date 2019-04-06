package LockPackage;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * condition接口的使用
 *
 * @author: xiaoran
 * @date: 2019-04-06 19:23
 */
public class ConditionTest {
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void conditionWait() throws InterruptedException {
        lock.lock();
        try {
            //获取锁的当前线程，进入等待状态知道被通知
            condition.await();
        } finally {
            lock.unlock();
        }
    }

    public void contidionSingal() throws InterruptedException {
        lock.lock();
        try {
            //唤醒一个等待在condition上的线程
            condition.signal();
        } finally {
            lock.unlock();
        }
    }
}
