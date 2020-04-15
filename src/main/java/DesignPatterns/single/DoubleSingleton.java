package DesignPatterns.single;

/**
 * @author: xiaoran
 * @date: 2018-08-28 13:36
 *
 * 双重校验
 *
 * 使用volatile防止指令重排
 *
 * doubleSingleton = new DoubleSingleton();不是原子操作，
 * 分三步执行：
 * 1. 为 instance 分配内存空间;
 * 2. 初始化 instance;
 * 3. 将 instance 指向分配的内存地址
 *
 * JVM 指令重排，可能变成 1->3->2。
 * 多线程环境下会导致一个线程获得还没有初始化的实例。
 * 例 线程 a 执行了 1 和 3，此时 线程 b 调用 getInstance() 后发现 doubleSingleton 不为空，因此返回 doubleSingleton，但此时还未被初始化，空指针异常。
 *
 */
public class DoubleSingleton {
    private DoubleSingleton(){
    }

    private volatile static DoubleSingleton doubleSingleton;

    public static DoubleSingleton getInstance(){
        if(doubleSingleton == null){
            synchronized (DoubleSingleton.class) {
                if(doubleSingleton == null){
                    doubleSingleton = new DoubleSingleton();
                }
            }
        }
        return doubleSingleton;
    }
}
