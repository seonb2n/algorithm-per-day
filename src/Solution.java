import java.util.*;

/**
 * 프로그래머스
 * https://school.programmers.co.kr/learn/courses/30/lessons/67260 (동굴탐험)
 */

class Solution {

    private int size;
    private List<List<Integer>> graph;
    private int[] before;
    private int[] after;

    public boolean solution(int n, int[][] path, int[][] order) {
        //탐색 중 방문한 노드를 check 한다.
        size = n;
        graph = new ArrayList<>();
        before = new int[n];
        after = new int[n];
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        // 각 node 별로 해당 node 를 방문하기 위해서 필요한 선행 노드의 목록을 정리한다.
        for (int[] arr : path) {
            graph.get(arr[0]).add(arr[1]);
            graph.get(arr[1]).add(arr[0]);
        }
        for (int[] arr : order) {
            before[arr[1]] = arr[0];
            after[arr[0]] = arr[1];
        }

        return isAllRoomCanBeSearched();
    }

    private boolean isAllRoomCanBeSearched() {
        int visitedRoomNumber = 0;
        Queue<Integer> queue = new LinkedList<>();
        int[] visited = new int[size]; // 0: 방문 X, 1: 방문했지만 선후관계 충족 X, 2: 방문 가능 상태에서 방문
        // 0 에서부터 탐색을 시작한다.
        if (before[0] == 0) {
            queue.offer(0);
            visited[0] = 2;
        }

        while (!queue.isEmpty()) {
            int nowNode = queue.poll();
            visitedRoomNumber++;
            //지금 노드에서 방문할 수 있는 노드를 하나씩 꺼낸다.
            for (int nextNode : graph.get(nowNode)) {
                // 다음 노드가 이미 방문한 노드라면 넘어간다.
                if (visited[nextNode] == 2) {
                    continue;
                }

                // 다음 노드를 방문하기 위해서 필요한 노드가 아직 방문 노드가 아니므로 1 로 체크하고 넘어간다.
                if (visited[before[nextNode]] != 2) {
                    visited[nextNode] = 1;
                    continue;
                }

                // 다음 노드가 방문한 노드도 아니고, 방문에 필요한 제약도 충족했다면
                // 다음 노드를 큐에 추가한다.
                queue.offer(nextNode);
                // 방문 처리를 해준다.
                visited[nextNode] = 2;
            }
            // 지금 노드를 방문한 후에 갈 수 있는 노드를 꺼낸다.
            int savedNode = after[nowNode];
            // 갈 수 있는 노드가 0 이 아니고, 아직 방문 처리가 안됐다면
            if (savedNode != 0 && visited[savedNode] == 1) {
                queue.offer(savedNode);
                // 해당 노드를 방문처리해준다.
                visited[savedNode] = 2;
            }

        }
        // 모든 노드를 한 번씩 방문한 것으로 처리되기 때문에
        return visitedRoomNumber == size;
    }
}