package LockPackage;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author: xiaoran
 * @date: 2018-09-02 18:09
 */
public class WriteReadLock{
    public static void main(String[] args) {
        MachineNumber machineNumber = new MachineNumber();

        for(int i=0;i<100;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    machineNumber.setCountByLock((int) (Math.random() * 100));
                    System.out.println(Thread.currentThread().getId()+"设置为："+machineNumber.getCountByLock());
                }
            },"write").start();

//            try{
//                Thread.sleep(10);
//            }catch (InterruptedException e){
//                e.printStackTrace();
//            }
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(machineNumber.getCountByLock());
                }
            },"read").start();
        }


    }

}

class MachineNumber{
    private int count = 0;
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    /**
     * 获取数字   加上读锁
     * @return
     */
    public int getCountByLock(){
        lock.readLock().lock();
        try{
            return count;
        }finally {
            lock.readLock().unlock();
        }
    }

    /**
     * 写数据   加上写锁
     * @param num
     */
    public void setCountByLock(int num){
        lock.writeLock().lock();
        try{
            count = num;
        }finally {
            lock.writeLock().unlock();
        }
    }

}
