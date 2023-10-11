package LambdaTest;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Stream;

public class PeekTest {

    public static void main(String[] args) {
        File[] fileArr = { new File("t1.txt"), new File("t2.txt") };

        Stream.of(fileArr).map(File::getName).peek(t -> System.out.printf("filename = %s%n", t)).forEach(System.out::println);
    }

}
