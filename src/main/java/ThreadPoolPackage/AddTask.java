package ThreadPoolPackage;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author: xiaoran
 * @date: 2018-08-31 00:18
 */
public class AddTask implements Callable<Integer> {
    private int i;

    private int j;

    public AddTask(int i, int j) throws InterruptedException{
        this.i = i;
        this.j = j;
        throw new InterruptedException("123");
    }

    @Override
    public Integer call() throws Exception {
        int sum =  i + j;
        System.out.println("线程main的运算结果：" + sum);
        return sum;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        try{
            Future<Integer> future = executorService.submit(new AddTask(1,2));

            int result = future.get();

            System.out.println(result);

        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }
}
