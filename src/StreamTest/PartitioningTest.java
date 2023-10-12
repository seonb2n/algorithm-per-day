package StreamTest;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.partitioningBy;

public class PartitioningTest {

    public static void main(String[] args) {
        List<String> list = List.of("apple", "banana", "cat", "egg", "board");
        Map<Boolean, List<String>> partitioned = list.stream().collect(partitioningBy(s -> s.contains("e")));
        List<String> containE = partitioned.get(true);
        List<String> notContainE = partitioned.get(false);
        System.out.println(containE.toString());
        System.out.println(notContainE.toString());

        Map<Boolean, Long> numByE = list.stream().collect(partitioningBy(s -> s.contains("e"), counting()));
        System.out.println(numByE.get(true));
        System.out.println(numByE.get(false));
    }

}
