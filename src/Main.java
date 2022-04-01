import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main{

    static int N;
    static int M;
    static int K;
    static char[][] map;
    static String targetWord;
    static int wordSize;
    static int answer;
    static int[] x_move = {-1, 1, 0, 0};
    static int[] y_move = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        targetWord = br.readLine();
        wordSize = targetWord.length() - 1;
        answer = 0;
        char firstWord = targetWord.charAt(0);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] == firstWord) {
                    DFS(0, i, j);
                }
            }
        }

        System.out.println(answer);
    }

    public static void DFS(int nowChar, int nowY, int nowX) {
        if(nowChar == wordSize) {
            answer++;
        }
        else {
            int targetChar = nowChar+1;
            char target = targetWord.charAt(targetChar);
            for (int i = 0; i < 4; i++) {
                for (int j = 1; j <= K; j++) {
                    int newX = nowX + x_move[i] * j;
                    int newY = nowY + y_move[i] * j;
                    if(inArea(newY, newX) && map[newY][newX] == target) {
                        DFS(targetChar, newY, newX);
                    }
                }
            }
        }
    }

    public static boolean inArea(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < M;
    }
}