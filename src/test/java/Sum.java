/**
 * @author xiaoran
 * @version 1.0
 */
public class Sum extends Base {
    int num = 5;
    public void print(){
        System.out.println("Sum num:"+num);
    }
    public Sum(){
        print();
        num = 99;
    }

    public static void main(String[] args) {
        Base b = new Sum();
        System.out.println(b.num);
    }
}
