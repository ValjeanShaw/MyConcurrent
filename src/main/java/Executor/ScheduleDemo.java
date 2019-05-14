package Executor;

import ThreadPoolPackage.PrintTask;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleDemo {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);

        //隔xx时间再执行。只执行一次
//        scheduledExecutorService.schedule(new PrintTask(),3, TimeUnit.SECONDS);


        //首次5秒后打印，每隔1秒打印一次   执行任务时间比间隔时间长，推迟延迟时间，那么等任务执行完毕后，立即执行下一任务
//        scheduledExecutorService.scheduleAtFixedRate(new PrintTask(2), 5, 1, TimeUnit.SECONDS);

//首次5秒后打印，每隔1秒打印一次   执行任务时间比间隔时间长，推迟延迟时间，那么等任务执行完毕后，再等待间隔时间后执行下一任务
        scheduledExecutorService.scheduleWithFixedDelay(new PrintTask("thread"), 5, 1, TimeUnit.SECONDS);
    }

}
