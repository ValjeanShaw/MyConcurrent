package LockPackage;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xiaoran
 * @version 1.0
 *
 *
 * try{
 *
 * }finally{
 *
 * }
 * 是标准的模板
 */
public class LockInJava {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        lock.lock();

        try {

        } finally {
            lock.unlock();
        }
    }

}
