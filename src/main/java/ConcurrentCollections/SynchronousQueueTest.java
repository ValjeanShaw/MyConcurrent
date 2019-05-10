package ConcurrentCollections;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * 适合传递性场景的阻塞队列
 * 不存储数据
 * 生产者一直阻塞，直到有至少一个消费者在等待消费数据
 *
 * @author xiaoran
 * @date 2019/05/10
 */
public class SynchronousQueueTest {


    public static void main(String[] args) {
        //用于多个线程间传递消息的queue
        BlockingQueue<String> synchronousQueue = new SynchronousQueue<>();

        SynchronousQueueProducer producer = new SynchronousQueueProducer(synchronousQueue);
        new Thread(producer).start();

        SynchronousQueueConsumer consumer1 = new SynchronousQueueConsumer(synchronousQueue);
        new Thread(consumer1).start();

        SynchronousQueueConsumer consumer2 = new SynchronousQueueConsumer(synchronousQueue);
        new Thread(consumer2).start();

    }

    /**
     * 队列生产者
     */
    final static class SynchronousQueueProducer implements Runnable {
        protected BlockingQueue<String> blockingQueue;

        public SynchronousQueueProducer(BlockingQueue<String> queue) {
            this.blockingQueue = queue;
        }

        /**
         * 一直生产数据,阻塞式放入queue
         */
        @Override
        public void run() {
            while (true) {
                try {
                    String data = UUID.randomUUID().toString();
                    System.out.println("生产者：==>>" + "Put: " + data);
                    blockingQueue.put(data);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 队列消费者
     */
    final static class SynchronousQueueConsumer implements Runnable {
        protected BlockingQueue<String> blockingQueue;

        public SynchronousQueueConsumer(BlockingQueue<String> queue) {
            this.blockingQueue = queue;
        }

        /**
         * 一直消费数据，从queue中取出
         */
        @Override
        public void run() {
            while (true) {
                try {
                    String data = blockingQueue.take();
                    System.out.println("消费者：<<<<<<===" + Thread.currentThread().getName() + " take(): " + data + "\n");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
