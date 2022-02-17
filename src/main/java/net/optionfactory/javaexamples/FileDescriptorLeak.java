package net.optionfactory.javaexamples;

import java.io.FileOutputStream;
import java.io.IOException;

public class FileDescriptorLeak {
    public static void main(String[] args) throws IOException, InterruptedException {
        for (int i=0; i<2048; i++) {
            System.out.println(".");
            var fos = new FileOutputStream("/dev/null");
            fos.write(42);
            Thread.sleep(100);
        }
    }
}
