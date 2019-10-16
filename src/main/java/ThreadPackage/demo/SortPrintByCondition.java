package ThreadPackage.demo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 和synchronize类似
 * 获取锁进行判断，一直判断阈值，如果不满足，就进入睡眠，如果满足，打印并退出释放锁。唤醒其它线程
 *
 * @author xiaoran
 * @date 2019/10/16
 */
public class SortPrintByCondition {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        PrintRunByConditon printThreadA = new PrintRunByConditon(0, "A", lock, condition);
        PrintRunByConditon printThreadB = new PrintRunByConditon(1, "B", lock, condition);
        PrintRunByConditon printThreadC = new PrintRunByConditon(2, "C", lock, condition);

        Thread thread = new Thread(printThreadA);
        thread.start();
        thread = new Thread(printThreadB);
        thread.start();
        thread = new Thread(printThreadC);
        thread.start();
    }
}

class PrintRunByConditon implements Runnable {
    private Lock lock;
    private Condition condition;
    private int myId;
    private String name;
    volatile static int globalCount = 0;

    public PrintRunByConditon(int myId, String name, Lock lock, Condition condition) {
        this.myId = myId;
        this.name = name;
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            lock.lock();
            try {
                //不满足条件就一直循环
                while (globalCount % 3 != myId) {
                    //获取当前锁的线程等待
                    condition.await();
                }
                System.out.println(name);
                globalCount++;
                //唤醒其它线程
                condition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
