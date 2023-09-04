import java.util.*;

class Solution {
    // 주어진 TreeNode root 가 이진 트리인지 체크해야 한다.
    public boolean isValidBST(TreeNode root) {
        return checker(root, Long.MAX_VALUE, Long.MAX_VALUE);
    }

    private boolean checker(TreeNode node, long start, long end) {
        if (node == null) return true;
        // node 가 start - end 의 바운더리를 벗어나는지 체크한다.
        if (node.val <= start || node.val >= end) return false;

        // 왼쪽 자식은 부모보다 작아야하고, 오른쪽 자식은 부모보다 커야한다.
        return checker(node.left, start, node.val) && checker(node.right, node.val, end);
    }

    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
}