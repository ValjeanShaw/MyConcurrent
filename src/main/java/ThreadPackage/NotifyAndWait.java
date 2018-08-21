package ThreadPackage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaoran
 * @version 1.0
 * <p>
 * 经典范式
 * <p>
 * <p>
 * 等待方：
 * 1.获取对象锁
 * 2.如果条件不满足，调用wait()方法
 * 3.条件满足执行相关逻辑
 * <p>
 * 通知方：
 * 1.获取对象锁
 * 2.改变条件
 * 3.发送通知  notify() 和 notifyAll()方法
 */
public class NotifyAndWait {
    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args) {
        Thread notifyThead = new Thread(new notify(), "notifyThread");
        Thread waitThead = new Thread(new wait(), "waitThead");

        waitThead.start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        notifyThead.start();
    }


    static class notify implements Runnable {

        public void run() {
            synchronized (lock) {
                //flag变为false
                System.out.println(Thread.currentThread() + "  " + new SimpleDateFormat("HH:mm:ss").format(new Date()));

                flag = false;
                lock.notifyAll();
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            System.out.println("123");


        }
    }


    static class wait implements Runnable {

        public void run() {
            //flag为false时，执行任务
            synchronized (lock) {
                while (flag) {
                    try {
                        System.out.println(Thread.currentThread() + "flag is true,wait:" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        //释放锁
                        lock.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                //flag变为false
                System.out.println(Thread.currentThread() + "任务完成flag is false,wait:" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }

        }
    }
}


