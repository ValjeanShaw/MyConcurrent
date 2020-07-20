package DesignPatterns.single;

/**
 * @author xiaoran
 * @date 2020/07/20
 */
public enum Singleton {
    INSTANCE;

    Singleton(){
        System.out.println("初始化一次");
    }

    public static void main(String[] args) {
        Singleton t1 = Singleton.INSTANCE;
        Singleton t2 = Singleton.INSTANCE;
        System.out.println(t1==t2);
    }
}
