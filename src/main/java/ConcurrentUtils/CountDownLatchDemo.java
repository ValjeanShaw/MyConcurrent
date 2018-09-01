package ConcurrentUtils;

import java.util.concurrent.CountDownLatch;

/**
 * @author: xiaoran
 * @date: 2018-09-01 15:54
 *
 * 工具类 countDownLatch的使用
 *
 * 允许一个或者多个线程等待其他线程完成操作
 *功能和join()有点相似
 *
 * 需求：
 *  除主线程外，一共创建三个线程，第三个线程，需等到前两个线程执行完毕之后，才能运行
 */
public class CountDownLatchDemo {

    static CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("this is thread1，初始化啦");
                try{
                    Thread.sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("this is thread1,报告：任务完成");
                countDownLatch.countDown();
            }
        },"thread1");

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("this is thread2，我也初始化啦");
                try{
                    Thread.sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("this is thread2,报告，报告：任务完成");
                countDownLatch.countDown();
            }
        },"thread2");

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    System.out.println("this is thread3,我的任务是等待前两货执行完毕");
                    countDownLatch.await();
                    System.out.println("我叫王大锤，经过漫长的等待，我终于执行完了。。。");
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        },"王大锤");


        thread1.start();
        thread2.start();
        thread3.start();
    }

}
