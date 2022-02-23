package net.optionfactory.javaexamples;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

public class AccidentalSerialization {

    private final static long PRIME_NUMBERS_TILL = 10_000_000;

    public static void main(String[] args) throws InterruptedException {

        final List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            final Thread thread = new Thread(new DefectivePrimeThread(PRIME_NUMBERS_TILL));
            thread.start();
            threads.add(thread);
        }
        System.out.println("Waiting threads ending");
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println("Ending main");
    }

    public static class DefectivePrimeThread implements Runnable {

        private final long upper;

        public DefectivePrimeThread(long upper) {
            this.upper = upper;
        }

        @Override
        public void run() {
            LongStream.rangeClosed(2, upper)
                    .filter(this::isPrime)
                    .forEach(System.out::println);
            System.out.println("Ending prime thread");
        }

        public static boolean isDivisible(long a, long b) {
            return a % b == 0;
        }

        public static void mysteriousMethod() {
            try {
                DefectivePrimeThread.class.getClassLoader().loadClass("foo.bar");
            } catch (Exception ex) {
                // no problem
            }
        }

        private boolean isPrime(long number) {
            return LongStream.range(2, number)
                    .allMatch(n -> {
                        mysteriousMethod();
                        return number % 2 != 0;
                    });
        }
    }
}
