package AtomicPackage;

import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * 含有3种类型
 *
 * AtomicIntegerArray
 * AtomicLongArray
 * AtomicReferenceArray
 *
 * @author xiaoran
 * @date 2019/05/08
 */
public class AtomicArray {

    /**
     * 整型数组
     */
    int[] intArray = {1, 2, 3};
    AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(intArray);

    /**
     * 长整型数组
     */
    long[] longArray = {1L, 2L, 3L, 5L};
    AtomicLongArray atomicLongArray = new AtomicLongArray(longArray);


    /**
     * 引用类型数组
     */
    final static class Node {
        int value;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }

    Node[] nodeArray = {new Node(3), new Node(5), new Node(7), new Node(9)};
    AtomicReferenceArray<Node> atomicReferenceArray = new AtomicReferenceArray(nodeArray);


    public static void main(String[] args) {
        AtomicArray atomicArray = new AtomicArray();
        //常用方法

        //将指定位置数原值与入参相加
        atomicArray.atomicIntegerArray.addAndGet(1, 2);
        System.out.println(atomicArray.atomicIntegerArray.get(1));
        //在指定位置  判断原值与expect期望值是否一致，当一致时，将其update为入参值，否则不动
        atomicArray.atomicIntegerArray.compareAndSet(1, 2, 5);
        atomicArray.atomicIntegerArray.compareAndSet(1, 4, 7);
        System.out.println(atomicArray.atomicIntegerArray.get(1));

        //AtomicLongArray用于与AtomicIntegerArray基本一致

        Node nodeExpect = new Node(7);
        Node node = new Node(110);
        //注意，需是同一对象
        atomicArray.atomicReferenceArray.compareAndSet(2, nodeExpect, node);
        System.out.println(atomicArray.atomicReferenceArray.get(2).toString());
    }
}
