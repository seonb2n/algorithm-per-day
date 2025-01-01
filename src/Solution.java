
// https://leetcode.com/problems/diagonal-traverse-ii/
class Solution {
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int count = 0;

        // 대각선 합을 기준으로 그룹화
        for (int i = 0; i < nums.size(); i++) {
            List<Integer> row = nums.get(i);
            for (int j = 0; j < row.size(); j++) {
                int sum = i + j;  // 대각선 상의 원소들은 row + col 값이 같음
                map.computeIfAbsent(sum, k -> new ArrayList<>()).add(nums.get(i).get(j));
                count++;
            }
        }

        // 결과 배열 생성
        int[] result = new int[count];
        int index = 0;

        // map의 key를 정렬하여 순회
        for (int i = 0; i <= map.size() - 1; i++) {
            if (map.containsKey(i)) {
                List<Integer> temp = map.get(i);
                // 같은 대각선 상의 원소들은 역순으로 추가
                for (int j = temp.size() - 1; j >= 0; j--) {
                    result[index++] = temp.get(j);
                }
            }
        }

        return result;
    }
}
