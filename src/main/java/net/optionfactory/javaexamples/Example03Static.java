package net.optionfactory.javaexamples;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Example03Static {

    private final static int ELEMENTS_IN_LIST = 10_000_000;
    private final static long SLEEP_TIME_IN_MILLIS = 600_000;

    public static void main(String[] args) throws InterruptedException {
        System.out.println(String.format("Start main"));
        Thread.sleep(10_000); // waiting time to attach profiler
        System.out.println(String.format("Add elements in not static list"));
//        new NotStaticList().appendElements(ELEMENTS_IN_LIST);
        forceGarbageCollector();
        System.out.println(String.format("Add elements in static list"));
        new StaticList().appendElements(ELEMENTS_IN_LIST);
        forceGarbageCollector();
        System.out.println(String.format("Waiting"));
        Thread.sleep(SLEEP_TIME_IN_MILLIS);
        System.out.println("Ending main");

    }

    public static void forceGarbageCollector() throws InterruptedException {
        Thread.sleep(5_000);
        System.out.println(String.format("Force garbage collector"));
        System.gc();
        Thread.sleep(5_000);
    }

    public static class StaticList {

        private static List<Double> BUFFER = new ArrayList<>();

        private void appendElements(int elements) {
            IntStream.range(0, elements)
                    .forEach(i -> BUFFER.add(Math.random()));
        }
    }

    public static class NotStaticList {

        private List<Double> buffer = new ArrayList<>();

        private void appendElements(int elements) {
            IntStream.range(0, elements)
                    .forEach(i -> buffer.add(Math.random()));
        }
    }

}
