import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static BufferedReader br;
    static StringBuilder sb;
    static StringTokenizer st;
    static StringTokenizer st2;
    static int appNumber;
    static int needMemory;
    static App[] apps;
    static int minCost;
    static int[][] dp;

    public static void main(String[]args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ");
        appNumber = Integer.parseInt(st.nextToken());
        needMemory = Integer.parseInt(st.nextToken());

        apps = new App[appNumber];

        int maxCost = 0;
        st = new StringTokenizer(br.readLine(), " ");
        st2 = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < appNumber; i++) {
            apps[i] = new App(Integer.parseInt(st2.nextToken()), Integer.parseInt(st.nextToken()));
            maxCost+= apps[i].appCost;
        }

        // i cost 일 경우, j 번째 app 까지 탐색할 때, 가질 수 있는 최대 메모리
        dp = new int[maxCost+1][appNumber];

        for (int i = 0; i < maxCost+1; i++) {
            for (int j = 0; j < appNumber; j++) {
                dp[i][j] = findMaxMemory(i, j);
                if(dp[i][j] >= needMemory) {
                    System.out.println(i);
                    return;
                }
            }
        }
    }

    //maxCost 일 때, searchApp 까지 탐색한 경우 가질 수 있는 최대 메모리를 반환한다.
    private static int findMaxMemory(int maxCost, int searchAppNode) {
        App searchApp = apps[searchAppNode];
        if(searchApp.appCost > maxCost) {
            if(maxCost == 0 && searchAppNode == 0) {
                return 0;
            }
            else if(searchAppNode == 0) {
                return dp[maxCost-1][searchAppNode];
            }
            else {
                return dp[maxCost][searchAppNode-1];
            }
        }
        //해당 cost 에서 지금 노드를 포함할 수 있다면
        else {
           if(searchAppNode == 0) {
               return searchApp.appSize;
           }
           else {
               return Math.max(dp[maxCost][searchAppNode-1], dp[maxCost-searchApp.appCost][searchAppNode-1] + searchApp.appSize);
           }
        }
    }

    public static class App {
        int appCost;
        int appSize;

        public App(int appCost, int appSize) {
            this.appCost = appCost;
            this.appSize = appSize;
        }
    }
}