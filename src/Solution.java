import java.util.*;

class Solution {


    static int[] x_moves = {-1, 0, 1, 0};
    static int[] y_moves = {0, 1, 0, -1};

    public int orangesRotting(int[][] grid) {
        int N = grid.length;
        int M = grid[0].length;

        Queue<Node> rottenTomatoQueue = new LinkedList<>();
        Set<Node> freshTomato = new HashSet<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    rottenTomatoQueue.add(new Node(i, j));
                }
                else if (grid[i][j] == 1) {
                    freshTomato.add(new Node(i, j));
                }
            }
        }

        Queue<Node> nextQueue = new LinkedList<>();
        int turn = 0;
        do {
            while (!rottenTomatoQueue.isEmpty()) {
                Node now = rottenTomatoQueue.poll();
                for (int i = 0; i < 4; i++) {
                    int nextX = now.x + x_moves[i];
                    int nextY = now.y + y_moves[i];
                    if (inArea(nextX, nextY, N , M) && grid[nextX][nextY] == 1) {
                        freshTomato.remove(new Node(nextX, nextY));
                        nextQueue.add(new Node(nextX, nextY));
                        grid[nextX][nextY] = 2;
                    }
                }
            }
            turn++;
            rottenTomatoQueue.addAll(nextQueue);
            nextQueue = new LinkedList<>();
        } while (!rottenTomatoQueue.isEmpty());

        if (!freshTomato.isEmpty()) {
            return -1;
        } else {
            return turn;
        }
    }

    class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return x == node.x && y == node.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }


    static boolean inArea(int x, int y, int N, int M) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}