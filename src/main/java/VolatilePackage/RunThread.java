package VolatilePackage;

/**
 * @author: xiaoran
 * @date: 2018-08-21 23:56
 */
public class RunThread implements Runnable {

    /**
     * volatile修饰，保持线程之间的可见性
     */
    private volatile Boolean flag = true;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        System.out.println("进入了run方法。。。。");
        while (flag == true) {

        }
        System.out.println("run方法执行完毕。。。");
    }


    public static void main(String[] args) {
        //一个子线程，进行运行
        RunThread runThread = new RunThread();
        new Thread(runThread).start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        runThread.setFlag(false);
        System.out.println("now this flag is:" + runThread.isFlag());

    }
}
