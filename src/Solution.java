import java.util.*;

// https://leetcode.com/problems/combintion-sum-ii/
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        // dfs
        dfs(candidates, target, 0, new ArrayList<>(), result);
        return new ArrayList<>(result);
    }


    void dfs(int[] candidates, int target, int nowIndex, List<Integer> current, List<List<Integer>> result) {
        // 현재 조합의 합이 target 과 같다.
        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = nowIndex; i < candidates.length; i++) {
            if (i > nowIndex && candidates[i] == candidates[i-1]) {
                continue;
            }
            if (candidates[i] > target) break;

            current.add(candidates[i]);
            dfs(candidates, target - candidates[i], i+1, current, result);
            current.remove(current.size() - 1);
        }
    }
}
