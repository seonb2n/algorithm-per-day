import java.util.*;

class Solution {

    public static int solution(int[] stones, int k) {
        int answer = 0;

        Node[] nodes = new Node[stones.length];
        for (int i = 0; i < stones.length; i++) {
            nodes[i] = new Node(i, stones[i]);    
        }
        // stones 를 오름차순으로 정렬함
        Arrays.sort(nodes);
        Set<Integer> zeroStoneSet = new HashSet<Integer>();
        int index = 0;
        int lastNumber = 0;
        while (true) {
            answer += (nodes[index].stoneSize - lastNumber);
            lastNumber = nodes[index].stoneSize;
            zeroStoneSet.add(nodes[index].index);
            index++;
            // lastNumber 와 동일한 stoneSize를 모두 처리해준다.
            while (index < stones.length && nodes[index].stoneSize == lastNumber) {
                zeroStoneSet.add(nodes[index].index);
                index++;
            }
            // 해당 stones 가 0 이 됐을 때, k 에 위배되는지 확인
            if (containsConsecutive(zeroStoneSet, k)) {
                break;
            }
        }
        
        return answer;
    }
    
    // set 안에 연속된 숫자가 k 개 만큼 존재하는지 확인한다.
    public static boolean containsConsecutive(Set<Integer> set, int k) {
        for (int num : set) {
            boolean isConsecutive = true;
            for (int i = 1; i < k; i++) {
                if (!set.contains(num + i)) {
                    isConsecutive = false;
                    break;
                }
            }
            if (isConsecutive) {
                return true;
            }
        }
        return false;
    }

    public static class Node implements Comparable<Node> {
        int index;
        int stoneSize;

        public Node(int index, int stoneSize) {
            this.index = index;
            this.stoneSize = stoneSize;
        }

        @Override
        public int compareTo(Node o) {
            return this.stoneSize - o.stoneSize;
        }
    }
}