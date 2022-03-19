import java.util.*;

class Solution {
    int n, root, cnt;
    int[] parent, left, right, people;

    public int solution(int k, int[] num, int[][] links) {
        n = num.length;
        parent = new int[n];
        left = new int[n];
        right = new int[n];
        people = num;
        int lo = 0, hi = (int) 1e9;

        Arrays.fill(parent, -1);

        //각 i 별로 왼쪽 노드와 오른쪽 노드, 부모 노드를 채운다.
        for(int i = 0; i < links.length; i++) {
            left[i] = links[i][0];
            right[i] = links[i][1];

            if(left[i] != -1)
                parent[left[i]] = i;
            if(right[i] != -1)
                parent[right[i]] = i;

            lo = Math.max(lo, people[i]);
        }

        //루트 노드를 찾는다.
        for(int i = 0; i < n; i++)
            if(parent[i] == -1) {
                root = i;
                break;
            }

        while(lo <= hi) {
            int mid = (lo + hi) / 2;
            if(getGroup(mid) <= k) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return hi + 1;
    }

    //limit 이하의 사람 수를 가진 그룹이 몇 개가 나오는가.
    public int getGroup(int limit) {
        //현재 그룹 수
        cnt = 0;
        dfs(root, limit);
        return cnt + 1;
    }

    //지금 노드에서 탐색할 때, limit 수가 넘으면 그룹을 나눈다.
    public int dfs(int currentNode, int limit) {
        int leftStudentNumber = 0;
        int rightStudentNumber = 0;

        //왼쪽 하위 노드로 탐색을 한다.
        if(left[currentNode] != -1)
            leftStudentNumber = dfs(left[currentNode], limit);

        //오른쪽 하위 노드로 탐색을 한다.
        if(right[currentNode] != -1)
            rightStudentNumber = dfs(right[currentNode], limit);

        //숫자가 작으면 지금 그룹을 유지해도 된다.
        if(people[currentNode] + leftStudentNumber + rightStudentNumber <= limit) {
            return people[currentNode] + leftStudentNumber + rightStudentNumber;
        }

        //숫자가 크지만, 둘 중 왼쪽과 오른쪽 중 작은쪽만 사용해도 된다면 그룹을 쪼갠다.
        if(people[currentNode] + Math.min(leftStudentNumber, rightStudentNumber) <= limit) {
            cnt++;
            return people[currentNode] + Math.min(leftStudentNumber, rightStudentNumber);
        }

        //루트, 왼쪽 하위, 오른쪽 하위 3개로 그룹을 쪼개야 한다.
        cnt += 2;
        return people[currentNode];
    }
}