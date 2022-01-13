import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static StringBuilder sb;
    static int[][] abilityArr;
    static int min;
    static int numberCount;
    static int[] team_start;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        numberCount = Integer.parseInt(br.readLine());
        abilityArr = new int[numberCount][numberCount];
        team_start = new int[numberCount];
        min = 99999;

        for (int i = 0; i < numberCount; i++) {
            abilityArr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        dfs(0, 0);

        System.out.println(min);
    }

    private static void dfs(int startNumber, int depth) {
        //절반의 플레이어를 뽑았을 때 차이값 계산
        if (depth == numberCount/2) {
            //team_link 명단 계산
            int[] team_link = new int[numberCount];
            for (int i = 0; i < team_start.length; i++) {
                if(team_start[i] == 0) {
                    team_link[i] = i+1;
                }
            }
            //team_start 의 능력치 계산
            int teamStartAbility = calculateAbility(team_start);

            //team_link 의 능력치 계산
            int teamLinkAbility = calculateAbility(team_link);

            int result = Math.abs(teamLinkAbility - teamStartAbility);
            if(result < min) {
                min = result;
            }

            return;
        }

        //players 중에 한명을 뽑음
        for (int i = startNumber; i < numberCount; i++) {
            team_start[i] = i+1;
            dfs(i+1, depth + 1);
            team_start[i] = 0;
        }

    }

    private static int calculateAbility(int[] team) {
        int result = 0;

        for (int i = 0; i < team.length; i++) {
            if(team[i] != 0) {
                for (int j = 0; j < team.length; j++) {
                    if(j != i && team[j] != 0) {
                        result += abilityArr[team[i]-1][team[j]-1];
                    }
                }
            }
        }

        return result;
    }


}
