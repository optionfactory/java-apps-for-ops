package net.optionfactory.javaexamples;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

public class Example05Leak {

    private final static int SLEEP_TIME_PRODUCER = 1;
    private final static int SLEEP_TIME_CONSUMER = 1000;
    private final static BlockingQueue<Double> QUEUE = new LinkedBlockingQueue<>();

    public static void main(String[] args) throws InterruptedException {
        System.out.println(String.format("Start main"));
        Thread.sleep(10000); // waiting time to attach profiler
        System.out.println(String.format("Starting threads"));
        final Thread producer = new Thread(new Producer(QUEUE, SLEEP_TIME_PRODUCER));
        final Thread consumer = new Thread(new Consumer(QUEUE, SLEEP_TIME_CONSUMER));
        System.out.println("Started producer and consumer threads");
        producer.start();
        consumer.start();
        System.out.println("Ending main");

    }

    public static class Producer implements Runnable {

        private final BlockingQueue<Double> queue;
        private final long sleepTime;

        public Producer(BlockingQueue<Double> queue, long sleepTime) {
            this.queue = queue;
            this.sleepTime = sleepTime;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    queue.add(Math.random());
                    System.out.println(String.format("elements in queue: %d", queue.size()));
                    Thread.sleep(sleepTime);
                }
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static class Consumer implements Runnable {

        private final BlockingQueue<Double> queue;
        private final long sleepTime;
        private final AtomicInteger counter = new AtomicInteger();

        public Consumer(BlockingQueue<Double> queue, long sleepTime) {
            this.queue = queue;
            this.sleepTime = sleepTime;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    final Double take = queue.take();
                    int c = counter.incrementAndGet();

                    System.out.println(String.format("[%d] take: %s", c, take));
                    Thread.sleep(sleepTime);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Example05Leak.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

}
