package ThreadPackage.demo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 实现3个线程，分别命名 A B C，然后将分别打印自己的名字十次，要求按顺序打印。要求如下  ABCABCABC
 *
 * 通过Atomic 原子特性遍历取模，实现控制
 *
 * @author xiaoran
 * @date 2019/09/16
 */
public class SortPrintByAtomic {

    public static void main(String[] args) {
        AtomicInteger globalCount = new AtomicInteger(0);
        int printCount = 10;
        PrintThread printThreadA = new PrintThread(0,printCount,globalCount,"A");
        PrintThread printThreadB = new PrintThread(1,printCount,globalCount,"B");
        PrintThread printThreadC = new PrintThread(2,printCount,globalCount,"C");

        Thread thread = new Thread(printThreadA);
        thread.start();
        thread = new Thread(printThreadB);
        thread.start();
        thread = new Thread(printThreadC);
        thread.start();
    }
}


class PrintThread implements Runnable{

    int myId;
    int count;
    AtomicInteger lockInt;
    String name;
    public PrintThread(int id,int count,AtomicInteger lockInt,String name){
        this.myId = id;
        this.count = count;
        this.lockInt = lockInt;
        this.name= name;
    }

    public void printString(String name){
        System.out.print(name);
    }

    @Override
    public void run() {
        while(count>0){
            if(lockInt.get() % 3 == myId){
                count--;
                printString(name);
                //轮盘
                lockInt.getAndIncrement();
            }
        }
    }
}