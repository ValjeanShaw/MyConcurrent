package LockPackage.demo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用condition实现一个有界队列
 * <p>
 * 当队列为空的时候，队列会阻塞 获取线程
 * 当队列满的时候，队列会阻塞 插入线程
 *
 * @author: xiaoran
 * @date: 2019-04-06 19:28
 */
public class BoundedQueue<T> {
    /**
     * 节点元素
     */
    private Object[] items;

    /**
     * 添加下标  删除下标  当前数量
     */
    private int addIndex = 0;
    private int removeIndex = 0;
    private int count;

    private Lock lock = new ReentrantLock();
    private Condition emptyConditon = lock.newCondition();
    private Condition fullCondition = lock.newCondition();

    public BoundedQueue(int size) {
        items = new Object[size];
    }

    /**
     * 添加一个元素，当队列满，插入线程阻塞，唤醒获取线程
     *
     * @param t
     * @throws InterruptedException
     */
    public void add(T t) throws InterruptedException {
        lock.lock();
        try {
            //队列满了，插入线程等待，当被唤醒之后，也需要再起检验是否满了，才能进行后续操作
            while (count == items.length) {
                fullCondition.await();
            }
            items[addIndex] = t;
            //成环，保证插入的数据有序
            if (++addIndex == items.length) {
                addIndex = 0;
            }
            ++count;
            //只要插入了元素，就通知获取线程取元素
            emptyConditon.signal();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 从头部开始删除元素（成环），当队列为空，获取线程阻塞，唤醒插入线程
     * @return
     * @throws InterruptedException
     */
    public T remove() throws InterruptedException {
        lock.lock();
        try {
            //队列中数据为空，获取线程阻塞，等待被唤醒
            while (count == 0) {
                emptyConditon.await();
            }
            Object o = items[removeIndex];
            if (++removeIndex == items.length) {
                removeIndex = 0;
            }
            --count;
            //通知插入线程有空位置了
            fullCondition.signal();
            return (T) o;
        } finally {
            lock.unlock();
        }
    }
}
