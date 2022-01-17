import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static StringBuilder sb;
    static int[] stairs;
    static int[] results;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int caseNumber = Integer.parseInt(br.readLine());
        stairs = new int[caseNumber+1];
        results = new int[caseNumber+1];

        for (int i = 1; i <= caseNumber; i++) {
            stairs[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 1; i <= caseNumber; i++) {
            results[i] = getMax(i);
        }

        sb.append(results[caseNumber]);

        System.out.println(sb);
    }

    private static int getMax(int nowStair) {
        if(nowStair == 1) {
            return stairs[nowStair];
        }
        if(nowStair == 2) {
            return results[1] + stairs[nowStair];
        }
        if(nowStair == 3) {
            return Math.max(results[1] + stairs[nowStair], stairs[2] + stairs[3]);
        }

        return Math.max(results[nowStair-2] + stairs[nowStair], results[nowStair-3] + stairs[nowStair-1] + stairs[nowStair]);

    }
}
