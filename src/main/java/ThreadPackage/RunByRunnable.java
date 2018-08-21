package ThreadPackage;

/**
 * @author xiaoran
 * @version 1.0
 * <p>
 * 实现Runnable接口，重写run接口
 */
public class RunByRunnable implements Runnable {

    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                System.out.println("running by runnable......");
            } catch (InterruptedException e) {
                System.out.println("interrupted...");
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
