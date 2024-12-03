// https://leetcode.com/problems/permutations=ii

import java.util.*;


class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        dfs(nums, visited, new ArrayList<>(), res);
        return res;
    }

    private void dfs(int[] nums, boolean[] visited, List<Integer> current, List<List<Integer>> res) {
        if (current.size() == nums.length) {
            res.add(new ArrayList<>(current));
            return;
        }


        int prev = -11;

        for (int i = 0; i < nums.length; i++) {
            if (visited[i] || prev == nums[i]) {
                continue;
            }

            if (!visited[i] && prev != nums[i]) {
                visited[i] = true;
                current.add(nums[i]);
                dfs(nums, visited, current, res);
                current.remove(current.size() - 1);
                visited[i] = false;
                prev = nums[i];
            }
        }
    }
}
