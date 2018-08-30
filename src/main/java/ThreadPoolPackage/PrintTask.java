package ThreadPoolPackage;

import java.util.concurrent.atomic.AtomicInteger;

public class PrintTask implements Runnable{
    private AtomicInteger count = new AtomicInteger(1);

    private String name;

    public PrintTask(){

    }

    public PrintTask(Integer num){
        count.set(num);
    }

    public PrintTask(String name){
        this.name = name;
    }

    @Override
    public void run(){
        count.getAndIncrement();
        System.out.println(Thread.currentThread().getId()+"=="+this.name+"=="+count+"开始");
        try{
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getId()+"=="+this.name+"=="+count+"结束");
    }
}
