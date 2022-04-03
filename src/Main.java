import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main{

    static int N;
    static int M;
    static BufferedReader br;
    static Line[] lines;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        lines = new Line[M];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            lines[i] = new Line(Integer.parseInt(st.nextToken()),
                            Integer.parseInt(st.nextToken()),
                            Integer.parseInt(st.nextToken()));
        }



    }

    public static int DFS(int destination, int nowNode, int nowTime) {
        if(nowNode == destination) {
            return nowTime;
        }
        
    }

    public static class Line {
        int from;
        int to;
        int time;

        public Line(int from, int to, int time) {
            this.from = from;
            this.to = to;
            this.time = time;
        }
    }
}