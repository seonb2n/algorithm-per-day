import java.util.*;

class Solution {
    static Set<Character> left;
    public static void main(String[] args) {
        String[] gems = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
        solution(gems);
    }

    public static int[] solution(String[] gems) {
        Map<String, Integer> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        int len = Integer.MAX_VALUE, start = 0, idx = 0;

        set.addAll(Arrays.asList(gems));

        for (int i = 0; i < gems.length; i++) {
            map.put(gems[i], map.getOrDefault(gems[i], 0) + 1);
            queue.add(gems[i]);

            while (map.get(queue.peek()) > 1) {
                map.put(queue.peek(), map.get(queue.poll()) - 1);
                idx++;
            }

            if (map.size() == set.size() && len > (i - idx)) {
                len = i - idx;
                start = idx + 1;
            }
        }

        return new int[]{start, start + len};
    }

    public static class Book {
        String serialNumber;
        String bookTitle;

        @Override
        public boolean equals(Object o) {
            Book book = (Book) o;
            return (Objects.equals(((Book) o).serialNumber, this.serialNumber));
        }
    }
}