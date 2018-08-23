package ThreadPackage;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: xiaoran
 * @date: 2018-08-23 23:42
 *
 * 线程变量，是一个存储结构，用于存储当前线程的变量，各个线程之间隔离，不可见
 */
public class ThreadLocalTest {

    public static void main(String[] args) {
        Thread thread1 = new Thread(new MyRunnable(),"run_1");
        Thread thread2 = new Thread(new MyRunnable(),"run_2");

        thread1.start();
        thread2.start();
    }
}

class MyRunnable implements Runnable{
    static AtomicInteger count = new AtomicInteger(0);

    /**
     * 寄存一把钥匙放在这里
     */
    private ThreadLocal<String> threadLocalKey = new ThreadLocal<>();

    public void remove(){
        threadLocalKey.remove();
    }


    @Override
    public void run() {
        threadLocalKey.set("key"+(count.addAndGet(10)));
        System.out.println(Thread.currentThread().getName() + ":" + threadLocalKey.get());
    }
}