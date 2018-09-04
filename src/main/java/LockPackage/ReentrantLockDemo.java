package LockPackage;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: xiaoran
 * @date: 2018-09-02 17:54
 *
 * 重入锁
 *
 */
public class ReentrantLockDemo implements Runnable{

    public static ReentrantLock lock = new ReentrantLock();
    public static int i=0;

    @Override
    public void run() {
        for(int i=0;i<1000;i++){
            lock.lock();
            //可多重加锁
            lock.lock();
            lock.lock();
            try{
                i++;
            }finally {
                //释放锁
                lock.unlock();
                lock.unlock();
                lock.unlock();
            }

        }

    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockDemo test = new ReentrantLockDemo();

        Thread t1 = new Thread(test);
        Thread t2 = new Thread(test);
        t1.start();
        t2.start();

        t1.join();
        t2.join();
        // main线程会等待t1和t2都运行完再执行以后的流程
        System.err.println(i);
    }


}
