package synchronizedTest;

import lombok.Data;

/**
 * @author 600006
 * @version 1.0
 *
 *
 * 1.修饰实例方法
 * 2.修饰静态方法
 * 3.同步实例方法中的代码块
 * 4.同步静态方法中的代码块
 */
@Data
public class SynchronizedTest extends Thread{
    private Integer num = 100;

    private static Integer count = 200;

    private Integer number = 300;

    private static Integer counter = 400;

    public synchronized void addOne(){
        try{
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }
        num++;
        System.out.println(this.getId()+"  "+num+"  "+System.currentTimeMillis());
    }

    public static synchronized void addTwo(){
        try{
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }
        count += 2;
        System.out.println("  "+count+"  "+System.currentTimeMillis());
    }


    public void addThree(){

        //使用了“this”，即为调用add方法的实例本身。
        synchronized (this){
            try{
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }

            number += 3;
        }
        System.out.println("  "+number+"  "+System.currentTimeMillis());
    }


    public static void addFour(){
        synchronized (SynchronizedTest.class){
            try{
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }

            counter += 4;
        }
        System.out.println("  "+counter+"  "+System.currentTimeMillis());
    }

    public void run(){

        addOne();
        addTwo();
        addThree();
        addFour();
    }

    public static void main(String[] args) {
        SynchronizedTest thread1 = new SynchronizedTest();
        SynchronizedTest thread2 = new SynchronizedTest();
        SynchronizedTest thread3 = new SynchronizedTest();
        SynchronizedTest thread4 = new SynchronizedTest();
        SynchronizedTest thread5 = new SynchronizedTest();
        SynchronizedTest thread6 = new SynchronizedTest();

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();

    }
}
