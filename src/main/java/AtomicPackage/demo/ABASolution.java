package AtomicPackage.demo;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * CAS方案的ABA问题解决方案
 * 使用AtomicStampedReference解决
 *
 * @author xiaoran
 * @date 2019/05/09
 */
public class ABASolution {
    private static AtomicStampedReference atomicStampedRef = new AtomicStampedReference(1, 0);

    public static void main(String[] args) {
        Thread main = new Thread(() -> {
            System.out.println("操作线程" + Thread.currentThread() + ",初始值 a = " + atomicStampedRef.getReference() + " 版本号：" + atomicStampedRef.getStamp());

            //获取当前标识别
            int stamp = atomicStampedRef.getStamp();
            try {
                //等待2秒 ，以便让干扰线程执行
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //此时expectedReference未发生改变，但是stamp已经被修改了,所以CAS失败
            boolean isCASSuccess = atomicStampedRef.compareAndSet(1, 2, stamp, stamp + 1);
            System.out.println("操作线程" + Thread.currentThread() + ",CAS操作结果: " + isCASSuccess + " 版本号：" + atomicStampedRef.getStamp());
        }, "主操作线程");

        Thread other = new Thread(() -> {
            atomicStampedRef.compareAndSet(1, 2,
                    atomicStampedRef.getStamp(), atomicStampedRef.getStamp() + 1);
            System.out.println("操作线程" + Thread.currentThread() + ",【increment】 ,值 = " + atomicStampedRef.getReference() + " 版本号：" + atomicStampedRef.getStamp());

            atomicStampedRef.compareAndSet(2, 1,
                    atomicStampedRef.getStamp(), atomicStampedRef.getStamp() + 1);
            System.out.println("操作线程" + Thread.currentThread() + ",【decrement】 ,值 = " + atomicStampedRef.getReference() + " 版本号：" + atomicStampedRef.getStamp());
        }, "干扰线程");

        main.start();
        other.start();
    }
}
