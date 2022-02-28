package net.optionfactory.javaexamples;

public class Example00Main {
    public String name;

    public String getName() {
        return this.name;
    }

    public static int getVersion() {
        return 42;
    }

    public static void main(String[] args) {
        System.out.println("Arguments: ");
        for (String arg: args) {
            System.out.println(arg);
        }
        System.out.println(Foo.class.getName());
    }

    public static class Foo {}
}

