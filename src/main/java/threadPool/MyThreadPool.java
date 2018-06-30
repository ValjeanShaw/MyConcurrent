package threadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 600006
 * @version 1.0
 *

 *
 *
 */
public class MyThreadPool {
    public static void main(String[] args) {
        MyThreadPool myThreadPool = new MyThreadPool();
        myThreadPool.createPoolOne();
    }

    /**
     *  ThreadPoolExecutor必备参数：
     * 核心线程池
     * 线程池最大数
     * 保持连接时间
     * 保持连接时间的单位
     * 工作队列    BlockingQueue
     *
     * ArrayBlockingQueue   有界队列
     *  LinkedBlockingQueue：⼀个基于链表结构的阻塞队列  无界
     *  SynchronousQueue    ⼀个不存储元素的阻塞队列
     *  PriorityBlockingQueue   ⼀个具有优先级的⽆限阻塞队列
     *
     */
    public void createPoolOne(){
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(10);
        ThreadPoolExecutor threadPoolExecutor
                = new ThreadPoolExecutor(5,20,1000, TimeUnit.MILLISECONDS,workQueue);

        for (int i = 0; i < 20; i++) {
            final int index = i;
            threadPoolExecutor.execute(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(4000);
                        System.out.println(index+":  当前线程id:"+Thread.currentThread().getId());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(String.format("thread %d finished", index));
                }
            });
        }
        threadPoolExecutor.shutdown();

    }
}
