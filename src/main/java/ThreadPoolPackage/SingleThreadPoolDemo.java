package ThreadPoolPackage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 被提交到该线程的任务将在一个线程中串行执行，并且能确保任务可以按照队列中的顺序串行执行。
 */
public class SingleThreadPoolDemo {

    public static void main(String[] args) {
        ExecutorService singleService = Executors.newSingleThreadExecutor();
        //任务单线执行
        for(int i=0;i<15;i++){
            singleService.execute(new PrintTask(i));
        }

        singleService.shutdown();
    }
}
