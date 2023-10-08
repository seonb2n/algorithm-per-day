import java.util.*;

//https://leetcode.com/problems/making-a-large-island/
class Solution {

    static int[] x_moves = {-1, 0, 1, 0};
    static int[] y_moves = {0, 1, 0, -1};

    public int largestIsland(int[][] grid) {

        int maxSize = 0;
        int N = grid.length;
        int M = grid[0].length;

        HashMap<Integer, Integer> flagSizeMap = new HashMap<>();
        int flag = 1;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    flag++;
                    int size = 1;
                    Queue<Node> nodeQueue = new LinkedList<>();
                    nodeQueue.add(new Node(i, j));
                    grid[i][j] = flag;
                    while (!nodeQueue.isEmpty()) {
                        Node nowNode = nodeQueue.poll();
                        for (int k = 0; k < 4; k++) {
                            int nextX = nowNode.x + x_moves[k];
                            int nextY = nowNode.y + y_moves[k];
                            if (inArea(nextX, nextY, N, M) && grid[nextX][nextY] == 1) {
                                size++;
                                grid[nextX][nextY] = flag;
                                nodeQueue.add(new Node(nextX, nextY));
                            }
                        }
                    }
                    flagSizeMap.put(flag, size);
                    maxSize = Math.max(maxSize, size);
                }
            }
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    int size = 1;
                    Set<Integer> flags = new HashSet<>();
                    for (int k = 0; k < 4; k++) {
                        int nextX = i + x_moves[k];
                        int nextY = j + y_moves[k];
                        if (inArea(nextX, nextY, N, M)) {
                            if (grid[nextX][nextY] > 1) {
                                flags.add(grid[nextX][nextY]);
                            }
                        }
                    }
                    Integer[] arr = flags.toArray(new Integer[0]);

                    for (Integer integer : arr) {
                        size += flagSizeMap.get(integer);
                    }
                    maxSize = Math.max(maxSize, size);
                }
            }
        }

        return maxSize;
    }

    boolean inArea(int x, int y, int N, int M) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    class Node {
        int x;
        int y;
        Node (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}