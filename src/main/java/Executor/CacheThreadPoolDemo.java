package Executor;

import ThreadPoolPackage.PrintTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 *会为每个任务都分配一个线程(容量很大)，但是如果一个线程执行完任务后长时间(60秒)没有新的任务可执行，该线程将被回收。
 *
 * 是一个大小无界的线程池，适用于
 * 负载较轻的服务
 * 执行很多短期异步任务的服务
 */
public class CacheThreadPoolDemo {

    public static void main(String[] args) {
        ExecutorService cacheService = Executors.newCachedThreadPool();
        for(int i=0;i<20;i++){
            cacheService.execute(new PrintTask(i));
        }

    }

}
