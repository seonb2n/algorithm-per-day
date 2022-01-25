import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        int caseNumber = Integer.parseInt(br.readLine());
        int[][] timeTable = new int[caseNumber][2];

        for(int i = 0; i < caseNumber; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            timeTable[i][0] = Integer.parseInt(st.nextToken());	// 시작시간
            timeTable[i][1] = Integer.parseInt(st.nextToken());	// 종료시간
        }

        Arrays.sort(timeTable, (o1, o2) -> {
            // 종료시간이 같을 경우 시작시간이 빠른순으로 정렬
            if(o1[1] == o2[1]) {
                return o1[0] - o2[0];
            }
            // 종료시간이 빠른 순으로 정렬
            return o1[1] - o2[1];
        });

        int max = 0;
        int nowEndTime = 0;

        for(int i = 0; i < caseNumber; i++) {
            if(nowEndTime <= timeTable[i][0]) {
                nowEndTime = timeTable[i][1];
                max++;
            }
        }

        System.out.println(max);
    }

}

