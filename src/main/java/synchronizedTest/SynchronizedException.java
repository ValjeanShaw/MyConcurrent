package synchronizedTest;

/**
 * @author: xiaoran
 * @date: 2018-08-21 00:34
 * <p>
 * 验证 synchronized方法修饰的内容，在抛异常情况下，会释放锁
 */
public class SynchronizedException {

    public synchronized void doOneThing() {
        int i = 0;
        while (true) {
            i++;
            try{
                Thread.sleep(100);
            }
            catch (Exception e){
                e.printStackTrace();
            }
            System.out.println("now,the i is:" + i);

            try{
                if(i == 10){

                    Integer.parseInt("a");
                }
            }catch (Exception e){
                e.printStackTrace();
                continue;
            }

        }
    }

    public synchronized void doTwoThing() {
        int j = -100;
        while (true) {
            j--;
            try{
                Thread.sleep(100);
            }
            catch (Exception e){
                e.printStackTrace();
            }
            System.out.println("yes boy,the j is:" + j);

            if(j == -110){
                Integer.parseInt("a");
            }
        }
    }


    public static void main(String[] args) {
        SynchronizedException synExp = new SynchronizedException();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synExp.doOneThing();
            }
        });
        thread.start();
        synExp.doTwoThing();
    }


}
