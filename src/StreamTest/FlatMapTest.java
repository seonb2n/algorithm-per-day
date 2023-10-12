package StreamTest;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

public class FlatMapTest {

    public static void main(String[] args) {
        String[] lineArr = {"Believe or not It is true", "DO or do not There is not try"};
        Stream<Stream<String>> strStream = Arrays.stream(lineArr).map(line -> Stream.of(line.split(" +")));
//        strStream.forEach(System.out::println);
        Stream<String> strStreamWithFlatMap = Arrays.stream(lineArr).flatMap(line -> Stream.of(line.split(" +")));
//        strStreamWithFlatMap.forEach(System.out::println);
        strStreamWithFlatMap.map(String::toLowerCase).distinct().sorted().forEach(System.out::println);

        int result = Optional.of("123").filter(x -> !x.isEmpty()).map(Integer::parseInt).orElse(-1); //123
    }

}
