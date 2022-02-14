package net.optionfactory.javaexamples;

import java.util.stream.LongStream;

public class Example02Threads {

    private final static long SLEEP_TIME_IN_MILLIS = 600_000;
    private final static long PRIME_NUMBERS_TILL = 1_000_000;

    public static void main(String[] args) throws InterruptedException {
        System.out.println(String.format("Start main"));
        final Thread thread = new Thread(new SleepThread(SLEEP_TIME_IN_MILLIS));
        final Thread thread2 = new Thread(new PrimeThread(PRIME_NUMBERS_TILL));
        System.out.println(String.format("Start sleep thread for %dms", SLEEP_TIME_IN_MILLIS));
        thread.start();
        System.out.println(String.format("Start prime thread till %d", PRIME_NUMBERS_TILL));
        thread2.start();
        System.out.println("Waiting threads ending");
        thread.join();
        thread2.join();
        System.out.println("Ending main");

    }

    public static class SleepThread implements Runnable {

        private final long sleepTime;

        public SleepThread(long sleepTime) {
            this.sleepTime = sleepTime;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(sleepTime);
                System.out.println("Ending sleep thread");
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static class PrimeThread implements Runnable {

        private final long upper;

        public PrimeThread(long upper) {
            this.upper = upper;
        }

        @Override
        public void run() {
            LongStream.rangeClosed(2, upper)
                    .boxed()
                    .filter(n -> isPrime(n))
                    .forEach(n -> System.out.println(n));
            System.out.println("Ending prime thread");
        }

        private boolean isPrime(long number) {
            return LongStream.range(2, number)
                    .allMatch(n -> number % n != 0);
        }
    }
}
