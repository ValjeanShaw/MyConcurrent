package DesignPatterns.single;

/**
 * @author: xiaoran
 * @date: 2018-08-28 12:52
 *
 * 静态内部类模式，在多线程场景下，使用最多
 *
 * 加载InnerSingleton类时，不会立即初始化singleton，只有调用getInstance，才会立即初始化Singleton
 * 利用jvm加载特性，class文件只会被加载一次，每种class文件都会有一个锁。保证了线程安全和高效(除了第一次加载耗时较长之外)
 */
public class InnerSingleton {

    private InnerSingleton(){
    }

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

        if(o == o1 && o1== o2){
            System.out.println(true);
        }else{
            System.out.println(false);
        }
    }
}
