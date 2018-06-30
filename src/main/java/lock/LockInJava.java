package lock;

/**
 * @author 600006
 * @version 1.0
 */
public class LockInJava {
    public static void main(String[] args) {
        short a = 128;
        byte b = (byte) a;
        System.out.println(b);

        binaryToDecimal(a);

        System.out.println("-----");
        binaryToDecimal(b);


        char[] c = {'a','b','c','d','e'};
        String str = "abcde";

        System.out.println(str.equals(c));

    }

    public static void binaryToDecimal(short n) {
        for(int i = 31; i >= 0; i--){
            System.out.print(n >>> i & 1);
        }
    }

    public static void binaryToDecimal(byte n) {
        for(int i = 7; i >= 0; i--){
            System.out.print(n >>> i & 1);
        }
    }
}
