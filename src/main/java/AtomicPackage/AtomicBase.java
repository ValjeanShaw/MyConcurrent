package AtomicPackage;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author xiaoran
 * @date 2019/05/05
 */
public class AtomicBase {

    /**
     * 原子整型
     */
    AtomicInteger atomicInteger = new AtomicInteger(1);

    /**
     * 原子布尔类型
     */
    AtomicBoolean atomicBoolean = new AtomicBoolean(true);

    /**
     * 原子长整型
     */
    AtomicLong atomicLong = new AtomicLong(123L);


    public static void main(String[] args) {
        AtomicBase atomicBase = new AtomicBase();
        System.out.println(atomicBase.atomicBoolean.getAndSet(false));
        System.out.println(atomicBase.atomicInteger.getAndSet(1099));
        System.out.println(atomicBase.atomicLong.getAndSet(9999L));

        System.out.println(atomicBase.atomicBoolean.get());
        System.out.println(atomicBase.atomicInteger.get());
        System.out.println(atomicBase.atomicLong.get());
    }
}
