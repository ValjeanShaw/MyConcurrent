package ThreadPackage;

import java.util.concurrent.TimeUnit;

/**
 * @author xiaoran
 * @version 1.0
 *
 * 通过setDaemon设置为守护线程
 *
 * 当用户线上不存在的时候，守护线程会理解被清理掉，jvm清理，不会等待线程执行完毕
 *
 */
public class DaemonThread extends Thread{
    @Override
    public void run() {
        try{
            System.out.println("DaemonThread running start");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("DaemonThread running end");
        } catch (InterruptedException e){
            e.printStackTrace();
            System.out.println("DaemonThread  InterruptedException");
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("DaemonThread  exception");
        }finally {
            System.out.println("DaemonThread finally");
        }
    }

    public static void main(String[] args) {
        DaemonThread daemonThread = new DaemonThread();
        daemonThread.setDaemon(true);
        daemonThread.start();
    }
}
