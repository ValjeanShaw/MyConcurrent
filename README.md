# Java并发编程
> 推荐书籍《java并发编程的艺术》  
> 此项目，为并发基础项目，内容涵盖了Java基础到高级的并发知识。


## 项目结构
 * ThreadPackage         java线程基础包
 * SynchronizedPackage   synchronized相关知识
 * VolatilePackage       volatile相关知识
 * LockPackage           java锁的知识
 * ThreadPoolPackage     java线程池知识
 * DesignPatterns        多线程相关的设计模式
 * ConcurrentUtils       JDK提供的并发的工具类
 
 **接下来请看详细内容**
 
## 代码涵盖的知识点

### 1.并发基础

#### 线程基础
`ThreadPackage`

线程状态

* java线程创建基础，两种创建模式
    1. 集成Thread类
    2. 实现Runnable接口
* 线程的启动
* 守护线程（Daemon Thread）的概念和使用
* 加入线程中断概念和使用  interrupt

线程通信

* 线程间通知，wait和notify的使用（经典范式）
* 线程间IO传输，使用管道输入输出流实现 (Piped)
* Thread.join()的使用
* 线程变量ThreadLocal的使用

实例
使用wait和notify模拟一个阻塞队列

#### synchonized

`SynchronizedPackage`
* synchronized方法的基本使用
* synchronized方法的两个细节： 
    1. synchronized锁是可重入锁：当前对象不同方法可重入；父子类方法相互调用可重入。
    2. synchronized锁抛出异常时，会释放锁（异常抛出当前锁控制的区域）
   
#### valotile 
`VolatilePackage`
* volatile 关键字的基本使用
* volatile 关键字的非原子性验证，以及原子类 AtomicInteger的操作
    
 
#### 设计模式
`DesignPatterns`
* 多线程情况下，使用较多的两种单例模式
    1. 静态内部类 （推荐使用）
    2. 双重校验
    
### 2.并发进阶

#### 一些基础的并发类容器
`ConcurrentCollecntions`
* Vector
* ConcurrentHashMap (设计原理比较重要，经常拿来和HashMap进行比较)

以上集合不做code
* CopyOnWrite容器  具体看源码的add方法
常用的几种queue
* ConcurrentLinkedQueue    非阻塞，性能很高
* ArrayBlockingQueue       
* LikedBlockingQueue  

#### 线程池
`ThreadPoolPackage`
* Executors和Executor
* Executors中fixThreadPool
* Executors中的cacheThreadPool
* Executors中的schduleThreadPool
* Executors中的singleThreadPool
* 线程中callback和fature的简单使用
* 线程工厂的使用
* 自定义线程池的使用及其饱和策略

#### 并发工具类
`ConcurrentUtils`
* CountDownLtch的使用
* CyclicBarrier的使用
* Semaphore的使用

#### java中的Lock
`LockPackage`
* Lock的基本模型
* ReentrantLock的使用
* ReentrantLock公平锁的使用
* WriteReadLock的使用