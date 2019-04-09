package FuturePackage;

import java.util.concurrent.Callable;

/**
 * Callable在java 1.5之后提供
 *
 * 实际属于Executor框架中的功能类
 *
 * Callable接口与Runnable接口的功能类似，但提供了比Runnable更加强大的功能。
 *
 * 1. Callable可以在任务结束的时候提供一个返回值，Runnable无法提供这个功能
 * 2. Callable的call方法分可以抛出异常，而Runnable的run方法不能抛出异常。
 *
 * @author: xiaoran
 * @date: 2018-12-12 20:20
 */
public class RunByCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "hello,im callable";
    }

}
