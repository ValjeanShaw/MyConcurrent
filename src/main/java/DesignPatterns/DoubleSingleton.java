package DesignPatterns;

/**
 * @author: xiaoran
 * @date: 2018-08-28 13:36
 *
 * 双重校验
 */
public class DoubleSingleton {
    private static DoubleSingleton doubleSingleton;

    public static DoubleSingleton getDs(){
        if(doubleSingleton == null){
//            try {
//                //模拟初始化对象的准备时间...
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            synchronized (DoubleSingleton.class) {
                if(doubleSingleton == null){
                    doubleSingleton = new DoubleSingleton();
                }
            }
        }
        return doubleSingleton;
    }
}
