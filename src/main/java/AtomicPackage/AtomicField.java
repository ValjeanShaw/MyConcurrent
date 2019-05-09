package AtomicPackage;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 原子更新字段相关类
 * <p>
 * 操作方法：
 * 1.使用静态方法 newUpdater() 创建更新器
 * 2.更新类的字段须使用public volatile修饰
 * <p>
 * 3种类型
 * AtomicLongFieldUpdater
 * AtomicIntegerFieldUpdater
 * AtomicStampedReference
 *
 * @author xiaoran
 * @date 2019/05/09
 */
public class AtomicField {
    /**
     * 原子更新长整型字段
     * <p>
     * 字段只能是long，不能是Long
     */
    private static AtomicLongFieldUpdater atomicLongFieldUpdater = AtomicLongFieldUpdater.newUpdater(User.class, "cardId");

    /**
     * 原子更新整型字段
     */
    private static AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = AtomicIntegerFieldUpdater.newUpdater(User.class, "old");

    /**
     * 原子更新长整型字段
     * 常用于解决ABA问题
     */
    private static AtomicStampedReference atomicStampedReference = new AtomicStampedReference(1, 1);


    public static void main(String[] args) {
        System.out.println("\n---------长整型---------");
        User user = new User(76732643L, 19);
        atomicLongFieldUpdater.compareAndSet(user, 76732643L, 1111111122L);
        System.out.println(atomicLongFieldUpdater.get(user));
        System.out.println(user.getCardId());

        System.out.println("\n---------整型---------");
        atomicIntegerFieldUpdater.compareAndSet(user, 19, 30);
        System.out.println(atomicIntegerFieldUpdater.get(user));
        System.out.println(user.getOld());
    }


    final static class User {
        public volatile long cardId;
        public volatile int old;

        public User(Long cardId, int old) {
            this.cardId = cardId;
            this.old = old;
        }

        public Long getCardId() {
            return cardId;
        }

        public void setCardId(Long cardId) {
            this.cardId = cardId;
        }

        public int getOld() {
            return old;
        }

        public void setOld(int old) {
            this.old = old;
        }

        @Override
        public String toString() {
            return "User{" +
                    "cardId=" + cardId +
                    ", old=" + old +
                    '}';
        }
    }

}
