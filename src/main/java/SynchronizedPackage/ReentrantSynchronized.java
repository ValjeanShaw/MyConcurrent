package SynchronizedPackage;

/**
 * @author: xiaoran
 * @date: 2018-08-17 11:34
 * <p>
 * sychronized锁的重入性
 */
public class ReentrantSynchronized extends Father {

    /**
     * sychronized   重写父类的方法，锁也可以重入
     */
    @Override
    public synchronized void doSomeThing() {
        super.doSomeThing();
        System.out.println(this.getClass() + "  --  " + Thread.currentThread().getId() + "  --  " + Thread.currentThread().getName());
        System.out.println("ReentrantSynchronized child doSomeThing");
    }

    /**
     * sychronized    同一个对象中的两个方法，也可以重入
     */
    public synchronized void doOneThing() {
        System.out.println(this.getClass() + "  --  " + Thread.currentThread().getId() + "  --  " + Thread.currentThread().getName());
        System.out.println("ReentrantSynchronized child doOneThing");
        doAnotherThing();
    }

    /**
     *
     */
    public synchronized void doAnotherThing() {
        System.out.println(this.getClass() + "  --  " + Thread.currentThread().getId() + "  --  " + Thread.currentThread().getName());
        System.out.println("ReentrantSynchronized child doAnotherThing");
    }

    public static void main(String[] args) {
        ReentrantSynchronized reentrantSynchronized = new ReentrantSynchronized();
        reentrantSynchronized.doSomeThing();
        reentrantSynchronized.doOneThing();
    }
}

class Father {
    public synchronized void doSomeThing() {
        System.out.println(this.getClass() + "  --  " + Thread.currentThread().getId() + "  --  " + Thread.currentThread().getName());
        System.out.println("father doSomeThing()");
    }
}
