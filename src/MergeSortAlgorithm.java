import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MergeSortAlgorithm {

    public static List<Integer> origin;
    public static int[] tmp;

    public static void main(String[] args) {
        origin = List.of(38, 27, 43, 3, 9, 82, 10);
        var result = mergeSort(origin);
        StringBuilder sb = new StringBuilder();
        while (!result.isEmpty()) {
            sb.append(result.poll());
            sb.append(",");
        }
        System.out.println(sb);
    }

    public static Queue<Integer> mergeSort(List<Integer> originList) {
        if (originList.size() == 1) {
            LinkedList<Integer> list = new LinkedList<Integer>();
            list.add(originList.get(0));
            return list;
        }
        int mid = originList.size() / 2;
        // mid 기준 좌측은 정렬된 상태
        var leftList = mergeSort(originList.subList(0, mid));
        // mid 기준 우측은 정렬된 상태
        var rightList = mergeSort(originList.subList(mid, originList.size()));

        // 왼쪽과 오른쪽 값을 하나씩 꺼내서 병합한다.
        // 왼쪽과 오른쪽 리스트를 모두 비우기 전까지 반복한다.
        Queue<Integer> result = new LinkedList<>();
        while (!leftList.isEmpty() && !rightList.isEmpty()) {
            if (leftList.peek() < rightList.peek()) {
                result.add(leftList.poll());
            } else {
                result.add(rightList.poll());
            }
        }
        while (!rightList.isEmpty()) {
            result.add(rightList.poll());
        }

        while (!leftList.isEmpty()) {
            result.add(leftList.poll());
        }
        return result;
    }
}
