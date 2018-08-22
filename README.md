# 这是学习java并发中，实战的代码
> 推荐书籍《java并发编程的艺术》  


## 项目结构
 * lock包       java锁的知识
 * SynchronizedPackage   synchronized使用
 * thread包     java线程知识
 * threadPool   java线程池知识
 
## 代码涵盖的知识点

### 高并发基础
####synchonized

SynchronizedPackage包下的知识点
* synchronized方法的基本使用
* synchronized方法的两个细节： 
    1. synchronized锁是可重入锁：当前对象不同方法可重入；父子类方法相互调用可重入。
    2. synchronized锁抛出异常时，会释放锁（异常抛出当前锁控制的区域）
   
####valotile 
VolatilePackage包下的知识点
* valotile 关键字的基本使用
* valotile 关键字的非原子性验证，以及原子类 AtomicInteger的操作
    
 