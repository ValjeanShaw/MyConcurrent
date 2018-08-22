package ThreadPackage;

/**
 * @author: xiaoran
 * @date: 2018-08-22 14:51
 * <p>
 * 线程中断
 *
 * 线程中断有个标志位：isInterrupted()返回中断标志位，true为中断过，false为未中断过
 *
 * 当线程为终结状态，即使线程被中断过，标志位也为false
 * 当抛出InterruptedException异常时，jvm会先将中断标志位清除，所以isInterrupted() 为false
 */
public class ThreadInterrupt {
    public static void main(String[] args) {
        //sleepThread不停的尝试睡眠
        Thread sleepThread = new Thread(new SleepRunner(), "sleepThread");
        sleepThread.setDaemon(true);

        Thread busyThread = new Thread(new BusyRunner(), "busyThread");
        busyThread.setDaemon(true);

        sleepThread.start();
        busyThread.start();

        //休眠几秒钟，让两个线程充分运行
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        sleepThread.interrupt();
        busyThread.interrupt();

        System.out.println("sleepThread interrupted is " + sleepThread.isInterrupted());
        System.out.println("busyThread interrupted is " + busyThread.isInterrupted());

        //防止两个线程充分提前退出
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}


class SleepRunner implements Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class BusyRunner implements Runnable {

    @Override
    public void run() {
        while (true) {

        }
    }
}
