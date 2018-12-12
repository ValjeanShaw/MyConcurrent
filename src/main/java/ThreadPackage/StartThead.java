package ThreadPackage;

/**
 * @author xiaoran
 * @version 1.0
 * <p>
 * 从这里开始，包含以下内容
 * <p>
 * 创建和开启线程    Thread方式的，两种方式,Runnable方式，一种方法
 */
public class StartThead {
    public static void main(String[] args) {

        /**
         * 继承Thread类的两种启动方式
         * 1.  创建实例
         * 2.  创建对象，放入Thread中
         */
        RunByThread runByThread = new RunByThread();
        runByThread.start();

        Thread threadRun = new Thread(new RunByThread());
        threadRun.start();


        /**
         * 实现Runnable接口启动线程的方式
         *
         * 创建对象，放入Thread中
         */
        Thread thread = new Thread(new RunByRunnable(), "runByThread");
        thread.start();


    }
}
