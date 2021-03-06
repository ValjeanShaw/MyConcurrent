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
public class WaitAndNotifyExample {
    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args) {
        Thread notifyThead = new Thread(new Notify(), "notifyThread");
        Thread waitThead = new Thread(new Wait(), "waitThead");
        Thread waitLongTimeThread = new Thread(new WaitLongTime(), "waitLongTime");

        waitThead.start();
        waitLongTimeThread.start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        notifyThead.start();
    }


    static class Notify implements Runnable {

        @Override
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


    static class Wait implements Runnable {

        @Override
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

    static class WaitLongTime implements Runnable {

        @Override
        public void run() {
            //超时等待
            synchronized (lock){
                while (flag) {
                    try {
                        System.out.println(Thread.currentThread() + "flag is true,wait long long times:" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        //释放锁
                        lock.wait(1000);
                        System.out.println("老子等不了了。。。");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("去你的long long ago,老子不等了，先出来了");
            }
        }
    }
}


