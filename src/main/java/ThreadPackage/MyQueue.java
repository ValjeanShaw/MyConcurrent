package ThreadPackage;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: xiaoran
 * @date: 2018-08-28 08:06
 *
 * 使用wait和notify模拟一个Queue
 *
 */
public class MyQueue {

    // 1.需要一个承装元素的集合
    private final LinkedList<Object> list = new LinkedList<Object>();

    //2.需要一个计数器   计数器必须要原子的
    private final AtomicInteger count = new AtomicInteger(0);

    //3.需要制定上限和下限

    private final int maxSize;
    private final int minSize = 0;

    private final Object lock = new Object();

    public MyQueue (int maxSize){
        this.maxSize = maxSize;
    }

    /**
     * 把元素加入到阻塞队列中，当达到最大值的时候，等待被唤醒，有空间了再加入
     * @param obj
     */
    public void put (Object obj) {
        synchronized(lock){
            while(count.get() == maxSize){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.add(obj);
            count.getAndIncrement();
            System.out.println(" 元素 " + obj + " 被添加 ");
            lock.notify();

        }
    }

    /**
     * 把元素取出，当达到最小值，进行阻塞，等待有元素了，被唤醒再加入
     * @return
     */
    public Object take(){
        Object temp = null;
        synchronized (lock) {
            while(count.get() == minSize){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            count.getAndDecrement();
            temp = list.removeFirst();
            System.out.println(" 元素 " + temp + " 被消费 ");
            lock.notify();
        }
        return temp;
    }

    public int size(){
        return count.get();
    }


    public static void main(String[] args) throws Exception {

        final MyQueue m = new MyQueue(5);
        m.put("a");
        m.put("b");
        m.put("c");
        m.put("d");
        m.put("e");
        System.out.println("当前元素个数：" + m.size());
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                m.put("h");
                m.put("i");
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    Object t1 = m.take();
                    //System.out.println("被取走的元素为：" + t1);
                    Thread.sleep(1000);
                    Object t2 = m.take();
                    //System.out.println("被取走的元素为：" + t2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t2");

        t1.start();
        Thread.sleep(1000);
        t2.start();

    }


}
