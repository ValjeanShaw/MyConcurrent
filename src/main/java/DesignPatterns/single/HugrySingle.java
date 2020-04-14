package DesignPatterns.single;

/**
 *
 * 饿汉式单例模式
 *
 * @author xiaoran
 * @date 2020/04/14
 */
public class HugrySingle {
    private HugrySingle(){
    }

    private static HugrySingle hugrySingle = new HugrySingle();


    public static HugrySingle getInstance(){
        return hugrySingle;
    }
}
