package synchronizedTest;

/**
 * @author: xiaoran
 * @date: 2018-08-16 23:10
 *
 * 测试synchronized方法锁住了所有对象，该对象的synchonized方法在被锁住时，均不可用
 *
 */
public class SynchronizedTestTwo {
    private String id;

    public String getId() {
        return id;
    }

    public synchronized void setId(String id) {
        try{
            Thread.sleep(100);
        }catch (Exception e){
            e.printStackTrace();
        }
        this.id = id;
    }

    public static void main(String[] args) {
        for(int i=0;i<100;i++){
            SynchronizedTestTwo synchronizedTestTwo = new SynchronizedTestTwo();
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronizedTestTwo.setId("123");
                }
            });

            thread.start();

            try{
                Thread.sleep(100);
            }catch (Exception e){
                e.printStackTrace();
            }

            System.out.println("this id is:" + synchronizedTestTwo.getId());
        }

    }
}
