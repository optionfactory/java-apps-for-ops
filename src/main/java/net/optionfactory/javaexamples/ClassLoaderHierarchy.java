package net.optionfactory.javaexamples;

public class ClassLoaderHierarchy {
    public static void main(String[] args) {
        System.out.println("Classloader of String is: " + String.class.getClassLoader());
        System.out.println("Classloader of " + ClassLoaderHierarchy.class + " is: " + ClassLoaderHierarchy.class.getClassLoader().toString());

        System.out.println("Classloader hierarchy for us is:");
        var classloader = ClassLoaderHierarchy.class.getClassLoader();
        while (true) {
            System.out.println("# " + classloader);
            if (classloader == null) {
                break;
            }
            classloader = classloader.getParent();
        }
    }
}
