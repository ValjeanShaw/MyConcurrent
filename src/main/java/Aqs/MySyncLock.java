package Aqs;

import sun.misc.Unsafe;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.LockSupport;

/**
 *
 * 自定义同步锁
 *
 * @author xiaoran
 * @date 2020/04/10
 */
public class MySyncLock {
    /**
     * 任意时刻，都只有一个线程获取到锁，因此此处定义一个变量
     *
     * 用于记录当前加锁的次数
     */
    private volatile int state = 0;


    /**
     * 当前持有锁的线程
     */
    private Thread lockHolder;


    /**
     * cas 算法实现的一个等待队列，用于存放等待获取锁的线程
     */
    private ConcurrentLinkedQueue<Thread> waiterQueue = new ConcurrentLinkedQueue<>();

    /**
     * 通过unsafe反射的模式拿到Unsafe类，使用其中的 cas 方法实现对state更改加锁次数的原子操作
     */
    private static final Unsafe unsafe = UnsafeInstance.reflectUnsafe();

    /**
     * 用于指示 state 变量在内存中的位置，或者说偏移量
     * cas计算中会用到
     */
    private static final long stateOffset;

    //stateOffset初始化
    static{
        try{
            stateOffset = unsafe.objectFieldOffset(MySyncLock.class.getDeclaredField("state"));
        }catch (Exception e){
            e.printStackTrace();
            throw new Error();
        }
    }

    /**
     * 指定对象中，state属性在内存中对应的偏移量
     * @param expect
     * @param update
     * @return
     */
    public final boolean compareAndSwapState(int expect,int update){
        return unsafe.compareAndSwapInt(this,stateOffset,expect,update);
    }






/**
 * park函数是将当前调用Thread阻塞，而unpark函数则是将指定线程Thread唤醒。
 *
 * 与Object类的wait/notify机制相比，park/unpark有两个优点：
 *
 * 以thread为操作对象更符合阻塞线程的直观定义
 * 操作更精准，可以准确地唤醒某一个线程（notify随机唤醒一个线程，notifyAll唤醒所有等待的线程），增加了灵活性。
 *
 */


    /**
     * 加锁
     */
    public void lock(){
        //加锁成功
        if(acquire()){
            return;
        }
        //如果没有加锁成功，那么该线程不能出这个方法,在里面等待直到拿到锁之后才能出来

        //当前线程放到队列中排队
        Thread currentThread = Thread.currentThread();
        waiterQueue.add(currentThread);

        for(;;){
            if(currentThread == waiterQueue.peek() && acquire()){
                //队列中第一个元素加锁成功，移除第一个元素
                waiterQueue.poll();
                return;
            }
            //阻塞当前线程
            LockSupport.park();
        }

    }

    /**
     * 尝试cas方式原子加锁
     * 加锁成功true 否则false
     *
     * @return
     */
    public boolean acquire(){
        Thread currentThread = Thread.currentThread();
        //volatile修饰state 各线程可见
        int c = this.getState();
        if(c == 0){
            // 当等待队列为空或者当前线程是队列第一个出队的线程时  那么同时执行cas将状态置为1成功   则代表获取锁成功
            if((waiterQueue.size() == 0 || currentThread == waiterQueue.peek())
                    && this.compareAndSwapState(0,1)){
                this.setLockHolder(currentThread);
                return true;
            }
        }
        return false;
    }

    /**
     * 释放锁
     *
     *
     */
    public void unlock(){
        //先判断是否是当前持有锁的线程
        if(Thread.currentThread() != lockHolder){
            throw new RuntimeException("lockHolder is not current thread");
        }
        int state = this.getState();
        //释放前将状态变为0
        if(this.compareAndSwapState(state,0)){
            //持有锁
            setLockHolder(null);
            //如果队列有值，则唤醒其中的线程
            Thread first = waiterQueue.peek();
            if(first != null){
                LockSupport.unpark(first);
            }
        }
    }






    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Thread getLockHolder() {
        return lockHolder;
    }

    public void setLockHolder(Thread lockHolder) {
        this.lockHolder = lockHolder;
    }
}
