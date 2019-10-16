package ConcurrentUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author: xiaoran
 * @date: 2018-09-02 00:21
 *
 * 信号量的问题
 * 用来控制同时访问特定资源的线程数量
 * 类似于：
 * 外面吃饭的排号系统。
 * 人多时，位置不够用，需要排号等待,这个信号量就是那个叫号的系统
 *
 * 信号量控制，只能有规定数量的进入信号量流程中
 * 信号量控制流程：
 * 信号量开始：acquire  减少一个信号量
 * 信号量结束：release  增加一个信号量
 *
 */
public class SemaphoreDemo {
    private static final int THREAD_COUNT = 30;
    private static ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);

    //控制，容量只能有10个线程
    private static Semaphore semaphore = new Semaphore(10);

    public static void main(String[] args) {
        for(int i=0;i<THREAD_COUNT;i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try{
                        //请求、
                        System.out.println("初始化："+Thread.currentThread().getName());
                        semaphore.acquire();
                        System.out.println("save data  "+Thread.currentThread().getName());
                        Thread.sleep(3000);
                        //释放
                        semaphore.release();

                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }


                }
            });

        }
        executorService.shutdown();
    }
}
