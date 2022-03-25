import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main{

    static int N;
    static int Q;
    static ArrayList<Node>[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        dist = new ArrayList[N+1];

        for (int i = 0; i <= N; i++) {
            dist[i] = new ArrayList<>();
        }

        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int startPoint = Integer.parseInt(st.nextToken());
            int endPoint = Integer.parseInt(st.nextToken());
            int usado = Integer.parseInt(st.nextToken());

            dist[startPoint].add(new Node(endPoint, usado));
            dist[endPoint].add(new Node(startPoint, usado));
        }

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int k = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            sb.append(getUsado(n, k));
            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static int getUsado(int startPoint, int maxUsado) {
        boolean[] isVisited = new boolean[N+1];
        isVisited[startPoint] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startPoint);

        int ans = 0;
        while (!queue.isEmpty()) {
            int nowNode = queue.poll();

            for (int i = 0; i < dist[nowNode].size(); i++) {
                if(!isVisited[dist[nowNode].get(i).end] && dist[nowNode].get(i).usado >= maxUsado) {
                    queue.add(dist[nowNode].get(i).end);
                    isVisited[dist[nowNode].get(i).end] = true;
                    ans++;
                }
            }
        }
        return ans;
    }

    public static class Node {
        int end;
        int usado;

        public Node(int end, int usado) {
            this.end = end;
            this.usado = usado;
        }

    }
}