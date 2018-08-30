package ThreadPoolPackage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 最开始该线程池中的线程数为0，之后每提交一个任务就会创建一个线程，直到线程数等于指定的nThreads参数，此后线程数量将不再变化。
 */
public class FixThreadPoolDemo {

    public static void main(String[] args) {
        //创建10个核心线程池
        ExecutorService fixService = Executors.newFixedThreadPool(10);
        //15个等待执行的任务
        for(int i=0;i<15;i++){
            fixService.execute(new PrintTask(i));
        }
        //所有任务执行完毕后，关闭线程池
        fixService.shutdown();
    }

}
