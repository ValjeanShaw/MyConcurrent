package ConcurrentCollections;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 阻塞队列
 *
 * @author: xiaoran
 * @date: 2018-08-29 00:14
 */
public class UseQueue {

    /**
     * 阻塞式队列
     * <p>
     * 数组结构的有界阻塞队列
     */
    Queue<String> arrayBlockingQueue = new ArrayBlockingQueue<String>(16);

    /**
     * 公平锁的阻塞队列
     * <p>
     * 先访问的最先获取   但是会降低队列的吞吐量
     */
    Queue<String> arrayBlockingQueue2 = new ArrayBlockingQueue<>(16, true);

    /**
     * 链表结构的有界阻塞队列
     * <p>
     * 最大长度  默认 2^31 次方，即 Integer.MAX_VALUE
     */
    Queue<String> linkedBlockingQueue = new LinkedBlockingQueue<>();


    /**
     * 阻塞队列的四种处理方式
     * 1.异常
     * 2.返回特殊值
     * 3.一直阻塞
     * 4.超时退出
     * <p>
     * 以arrayBlockingQueue为例展示
     */
    public static void main(String[] args) {
        UseQueue useQueue = new UseQueue();
//        useQueue.testException();
//        useQueue.testReturnValue();
        useQueue.testBlock();
    }

    /**
     * 异常
     */
    public void testException() {
        UseQueue useQueue = new UseQueue();

        useQueue.arrayBlockingQueue.add("123");
        useQueue.arrayBlockingQueue.add("abc");
        useQueue.arrayBlockingQueue.add("789");
        useQueue.arrayBlockingQueue.add("efg");
        useQueue.arrayBlockingQueue.add("~!@");
        useQueue.arrayBlockingQueue.add("<>?");
        useQueue.arrayBlockingQueue.add("4-5-6");

        /**
         * 抛出异常
         * 插入  add(e)            队列满，再插入抛异常
         * 移出  remove()          队列不为空，返回队列头元素，队列空，移出抛异常
         * 检查  element()         队列不为空，返回队列头元素，但不移出，队列为空，抛出异常
         *
         */
        try {
            for (int i = 0; i < 20; i++) {
                useQueue.arrayBlockingQueue.add("4-5-6");
                useQueue.arrayBlockingQueue.add("4-5-6");
                useQueue.arrayBlockingQueue.add("4-5-6");
                useQueue.arrayBlockingQueue.add("4-5-6");
                useQueue.arrayBlockingQueue.add("4-5-6");
                useQueue.arrayBlockingQueue.add("4-5-6");
            }
        } catch (Exception e) {
            System.out.println("添加抛异常了");
            e.printStackTrace();
        }

        try {
            for (int i = 0; i < 20; i++) {
                System.out.println(useQueue.arrayBlockingQueue.remove());
            }
        } catch (Exception e) {
            System.out.println("删除抛异常了");
            e.printStackTrace();
        }

        try {
            useQueue.arrayBlockingQueue.element();
        } catch (Exception e) {
            System.out.println("element  当队列为空，抛异常");
            e.printStackTrace();
        }
    }

    /**
     * 返回特殊值
     */
    public void testReturnValue() {
        UseQueue useQueue = new UseQueue();

        useQueue.arrayBlockingQueue.add("123");
        useQueue.arrayBlockingQueue.add("abc");
        useQueue.arrayBlockingQueue.add("789");
        useQueue.arrayBlockingQueue.add("efg");
        useQueue.arrayBlockingQueue.add("~!@");
        useQueue.arrayBlockingQueue.add("4-5-6");

        /**
         * 抛出异常
         * 插入  offer(e)       返回值   满：true  不满：false
         * 移出  poll()         返回值   不空：队列头元素  空：null
         * 检查  peek()         返回值   不空：队列头元素  空：null
         *
         */
        for (int i = 0; i < 10; i++) {
            System.out.println(useQueue.arrayBlockingQueue.offer("4-5-6"));
            System.out.println(useQueue.arrayBlockingQueue.offer("<>?"));
            System.out.println(useQueue.arrayBlockingQueue.offer("4-5-6"));
            System.out.println(useQueue.arrayBlockingQueue.offer("<>?"));
        }

        for (int i = 0; i < 20; i++) {
            System.out.println(useQueue.arrayBlockingQueue.poll());
        }

        System.out.println("peek的返回值:" + useQueue.arrayBlockingQueue.peek());
    }

    /**
     * 阻塞
     */
    public void testBlock() {
        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<String>(16);

        arrayBlockingQueue.add("123");
        arrayBlockingQueue.add("abc");
        arrayBlockingQueue.add("efg");
        arrayBlockingQueue.add("~!@");
        arrayBlockingQueue.add("4-5-6");

        /**
         * 抛出异常
         * 插入  put(e)         返回值   满：阻塞  不满：不阻塞
         * 移出  take()         返回值   空：阻塞  不空：不阻塞
         *
         */
        for (int i = 0; i < 20; i++) {
            try {
                System.out.println(arrayBlockingQueue.size());
                arrayBlockingQueue.put("00000000");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 超时退出
     * offer(e,time,unit)
     * poll(time,unit)
     */
    public void testTimeReturn() {

    }
}
