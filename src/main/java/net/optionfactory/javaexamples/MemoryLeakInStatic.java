package net.optionfactory.javaexamples;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class MemoryLeakInStatic {

    private final static int ELEMENTS_IN_LIST = 10_000_000;
    private final static long SLEEP_TIME_IN_MILLIS = 600_000;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Start main");
        Thread.sleep(5_000);
        System.out.println("Create lots of temporaries");
        new NotStaticListWithTemps().appendElements(ELEMENTS_IN_LIST);
        Thread.sleep(15_000);
        forceGarbageCollector();
        System.out.println("Add elements in not static list");
        new NotStaticList().appendElements(ELEMENTS_IN_LIST);
        Thread.sleep(15_000);
        forceGarbageCollector();
        Thread.sleep(15_000);
        System.out.println("Add elements in static list");
        new StaticList().appendElements(ELEMENTS_IN_LIST);
        Thread.sleep(15_000);
        forceGarbageCollector();
        System.out.println("Waiting");
        Thread.sleep(SLEEP_TIME_IN_MILLIS);
        System.out.println("Ending main");

    }

    public static void forceGarbageCollector() throws InterruptedException {
        System.out.println("Force garbage collector");
        System.gc();
    }

    public static class StaticList {

        private static List<Double> BUFFER = new ArrayList<>();

        private void appendElements(int elements) throws InterruptedException {
            for (int c = 0; c < 6000; c++) {
                Thread.sleep(10);
                IntStream.range(0, elements / 6000)
                        .forEach(i -> BUFFER.add(Math.random()));
            }
        }
    }

    public static class NotStaticList {

        private List<Double> buffer = new ArrayList<>();

        private void appendElements(int elements) throws InterruptedException {
            for (int c = 0; c < 6000; c++) {
                Thread.sleep(10);
                IntStream.range(0, elements / 6000)
                        .forEach(i -> buffer.add(Math.random()));
            }
        }
    }

    public static class NotStaticListWithTemps {

        private List<Double> buffer = new ArrayList<>();

        private void appendElements(int elements) throws InterruptedException {
            for (int c = 0; c < 6000; c++) {
                List<String> temps = new ArrayList<>();
                Thread.sleep(10);
                IntStream.range(0, elements / 6000)
                        .peek(i -> temps.add(Double.valueOf(Math.random()).toString()))
                        .forEach(i -> buffer.add(Math.random()));
            }
        }
    }
}
