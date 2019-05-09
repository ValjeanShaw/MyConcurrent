# Java并发编程
![GitHub top language](https://img.shields.io/github/languages/top/ValjeanShaw/MyConcurrent.svg)
![](https://img.shields.io/badge/Knowledge-concurrent-green.svg)

> 推荐书籍《java并发编程的艺术》  
> 此项目以案例为主，力求针对java的各个并发知识做出示例。


## 内容简介

![](https://img.shields.io/badge/tag-%E7%AE%80%E4%BB%8B-blue.svg)


并发基础

 * java线程基础
 * synchonized 关键字
 * valotile 关键字
 
并发进阶

 * java Lock
 * 一些基础的并发类容器和框架
 * JDK的原子操作类
 * 并发工具类
 * 线程池和Executor框架
 * Future和FutureTask模式
 
并发编程实战

部分设计模式

 
## 代码涵盖的知识点

![](https://img.shields.io/badge/tag-%E8%AF%A6%E7%BB%86-blue.svg)

### 1.并发基础

#### 1.1 线程基础

[ThreadPackage](https://github.com/ValjeanShaw/MyConcurrent/tree/develop/src/main/java/ThreadPackage)

##### 线程状态

* java线程创建基础，两种创建模式，不严格意义还有一种Executor工具类提供的方式
    1. 继承Thread类,重写run方法
    2. 实现Runnable接口,重写run方法
    3. 实现Callable接口，重写call方法(不严格意义,java 1.5之后提供)
* 线程的启动
* 守护线程（Daemon Thread）的概念和使用
* 加入线程中断概念和使用  interrupt

##### 线程通信

* 线程间通知，wait和notify的使用（经典范式）
* 线程间IO传输，使用管道输入输出流实现 (Piped)
* Thread.join()的使用
* 线程变量ThreadLocal的使用

##### 实例
使用wait和notify模拟一个阻塞队列

#### 1.2 synchonized 关键字

[SynchronizedPackage](https://github.com/ValjeanShaw/MyConcurrent/tree/develop/src/main/java/SynchronizedPackage)

* synchronized方法的基本使用
* synchronized方法的两个细节： 
    1. synchronized锁是可重入锁：当前对象不同方法可重入；父子类方法相互调用可重入。
    2. synchronized锁抛出异常时，会释放锁（异常抛出当前锁控制的区域）
   
#### 1.3 valotile 关键字

[VolatilePackage](https://github.com/ValjeanShaw/MyConcurrent/tree/develop/src/main/java/VolatilePackage)

* volatile 关键字的基本使用
* volatile 关键字的非原子性验证，以及原子类 AtomicInteger的操作
    
    
### 2.并发进阶

#### 2.1 java中的Lock

[LockPackage](https://github.com/ValjeanShaw/MyConcurrent/tree/develop/src/main/java/LockPackage)

* Lock的基本模型
* ReentrantLock的使用
* ReentrantLock公平锁的使用
* WriteReadLock的使用
##### lock相关使用实例
* 自定义同步组件，重写lock中的同步器，实现一种能容许同时最多两个线程的锁
* 使用读写锁和hashmap实现一个线程安全,性能较高的cache
* 使用condition实现一个有界队列，拥有满载及时阻塞，空闲通知插入的功能

#### 2.2 一些基础的并发类容器和框架

[ConcurrentCollecntions](https://github.com/ValjeanShaw/MyConcurrent/tree/develop/src/main/java/ConcurrentCollections)

* ConcurrentHashMap
* CopyOnWrite容器 
##### 线程安全的队列
* 非阻塞队列  
1. ConcurrentLinkedQueue    
* 阻塞队列 

4种处理方式

1. 抛异常
2. 返回特殊值
3. 阻塞
4. 超时退出 

7种类型队列

1. ArrayBlockingQueue
2. LikedBlockingQueue     
3. PriorityBlockingQueue
4. DelayQueue
5. SynchronousQueue
6. LinkedTransferQueue
7. LinkedBlockingDeque

框架
* fork/join框架

##### 性能对比 Demo
ConcurrentHashMap和ReadWriteLock+HashMap 在读多写少大并发场景下性能对比

#### 2.3 JDK的原子操作类

[AtomicPackage](https://github.com/ValjeanShaw/MyConcurrent/tree/develop/src/main/java/AtomicPackage)

* 原子更新基本类型类
    1. AtomicBoolean
    2. AtomicInteger
    3. AtomicLong
* 原子更新数组类型类
    1. AtomicIntegerArray
    2. AtomicLongArray
    3. AtomicReferenceArray<E>
* 原子更新引用类型
    1. AtomicReference
    2. AtomicReferenceFieldUpdater
    3. AtomicMarkableReference
* 原子更新字段类型
    1. AtomicLongFieldUpdater
    2. AtomicIntegerFieldUpdater
    3. AtomicStampedReference

#### 2.4 并发工具类

[ConcurrentUtils](https://github.com/ValjeanShaw/MyConcurrent/tree/develop/src/main/java/ConcurrentUtils)

* CountDownLtch的使用
* CyclicBarrier的使用
* Semaphore的使用
* Exchanger的使用

#### 2.5 线程池和Executor框架
`Waiting in line`

[ThreadPoolPackage](https://github.com/ValjeanShaw/MyConcurrent/tree/develop/src/main/java/ThreadPoolPackage)

* Executors和Executor
* Executors中fixThreadPool
* Executors中的cacheThreadPool
* Executors中的schduleThreadPool
* Executors中的singleThreadPool
* 线程中callback和fature的简单使用
* 线程工厂的使用
* 自定义线程池的使用及其饱和策略

#### 2.6 Future和FutureTask模式
`Waiting in line`

[FuturePackage](https://github.com/ValjeanShaw/MyConcurrent/tree/develop/src/main/java/FuturePackage)


### 3.并发编程实战
`Waiting in line`

### PS 部分设计模式

[DesignPatterns](https://github.com/ValjeanShaw/MyConcurrent/tree/develop/src/main/java/DesignPatterns)

* 多线程情况下，使用较多的两种单例模式
    1. 静态内部类 （推荐使用）
    2. 双重校验