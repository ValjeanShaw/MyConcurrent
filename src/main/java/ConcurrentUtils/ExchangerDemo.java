package ConcurrentUtils;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 用于线程之间相互交换数据
 * <p>
 * 两个线程之间通过阻塞方式进行数据交换给对方
 * <p>
 * exchange()
 * <p>
 * 场景：
 * 银行数据校对，相互交换数据，处理数据
 * 遗传算法
 *
 * @author: xiaoran
 * @date: 2019-04-22 13:49
 */
public class ExchangerDemo {
    private static Exchanger<String> exchanger = new Exchanger<>();

    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                String stringA = "银行流水A";
                try {
                    exchanger.exchange(stringA);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                String stringB = "银行流水B";
                try {
                    String stringA = exchanger.exchange(stringB);

                    System.out.println("A 和 B 数据是否一致:" + stringA.equals(stringB) + " A的数据: " + stringA + " B的数据: " + stringB);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadPool.shutdown();
    }

}
