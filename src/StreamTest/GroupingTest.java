package StreamTest;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class GroupingTest {

    public static void main(String[] args) {
        List<String> list = List.of("apple", "banana", "cat", "egg", "board");
        Map<Character, List<String>> listByFirstCharacter = list.stream().collect(groupingBy(s -> s.charAt(0)));
        System.out.println(listByFirstCharacter.get('b')); // [banana, board]

        List<String> list2 = List.of("apple", "Banana", "cat", "Egg", "board");

        Map<Integer, Long> listByLength = list2.stream().collect(groupingBy(s -> {
            if (s.length() < 5) return 5;
                else return 0;
        }, counting()));
        System.out.println(listByLength.get(0)); // 3
    }

}
