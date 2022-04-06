import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    static BufferedReader br;
    static int groupA;
    static int groupB;
    static int groupC;
    static boolean[][] isVisited;
    static int answer;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        groupA = Integer.parseInt(str[0]);
        groupB = Integer.parseInt(str[1]);
        groupC = Integer.parseInt(str[2]);
        answer = 0;

        isVisited = new boolean[1501][1501];

        if((groupA + groupB + groupC) % 3 != 0) {
            System.out.println(0);
        }
        else {
            DFS(groupA, groupB, groupC);
            System.out.println(answer);
        }

    }

    public static void DFS(int a, int b, int c) {
        if(a == b && b == c) {
            answer = 1;
            return;
        }
        //세 숫자에 대해서 값을 변조
        play(a, b, c);
        play(a, c, b);
        play(b, c, a);
    }

    public static void play(int a, int b, int c) {
        int min = Math.min(a, b);
        int max = Math.max(a, b);

        max = max - min;
        min *= 2;

        if(!isVisited[min][max]) {
            isVisited[min][max] = isVisited[max][min] = true;
            DFS(max, min, c);
        }
    }
}