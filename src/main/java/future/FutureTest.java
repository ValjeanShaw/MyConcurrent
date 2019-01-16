package future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * future + callable
 *
 * @author: xiaoran
 * @date: 2019-01-16 15:09
 */
public class FutureTest {
    public static void main(String[] args) {
        /**
         * 正常返回值的情况
         */
        ExecutorService executorService = Executors.newCachedThreadPool();
        RunByCallable runByCallable = new RunByCallable();
        Future<String> future = executorService.submit(runByCallable);
        try {
            System.out.println("callable中call方法执行的结果是：" + future.get());
            System.out.println("cancel情况： " + future.cancel(true));
            System.out.println("isCancelled情况： " + future.isCancelled());
            System.out.println("isDone情况： " + future.isDone());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        /**
         * 抛异常的情况
         */
        CallableException callableException = new CallableException();
        Future<String> future2 = executorService.submit(callableException);
        try {
            System.out.println("isDone情况： " + future2.isDone());
            System.out.println("callable中call方法执行的结果是：" + future2.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        /**
         * 超时
         */
        RunByCallable runByCallable3 = new RunByCallable();
        Future<String> future3 = executorService.submit(runByCallable3);
        try {
            System.out.println("callable中call方法执行的结果是：" + future3.get(0, TimeUnit.NANOSECONDS));
        } catch (TimeoutException e) {
            future3.cancel(true);
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
