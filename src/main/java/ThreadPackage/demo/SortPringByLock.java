package ThreadPackage.demo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * lock方式打印。
 * lock控制同一时间只能有一个线程在打印，是否打印需要满足条件，不满足条件的跳过，释放锁
 *
 * @author xiaoran
 * @date 2019/10/16
 */
public class SortPringByLock {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        PrintRunByLock printThreadA = new PrintRunByLock(0, "A", lock);
        PrintRunByLock printThreadB = new PrintRunByLock(1, "B", lock);
        PrintRunByLock printThreadC = new PrintRunByLock(2, "C", lock);

        Thread thread = new Thread(printThreadA);
        thread.start();
        thread = new Thread(printThreadB);
        thread.start();
        thread = new Thread(printThreadC);
        thread.start();
    }


}

class PrintRunByLock implements Runnable {
    private Lock lock;
    private int myId;
    private String name;
    volatile static int globalCount = 0;

    public PrintRunByLock(int myId, String name, Lock lock) {
        this.myId = myId;
        this.name = name;
        this.lock = lock;

    }

    @Override
    public void run() {
        for (int i = 0; i < 10; ) {
            lock.lock();
            try {
                if (globalCount % 3 == myId) {
                    System.out.println(name);
                    globalCount++;
                    i++;
                }
            } finally {
                lock.unlock();
            }
        }
    }
}