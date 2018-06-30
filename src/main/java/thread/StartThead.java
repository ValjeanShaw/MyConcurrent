package thread;

/**
 * @author 600006
 * @version 1.0
 *
 * 从这里开始，包含以下内容
 *
 * 创建和开启线程    Thread方式的，两种方式,Runnable方式，一种方法
 *
 */
public class StartThead {
    public static void main(String[] args) {

        //1.  创建实例
        RunByThread runByThread = new RunByThread();
        runByThread.start();
        System.out.println(runByThread.isInterrupted());
        runByThread.interrupt();
        System.out.println(runByThread.isInterrupted());
        //2.  创建对象，放入Thread中
        Thread threadRun = new Thread(new RunByThread());
        threadRun.start();
        threadRun.isInterrupted();


        //创建对象，放入Thread中
        Thread thread = new Thread(new RunByRunnable(),"runByThread");
        thread.start();


    }
}
