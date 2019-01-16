package future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * future + callable
 *
 * @author: xiaoran
 * @date: 2019-01-16 15:09
 */
public class FutureTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        RunByCallable runByCallable = new RunByCallable();
        Future<String> result = executorService.submit(runByCallable);
        try {
            System.out.println("callable中call方法执行的结果是：" + result.get());
            System.out.println("cancel情况： "+result.cancel(true));
            System.out.println("isCancelled情况： "+result.isCancelled());
            System.out.println("isDone情况： "+result.isDone());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        CallableException callableException = new CallableException();
        Future<String> result2 = executorService.submit(callableException);
        try {
            System.out.println("isDone情况： "+result2.isDone());
            System.out.println("callable中call方法执行的结果是：" + result2.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
