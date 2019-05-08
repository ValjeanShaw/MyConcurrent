package ThreadPoolPackage;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaoran
 * @version 1.0
 * <p>
 * 自定义线程池
 * <p>
 * <p>
 * <p>
 * 饱和策略：Abort 策略, CallerRuns 策略,Discard策略，DiscardOlds策略。
 * <p>
 * 自定义拒绝策略
 */
public class MyThreadPool {

    public static void main(String[] args) {
        MyThreadPool myThreadPool = new MyThreadPool();
//        myThreadPool.createPoolOne();
//        myThreadPool.createPoolAbort();
//        myThreadPool.createPoolCallerRuns();
//        myThreadPool.createPoolDiscardOldest();
//        myThreadPool.createPoolDiscardPolicy();
//        myThreadPool.createPoolMyReject();
        myThreadPool.createPool();
    }

    /**
     * 测试线程池从初始化到最大再到空闲，其中的线程数变化
     */
    public void createPool() {
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(5);
        ThreadPoolExecutor threadPoolExecutor
                = new ThreadPoolExecutor(3, 5, 1000, TimeUnit.MILLISECONDS, workQueue);
        System.out.println("未提交线程时线程池中数量：" + threadPoolExecutor.getActiveCount());

        for (int i = 0; i < 10; i++) {
            System.out.println("当前线程池中数量："+threadPoolExecutor.getActiveCount());
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try{
                        Thread.sleep(20);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            });
        }

        try{
            Thread.sleep(10000);
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("所有任务都执行完毕后，池中的线程数量：" + threadPoolExecutor.getPoolSize());
        System.out.println("所有任务都执行完毕后，池中活跃的线程数量：" + threadPoolExecutor.getActiveCount());
        System.out.println("所有任务都执行完毕后，曾经出现过最大线程数量：" + threadPoolExecutor.getLargestPoolSize());
        threadPoolExecutor.shutdown();

    }

    /**
     * ThreadPoolExecutor必备参数：
     * 核心线程池
     * 线程池最大数
     * 保持连接时间
     * 保持连接时间的单位
     * 工作队列    BlockingQueue
     * <p>
     * ArrayBlockingQueue   有界队列
     * LinkedBlockingQueue：⼀个基于链表结构的阻塞队列  无界
     * SynchronousQueue    ⼀个不存储元素的阻塞队列
     * PriorityBlockingQueue   ⼀个具有优先级的⽆限阻塞队列
     */
    public void createPoolOne() {
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(10);
        ThreadPoolExecutor threadPoolExecutor
                = new ThreadPoolExecutor(5, 20, 1000, TimeUnit.MILLISECONDS, workQueue);

        for (int i = 0; i < 20; i++) {
            final int index = i;
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(4000);
                        System.out.println(index + ":  当前线程id:" + Thread.currentThread().getId());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(String.format("thread %d finished", index));
                }
            });
        }
        threadPoolExecutor.shutdown();

    }


    /**
     * AbortPolicy  默认策略，新任务提交时直接抛出未检查的异常RejectedExecutionException，该异常可由调用者捕获。
     */
    public void createPoolAbort() {
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(5);
        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(5, 10, 1000, TimeUnit.MILLISECONDS, workQueue, new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i < 30; i++) {
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(100);
                        System.out.println("当前线程：" + Thread.currentThread().getId() + "--->" + Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        threadPoolExecutor.shutdown();
    }

    /**
     * CallerRunsPolicy 当任务添加到线程池中被拒绝时，会在线程池当前正在运行的Thread线程池中处理被拒绝的任务。
     */
    public void createPoolCallerRuns() {

        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(5);
        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(5, 10, 1000, TimeUnit.MILLISECONDS, workQueue, new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 0; i < 30; i++) {

            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {

                        Thread.sleep(100);
                        System.out.println("  当前线程：" + Thread.currentThread().getId() + "--->" + Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        threadPoolExecutor.shutdown();
    }

    /**
     * DiscardOldestPolicy 当任务添加到线程池中被拒绝时，线程池会放弃等待队列中最旧的未处理任务，然后将被拒绝的任务添加到等待队列中。
     */
    public void createPoolDiscardOldest() {

        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(5);
        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(5, 10, 1000, TimeUnit.MILLISECONDS, workQueue, new ThreadPoolExecutor.DiscardOldestPolicy());

        for (int i = 0; i < 30; i++) {
            final int index = i;

            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                        System.out.println("当前线程编号：" + index + " 线程池数字  " + Thread.currentThread().getId() + "--->" + Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        threadPoolExecutor.shutdown();
    }

    /**
     * DiscardPolicy   当任务添加到线程池中被拒绝时，线程池将丢弃被拒绝的任务。
     */
    public void createPoolDiscardPolicy() {

        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(5);
        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(5, 10, 1000, TimeUnit.MILLISECONDS, workQueue, new ThreadPoolExecutor.DiscardPolicy());

        for (int i = 0; i < 30; i++) {
            final int index = i;
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                        System.out.println("当前线程编号：" + index + "  线程池 " + Thread.currentThread().getId() + "--->" + Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        threadPoolExecutor.shutdown();
    }


    /**
     * DiscardPolicy   当任务添加到线程池中被拒绝时，线程池将丢弃被拒绝的任务。
     */
    public void createPoolMyReject() {

        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(5);
        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(5, 10, 1000, TimeUnit.MILLISECONDS, workQueue, new MyRejected());

        for (int i = 0; i < 30; i++) {
            final int index = i;
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                        System.out.println("当前线程编号：" + index + "  线程池 " + Thread.currentThread().getId());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        threadPoolExecutor.shutdown();
    }


}


class MyRejected implements RejectedExecutionHandler {


    public MyRejected() {
    }

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("现线程池中的情况为：" + executor.getActiveCount());
        System.out.println("当前被拒绝任务为：" + r.toString());

    }

}