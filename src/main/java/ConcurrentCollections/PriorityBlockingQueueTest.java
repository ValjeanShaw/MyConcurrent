package ConcurrentCollections;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * 支持优先级的无界阻塞队列
 * 优先级判断方法：
 * <p>
 * 1.对类中元素，自定义compareTo()方法指定元素排序规则
 * 2.构造函数实现实现Comparator
 *
 * @author: xiaoran
 * @date: 2019-04-11 20:06
 */
public class PriorityBlockingQueueTest {

    /**
     * 支持优先级的无界阻塞队列
     */
    public static PriorityBlockingQueue<User> queue = new PriorityBlockingQueue<>();

    public static void main(String[] args) {
        queue.add(new User(1, "wu-1"));
        queue.add(new User(5, "wu-5"));
        queue.add(new User(23, "wu-23"));
        queue.add(new User(55, "wu-55"));
        queue.add(new User(9, "wu-9"));
        queue.add(new User(3, "wu-3"));
        while (queue.peek() != null) {
            System.out.println(queue.poll());
        }
    }

    //静态内部类
    static class User implements Comparable<User> {

        public User(int age, String name) {
            this.age = age;
            this.name = name;
        }

        int age;
        String name;

        @Override
        public int compareTo(User o) {
            return this.age > o.age ? -1 : 1;
        }

        @Override
        public String toString() {
            return "User{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

}
