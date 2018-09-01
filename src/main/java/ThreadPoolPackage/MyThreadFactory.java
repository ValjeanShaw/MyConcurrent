package ThreadPoolPackage;

import java.util.concurrent.ThreadFactory;

/**
 * 实现线程工厂的接口，然后实现自定义内容。重写newThread方法
 */
public class MyThreadFactory implements ThreadFactory {

    private static int COUNTER = 0;

    private static String THREAD_PREFIX = "myThread";

    @Override
    public Thread newThread(Runnable r) {
        int i = COUNTER++;
        return new Thread(r, THREAD_PREFIX + i);
    }

    public static void main(String[] args) {
        PrintTask printTask = new PrintTask();
        MyThreadFactory myThreadFactory = new MyThreadFactory();
        Thread thread = myThreadFactory.newThread(printTask);
        thread.start();

    }
}
