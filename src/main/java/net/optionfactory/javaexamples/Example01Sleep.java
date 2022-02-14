package net.optionfactory.javaexamples;

public class Example01Sleep {

    private final static long SLEEP_TIME_IN_MILLIS = 600_000;

    public static void main(String[] args) throws InterruptedException {
        System.out.println(String.format("Started, now sleep for %d", SLEEP_TIME_IN_MILLIS));
        sleep(SLEEP_TIME_IN_MILLIS);
        System.out.println("Ending");
    }

    private static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
