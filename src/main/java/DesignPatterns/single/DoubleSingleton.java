package DesignPatterns.single;

/**
 * @author: xiaoran
 * @date: 2018-08-28 13:36
 *
 * 双重校验
 */
public class DoubleSingleton {
    private DoubleSingleton(){
    }

    private static DoubleSingleton doubleSingleton;

    public static DoubleSingleton getInstance(){
        if(doubleSingleton == null){
            synchronized (DoubleSingleton.class) {
                if(doubleSingleton == null){
                    doubleSingleton = new DoubleSingleton();
                }
            }
        }
        return doubleSingleton;
    }
}
