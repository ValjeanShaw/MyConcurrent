package LockPackage.demo;

import java.util.concurrent.locks.Lock;

/**
 * @author: xiaoran
 * @date: 2019-04-06 17:59
 */
public class TwinsLockTest implements Runnable {
    static Lock lock = new TwinsLock();

    @Override
    public void run() {
        while (true) {
            lock.lock();

            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }


    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            TwinsLockTest twinsLockTest = new TwinsLockTest();
            Thread thread = new Thread(twinsLockTest);
            thread.setDaemon(true);
            thread.start();
            //试验中断，当在获取锁的自旋过程中，即使已经中断，但一直自旋，等待自己被调用的时候再去处理中断
            if(i == 5){
                thread.interrupt();
            }
        }

        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
                System.out.println();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
