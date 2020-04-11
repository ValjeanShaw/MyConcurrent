package VolatilePackage;

/**
 * @author xiaoran
 * @date 2020/03/31
 */
public class VolatileTest {

    private static volatile boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello,this is thread one");
                while(flag){

                }
                System.out.println("hello,thread one is over");
            }
        }).start();

        Thread.sleep(2000);

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello,this is thread two");
                flag = false;
                System.out.println("i changed ths flag to false");
            }
        }).start();

    }
}
