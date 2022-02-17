package net.optionfactory.javaexamples;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.IntStream;

public class MemoryLeakDueToMissingEqHash {

    private final static int ELEMENTS_IN_LIST = 10_000_000;

    public static void main(String[] args) throws InterruptedException {
        System.out.println(String.format("Start main"));
        Thread.sleep(10000); // waiting time to attach profiler
        System.out.println(String.format("Add elements in cache"));
        final CacheUsers caches = new CacheUsers();
        IntStream.range(0, ELEMENTS_IN_LIST)
                .forEach(e -> caches.add(new User("foo")));
        final Set<User> elements = caches.getCaches();
        System.out.println(String.format("Elements in set: %d", elements.size()));
        Thread.sleep(5_000);
        // FIXME: GB can not be executed, elements are in heap memory also with hashcode defined
        System.out.println("Ending main");

    }

    public static class CacheUsers {

        private final Set<User> caches = new HashSet<>();

        public void add(User myObject) {
            caches.add(myObject);
        }

        public Set<User> getCaches() {
            return caches;
        }

    }

    public static class User {

        public String name;

        public User(String name) {
            this.name = name;
        }

        // Uncomment to fix code
//        @Override
//        public int hashCode() {
//            int hash = 3;
//            hash = 43 * hash + Objects.hashCode(this.name);
//            return hash;
//        }
//
//        @Override
//        public boolean equals(Object obj) {
//            if (this == obj) {
//                return true;
//            }
//            if (obj == null) {
//                return false;
//            }
//            if (getClass() != obj.getClass()) {
//                return false;
//            }
//            final User other = (User) obj;
//            if (!Objects.equals(this.name, other.name)) {
//                return false;
//            }
//            return true;
//        }
    }
}
