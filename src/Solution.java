import java.util.*;

// https://leetcode.com/problems/combintion-sum/
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        // dfs
        dfs(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }


    void dfs(int[] candidates, int target, int nowIndex, List<Integer> current, List<List<Integer>> result) {
        // 현재 조합의 합이 target 과 같다.
        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        if (target < 0) {
            return;
        }

        for (int i = nowIndex; i < candidates.length; i++) {
            current.add(candidates[i]);
            dfs(candidates, target - candidates[i], i, current, result);
            current.remove(current.size() - 1);
        }
    }
}

