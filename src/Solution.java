import java.util.*;

class Solution {

    public static void main(String[] args) {
        int[][] costs = {{0, 1, 1}, {0, 2, 2}, {1, 2, 5}, {1, 3, 1}, {2, 3, 8}};
        solution(4, costs);
    }

    public static int solution(int n, int[][] costs) {

        Cost[] costArr = new Cost[costs.length];

        // 섬 번호로 해당 섬이 속한 그룹을 가져올 수 있어야 한다.
        HashMap<Integer, Integer> islandGroupMap = new HashMap<Integer, Integer>();

        for (int i = 0; i < n; i++) {
            islandGroupMap.put(i, i);
        }

        for (int i = 0; i < costs.length; i++) {
            int start = costs[i][0];
            int end = costs[i][1];
            int cost = costs[i][2];
            costArr[i] = new Cost(cost, start, end);
        }

        Arrays.sort(costArr);

        int answer = 0;

        for (int i = 0; i < costArr.length; i++) {
            // costArr 에서 값을 꺼낸 뒤에 각각의 group 이 다르면 해당 다리를 건설한다.
            Cost nowCost = costArr[i];

            // 두 그룹이 다르다면
            if (islandGroupMap.get(nowCost.start) != islandGroupMap.get(nowCost.end)) {
                answer += nowCost.cost;
                // 그룹 하나로 뭉친다.
                int newGroup = islandGroupMap.get(nowCost.start);
                int oldGroup = islandGroupMap.get(nowCost.end);

                for (Integer index : islandGroupMap.keySet()) {
                    if (islandGroupMap.get(index) == oldGroup) {
                        islandGroupMap.put(index, newGroup);
                    }
                }
            }

        }

        return answer;
    }

    static class Cost implements Comparable<Cost> {
        int cost;
        int start;
        int end;

        public Cost(int cost, int start, int end) {
            this.cost = cost;
            this.start = start;
            this.end = end;
        }

        //desc
        @Override
        public int compareTo(Cost o) {
            return this.cost - o.cost;
        }
    }
}