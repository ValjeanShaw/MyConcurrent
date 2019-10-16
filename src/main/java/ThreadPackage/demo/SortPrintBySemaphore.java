package ThreadPackage.demo;

import java.util.concurrent.Semaphore;

/**
 * 信号量方式实现
 *
 * 初始(A=1,B=0,C=0)—>第一次执行线程A时(A=1,B=0,C=0)—->第一次执行线程B时（A=0,B=1,C=0）
 * —->第一次执行线程C时(A=0,B=0,C=1)—>第二次执行线程A(A=1,B=0,C=0)如此循环。
 *
 * @author xiaoran
 * @date 2019/10/16
 */
public class SortPrintBySemaphore {

    public static void main(String[] args) {
        Semaphore semaphoreA = new Semaphore(1);
        Semaphore semaphoreB = new Semaphore(0);
        Semaphore semaphoreC = new Semaphore(0);
        PrintRunBySemaphore printThreadA = new PrintRunBySemaphore( "A",semaphoreA,semaphoreB);
        PrintRunBySemaphore printThreadB = new PrintRunBySemaphore( "B",semaphoreB,semaphoreC);
        PrintRunBySemaphore printThreadC = new PrintRunBySemaphore( "C",semaphoreC,semaphoreA);

        Thread thread = new Thread(printThreadA);
        thread.start();
        thread = new Thread(printThreadB);
        thread.start();
        thread = new Thread(printThreadC);
        thread.start();
    }

}
class PrintRunBySemaphore implements Runnable {
    private String name;
    private Semaphore currentSemaphore;
    private Semaphore nextSemaphore;
    volatile static int globalCount = 0;

    public PrintRunBySemaphore(String name,Semaphore currentSemaphore,Semaphore nextSemaphore) {
        this.name = name;
        this.currentSemaphore = currentSemaphore;
        this.nextSemaphore = nextSemaphore;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try{
                currentSemaphore.acquire();
                System.out.println(name);
                globalCount++;
                nextSemaphore.release();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
