package ThreadPackage.demo;

/**
 * Scychronized方式打印
 *
 * @author xiaoran
 * @date 2019/09/24
 */
public class SortPrintBySynchronized {

    public static void main(String[] args) {

        final Object lockObj = new Object();
        PrintRunThread printThreadA = new PrintRunThread(0, "A", lockObj);
        PrintRunThread printThreadB = new PrintRunThread(1, "B", lockObj);
        PrintRunThread printThreadC = new PrintRunThread(2, "C", lockObj);

        Thread thread = new Thread(printThreadA);
        thread.start();
        thread = new Thread(printThreadB);
        thread.start();
        thread = new Thread(printThreadC);
        thread.start();
    }
}

class PrintRunThread implements Runnable {
    private int myId;
    private String name;
    private Object lockObj;
    volatile static int globalCount = 0;

    public PrintRunThread(int myId, String name, Object lockObj) {
        this.myId = myId;
        this.name = name;
        this.lockObj = lockObj;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            synchronized (lockObj) {
                while (globalCount % 3 != myId) {
                    try {
                        lockObj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(name);
                globalCount++;
                lockObj.notifyAll();
            }
        }
    }
}
