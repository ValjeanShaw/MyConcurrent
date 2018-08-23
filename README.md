# Java并发编程
> 推荐书籍《java并发编程的艺术》  
> 此项目，为我的并发基础项目，计划将Java并发所用到的内容和demo,都coding一遍。更新中


## 项目结构
 * ThreadPackage         java线程基础包
 * SynchronizedPackage   synchronized相关知识
 * VolatilePackage       volatile相关知识
 * LockPackage           java锁的知识
 * ThreadPoolPackage     java线程池知识
 
 **接下来请看详细内容**
 
## 代码涵盖的知识点

### 高并发基础

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


#### synchonized

`SynchronizedPackage`
* synchronized方法的基本使用
* synchronized方法的两个细节： 
    1. synchronized锁是可重入锁：当前对象不同方法可重入；父子类方法相互调用可重入。
    2. synchronized锁抛出异常时，会释放锁（异常抛出当前锁控制的区域）
   
#### valotile 
`VolatilePackage`
* valotile 关键字的基本使用
* valotile 关键字的非原子性验证，以及原子类 AtomicInteger的操作
    
 