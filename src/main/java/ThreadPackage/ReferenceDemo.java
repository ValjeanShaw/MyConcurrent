package ThreadPackage;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 主要用于讲解java中的四种引用
 *
 * 强  一定不会被回收
 * 软  内存不足时被回收  缓存
 * 弱  GC时被回收       ThreadLocal WeakHashMap 定期清理的数据
 * 虚  GC时被回收       监听
 *
 *
 * @author xiaoran
 * @date 2020/04/14
 */
public class ReferenceDemo {

    public static void main(String[] args) throws Exception{
        ReferenceDemo reference = new ReferenceDemo();
        System.out.println("强引用-----\n");
        reference.strongReference();
        System.out.println("软引用-----\n");
        reference.softReference();
        System.out.println("弱引用-----\n");
        reference.weakReference();
        System.out.println("虚引用-----\n");
        reference.phantomRefernce();
    }

    /**
     * 强引用
     * @throws Exception
     */
    public void strongReference() throws Exception{
        Object obj = new Object();
        //提醒系统开始gc，不一定什么时候执行
        System.gc();
        //确保系统有时间执行gc
        Thread.sleep(2000);

        System.out.println(obj);
        obj= null;
        System.gc();
        System.out.println(obj);

    }


    /**
     * -Xmx20M
     * 软引用
     * @throws Exception
     */
    public void softReference() throws Exception{
        SoftReference<byte[]> softReference = new SoftReference<>(new byte[1024*1024*10]);
        System.out.println(softReference.get());
        //提醒系统开始gc，不一定什么时候执行
        System.gc();
        //确保系统有时间执行gc
        Thread.sleep(2000);
        System.out.println(softReference.get());
        byte[] bytes = new byte[1024 * 1024 * 10];
        System.gc();
        System.out.println(softReference.get());

    }

    /**
     *
     * 弱引用
     * @throws Exception
     */
    public void weakReference() throws Exception{
        WeakReference<byte[]> weakReference = new WeakReference<byte[]>(new byte[1]);
        System.out.println(weakReference.get());
        System.gc();
        System.out.println(weakReference.get());

    }

    /**
     * 虚引用
     *
     * 无法通过虚引用来获取对一个对象的真实引用
     * 虚引用必须与ReferenceQueue一起使用，当GC准备回收一个对象，如果发现它还有虚引用，就会在回收之前，把这个虚引用加入到与之关联的ReferenceQueue中。
     *
     * @throws Exception
     */
    public void phantomRefernce() throws Exception{
        ReferenceQueue queue = new ReferenceQueue();
        List<byte[]> bytes = new ArrayList<>();
        PhantomReference<Object> reference = new PhantomReference<>(new Object(),queue);
        new Thread(() -> {
            for (int i = 0; i < 100;i++ ) {
                bytes.add(new byte[1024 * 1024]);
            }
        }).start();

        System.gc();
        new Thread(() -> {
            while (true) {

                Reference poll = queue.poll();
                if (poll != null) {
                    System.out.println("虚引用被回收了：" + poll);
                }
            }
        }).start();
    }

}
