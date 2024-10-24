import java.util.*;

//https://leetcode.com/problems/add-two-numbers/

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode tempA = l1;
        ListNode tempB = l2;

        ListNode startResult = new ListNode(0);
        ListNode result = startResult;
        int nextVal = 0;
        while (tempA != null || tempB != null) {
            // 둘다 값 있음
            if (tempA != null && tempB != null) {
                result.next = new ListNode((tempA.val + tempB.val + nextVal) % 10);
                nextVal = (tempA.val + tempB.val + nextVal) / 10;
                tempA = tempA.next;
                tempB = tempB.next;
            }
            // A 만 값 있음
            else if (tempA != null && tempB == null) {
                result.next = new ListNode((tempA.val + nextVal) % 10);
                nextVal = (tempA.val + nextVal) / 10;
                tempA = tempA.next;
            }
            // B 만 값 있음
            else if (tempB != null && tempA == null) {
                result.next = new ListNode((tempB.val + nextVal) % 10);
                nextVal = (tempB.val + nextVal) / 10;
                tempB = tempB.next;
            }
            result = result.next;
        }
        if (nextVal != 0) {
            result.next = new ListNode(nextVal);
        }

        return startResult.next;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {

    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
