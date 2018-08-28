package ConcurrentCollections;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author: xiaoran
 * @date: 2018-08-28 23:03
 *
 *  重点关注  CopyOnWriteArrayList中的add方法，读下源码
 *
 *
 *  jdk中包含COW容器有两种，CopyOnWriteArrayList和CopyOnWriteArraySet，
 *  ！！！！！敲黑板！！！！！！！！
 *
 *  这类容器在非常多的并发中都会用到，适合读多写少的场景
 *
 *  ！！！！！敲黑板！！！！！！！！
 *
 *  具体设计原理：
 *      读    get
 *          正常的读，不加锁
 *      写    add    整个添加的过程，使用重入锁锁住，独占
 *          先将容器中的数据进行复制一份，然后进行添加操作，添加完成后，再将引用指向新的容器
 *
 *
 */
public class UseCopyOnWrite {
    public static void main(String[] args) {

        CopyOnWriteArrayList<String> cwal = new CopyOnWriteArrayList<String>();
        CopyOnWriteArraySet<String> cwas = new CopyOnWriteArraySet<String>();

    }
}
