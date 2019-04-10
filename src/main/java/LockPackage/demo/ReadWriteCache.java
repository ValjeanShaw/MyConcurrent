package LockPackage.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 使用读写锁和hashmap实现的缓存
 *
 * @author: xiaoran
 * @date: 2019-04-08 10:04
 */
public class ReadWriteCache {
    static Map<String, Object> map = new HashMap<>();
    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static Lock readLock = readWriteLock.readLock();
    static Lock writeLock = readWriteLock.writeLock();

    /**
     * 根据key获取对象
     *
     * @param key
     * @return
     */
    public static final Object get(String key) {
        readLock.lock();
        try {
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }

    /**
     * 添加键值对  返回之前的值
     *
     * @param key
     * @param object
     */
    public static final Object put(String key, Object object) {
        writeLock.lock();
        try {
            return map.put(key, object);
        } finally {
            writeLock.unlock();
        }
    }

    /**
     * 清空map
     */
    public final static void clear() {
        writeLock.lock();
        try {
            map.clear();
        } finally {
            writeLock.unlock();
        }
    }
}
