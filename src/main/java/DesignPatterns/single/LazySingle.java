package DesignPatterns.single;

/**
 * 懒汉式单例模式
 *
 * @author xiaoran
 * @date 2020/04/14
 */
public class LazySingle {

    private LazySingle(){

    }

    private static LazySingle lazySingle;

    public static LazySingle getInstance(){
        if(lazySingle == null){
            return new LazySingle();
        }
        return lazySingle;
    }

}
