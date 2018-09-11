package ConcurrentCollections;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author: xiaoran
 * @date: 2018-08-29 00:14
 */
public class UseQueue {
    /**
     * 非阻塞是队列，采用cas算法来保证   性能很高
     */
    ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<String>();



    /**
     * 阻塞式队列
     *
     * 数组结构的有界阻塞队列
     */
    ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<String>(16);

    /**
     * 链表结构的有界阻塞队列
     *
     * 默认 2^31 次方
     */
    LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>();




}
