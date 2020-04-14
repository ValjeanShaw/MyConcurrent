package DesignPatterns;

/**
 * @author: xiaoran
 * @date: 2018-08-28 12:52
 *
 * 静态内部类模式，在多线程场景下，使用最多
 *
 */
public class InnerSingleton {
    private static class Singleton{
        private static Singleton singleton = new Singleton();
    }

    public static Singleton getInstance(){
        return Singleton.singleton;
    }


    public static void main(String[] args) {
        Object o = InnerSingleton.getInstance();
        Object o1 = InnerSingleton.getInstance();
        Object o2 = InnerSingleton.getInstance();
    }
}
