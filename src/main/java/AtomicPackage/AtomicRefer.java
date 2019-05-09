package AtomicPackage;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 原子更新引用类型
 *
 * @author xiaoran
 * @date 2019/05/09
 */
public class AtomicRefer {

    /**
     * 原子更新引用类型
     */
    public static AtomicReference<User> atomicReferUser = new AtomicReference<>();


    public static void main(String[] args) {
        User user = new User("name", 18);
        atomicReferUser.set(user);

        User updateUser = new User("new name", 20);
        atomicReferUser.compareAndSet(user, updateUser);
        System.out.println(atomicReferUser.get().toString());
    }


    final static class User {
        private String name;
        private int old;

        public User(String name, int old) {
            this.name = name;
            this.old = old;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getOld() {
            return old;
        }

        public void setOld(int old) {
            this.old = old;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", old=" + old +
                    '}';
        }
    }
}
