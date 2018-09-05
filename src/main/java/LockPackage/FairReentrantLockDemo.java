package LockPackage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: xiaoran
 * @date: 2018-09-05 22:50
 *
 *
 * 公平锁的测试，先阻塞住的重入锁，锁释放后，先进行获取
 */
public class FairReentrantLockDemo implements Runnable{
    private static AtomicInteger count = new AtomicInteger(1000);

    //设置为公平锁，默认为非公平锁

    public static ReentrantLock fairLock = new ReentrantLock(true);

    @Override
    public void run() {
        fairLock.lock();
        try{
            System.out.println(Thread.currentThread().getId() +"---->"+ count);
            count.decrementAndGet();
        }finally {
            fairLock.unlock();
        }

    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(100);
        for(int i=0;i<100;i++){
            service.execute(new FairReentrantLockDemo());
        }
        service.shutdown();
    }



}
