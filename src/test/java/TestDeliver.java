import lombok.Data;

/**
 * @author: xiaoran
 * @date: 2018-07-25 11:06
 */
public class TestDeliver {
    public static void main(String[] args) {
        Node node = new Node();
        node.setVal(123);
        System.out.println(node);
        TestDeliver.function(node);
        System.out.println(node.getVal());
        System.out.println(node);
    }

    public static void function(Node node){
        node.setVal(12354);
    }
}


class Node{
    private int val;

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }
}

