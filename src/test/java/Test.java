import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 600006
 * @version 1.0
 */
public class Test {

    public int inc = 0;

    public synchronized void increaseBySynchronized() {
        inc++;
    }

    public int lock_inc = 0;
    Lock lock = new ReentrantLock();

    public void increaseByLock() {
        lock.lock();
        try {
            lock_inc++;
        } finally{
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final Test test = new Test();
        for(int i=0;i<10;i++){
            new Thread(){
                public void run() {
                    for(int j=0;j<1000;j++){
                        test.increaseBySynchronized();
                        test.increaseByLock();
                    }
                }
            }.start();
        }

        try {
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(test.inc);
        System.out.println(test.lock_inc);
    }
}