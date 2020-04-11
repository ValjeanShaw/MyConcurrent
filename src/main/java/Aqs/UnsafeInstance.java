package Aqs;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author xiaoran
 * @date 2020/04/10
 *
 * unsafe 魔术类的工厂模式
 *
 * 魔术类：java1.7及之后才有的
 * 可直接操作内存，绕过JVM
 *
 * 很不安全，只能通过反射方法创建
 *
 */
public class UnsafeInstance {
    public static Unsafe reflectUnsafe(){
        try{
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
