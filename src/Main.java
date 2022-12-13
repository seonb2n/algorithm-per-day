import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.*;

class Main {

    static int N;
    static int cases;
    static int[][] memo;
    static Node[] caseArr;

    /**
     * https://www.acmicpc.net/problem/2618
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        cases = Integer.parseInt(br.readLine());

        caseArr = new Node[cases+1];

        for (int i = 1; i <= cases; i++) {
            String[] tmp = br.readLine().split(" ");
            Node node = new Node(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]));
            caseArr[i] = node;
        }

        //A 경찰차와 B 경찰차가 각각 i, j 케이스를 해결했을 때 총 이동한 거리
        memo = new int[cases+1][cases+1];

        for (int i = 0; i <= cases; i++) {
            Arrays.fill(memo[i], Integer.MAX_VALUE-1);
        }

        memo[0][0] = 0;
        memo[1][0] = caseArr[1].y + caseArr[1].x;
        memo[0][1] = Math.abs(N - caseArr[1].y) + Math.abs(N - caseArr[1].x);

        //todo 현재 지점부터 탐색하면서 i, j 둘 중 하나는 마지막에 도달해야 함.
        for (int i = 1; i <= cases; i++) {
            for (int j = 0; j < i; j++) {
                memo[i][j]
            }
        }

        System.out.println(memo[cases][cases]);
    }

    public static int getDist(int next, int now) {
        int nowY = caseArr[now].y;
        int nowX = caseArr[now].x;

        int nextY = caseArr[next].y;
        int nextX = caseArr[next].x;

        return Math.abs(nowY - nextY) + Math.abs(nowX - nextX);
    }

    public static class Node {
        int y;
        int x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

}
