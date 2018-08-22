package ThreadPackage;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: xiaoran
 * @date: 2018-08-22 22:20
 * <p>
 * 使用 wait和notify实现一个功能
 * <p>
 * 一个线程一直在添加元素，当元素个数到达10的时候，发出通知，唤醒其它线程
 * 另一个线程等待，接收第一个线程通知，当接收到通知后再进行之后的工作
 * <p>
 * 1.wait和notify必须配合synchronized方法一起使用
 * 2.
 */
public class WaitAndNotify {
    private volatile static List<String> list = new ArrayList<>();
    private volatile static int count = 0;

    public void listAdd() {
        count++;
        list.add("this count is:" + count);
        System.out.println("a num has been add");
    }

    public int getSize() {
        return list.size();
    }

    public static List<String> getList() {
        return list;
    }

    public static void main(String[] args) {
        WaitAndNotify waitAndNotify = new WaitAndNotify();

        final Object lock = new Object();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    try {
                        for (int i = 0; i < 10; i++) {
                            waitAndNotify.listAdd();
                            Thread.sleep(500);
                            if (waitAndNotify.getSize() == 5) {
                                lock.notify();
                                System.out.println("发了个通知");
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    if (waitAndNotify.getSize() != 5) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            System.out.println("wait被中断了");
                            e.printStackTrace();
                        }

                    }

                    for (String str : WaitAndNotify.getList()) {
                        System.out.println("this value :" + str);
                    }
                }
            }
        });

        thread2.start();
        thread1.start();

    }
}
