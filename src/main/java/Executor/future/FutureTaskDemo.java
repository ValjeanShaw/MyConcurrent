package Executor.future;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * future   均实现了 Future接口和Runnable接口
 * <p>
 * 使用：
 * 1.Executor执行
 * 2.当做Runnable直接执行，  FutureTask.run()
 * <p>
 * 以下案例展示FutureTask使用：
 * 假设有多个线程执⾏若⼲任务，每个任务最多只能被执⾏⼀次。
 * 当多个线程试图同时执⾏同⼀个任务时，只允许⼀个线程执⾏任务，其他线程需要等待这个任务 执⾏完后才能继续执⾏。
 *
 * @author xiaoran
 * @date 2019/05/16
 */
public class FutureTaskDemo {
    /**
     * 任务缓存器   存储当前任务和正在执行线程的键值对
     */
    private final Map<Object, Future<String>> taskCacheMap = new ConcurrentHashMap<>();

    private String execTask(String taskName) {
        while (true) {
            Future<String> future = taskCacheMap.get(taskName);
            //针对没有线程的任务，创建future
            if (future == null) {
                Callable<String> task = new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        return taskName;
                    }
                };
                FutureTask<String> futureTask = new FutureTask<>(task);
                //如果有值，不进行替换，返回原来的value   如果没有值，返回 null
                future = taskCacheMap.putIfAbsent(taskName,futureTask);
                if(future == null){
                    future = futureTask;
                    futureTask.run();
                }
            }
            try {
                //阻塞式获取任务结果
                return future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }


}
