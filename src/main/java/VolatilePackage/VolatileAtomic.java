package VolatilePackage;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: xiaoran
 * @date: 2018-08-22 00:27
 * <p>
 * volatile 关键字，不是原子性操作，不能用于原子性代码验证
 *
 * 若volatile关键字具有原子性，那么结果将会是100000
 * 这里，因为count++是非线程安全的，且volatile不保证原子性，所以在多线程情况下，会导致重复计算
 *
 * 采用AtomicInteger类支持原子性操作
 */
public class VolatileAtomic extends Thread {
    /**
     * volatile 关键字修饰的属性，static属性
     */
    private static volatile int count = 0;

    /**
     * 原子性类
     */
    private static AtomicInteger atomicCount = new AtomicInteger(0);

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            count++;
            atomicCount.incrementAndGet();
            atomicCount.addAndGet(10);
        }
        System.out.println(count +" count now is:"+System.currentTimeMillis());
        System.out.println(atomicCount +" atomicCount now is:"+System.currentTimeMillis());
    }

    public static void main(String[] args) {
        VolatileAtomic[] volatileAtomics = new VolatileAtomic[10];
        for (int i = 0; i < 10; i++) {
            volatileAtomics[i] = new VolatileAtomic();
        }

        for (int i = 0; i < 10; i++) {
            volatileAtomics[i].start();
        }
    }
}
