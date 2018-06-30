package thread;

/**
 * @author 600006
 * @version 1.0
 *
 * 继承Thread 类,重写run方法
 *
 *
 */
public class RunByThread extends Thread{

    @Override
    public void run(){
        while(true){
            try{
                Thread.sleep(5000);
                System.out.println("running by thread......");
            }catch (Exception e){
                System.out.println(232);
                e.printStackTrace();
            }

        }
    }
}
