package ThreadPackage;

/**
 * @author xiaoran
 * @version 1.0
 *
 * join()方法
 *
 * 在A线程里面调用 B.join()
 * 含义：等待B执行完之后，再调用A
 *
 */
public class ThreadJoin {
    public static void main(String[] args) {
        Thread previous = Thread.currentThread();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Domino(previous),String.valueOf(i));
            thread.start();
            previous = thread;
        }

        try{
            Thread.sleep(500);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());

    }


    static class Domino implements Runnable {

        private Thread parentThread;

        public Domino(Thread parentThread) {
            this.parentThread = parentThread;
        }

        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + 000);
                parentThread.join();
                System.out.println(Thread.currentThread().getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
