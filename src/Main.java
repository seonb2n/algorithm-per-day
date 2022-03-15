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
    static int[] dp;

    public static void main(String[]args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ");
        appNumber = Integer.parseInt(st.nextToken());
        needMemory = Integer.parseInt(st.nextToken());

        apps = new App[appNumber];

        //특정 메모리를 채울 수 있는 가장 효율적인 cost
        dp = new int[needMemory];

        st = new StringTokenizer(br.readLine(), " ");
        st2 = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < appNumber; i++) {
            apps[i] = new App(Integer.parseInt(st2.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(apps);

        minCost = Integer.MAX_VALUE;
        boolean[] searchAppArr = new boolean[appNumber];
        for (int i = 0; i < appNumber; i++) {
            boolean[] temp = searchAppArr.clone();
            temp[i] = true;
            findMinCost(temp, apps[i].appCost, apps[i].appSize);
        }

        System.out.println(minCost);
    }

    private static void findMinCost(boolean[] searchApp, int nowCost, int nowMemory) {
        if(nowMemory >= needMemory) {
            minCost = Math.min(minCost, nowCost);
        } else {
            for (int i = 0; i < appNumber; i++) {
                if(!searchApp[i]) {
                    boolean[] temp = searchApp.clone();
                    temp[i] = true;
                    findMinCost(temp, nowCost+apps[i].appCost, nowMemory+apps[i].appSize);
                }
            }
        }
    }

    public static class App implements Comparable<App>{
        int appCost;
        int appSize;
        int eff;

        public App(int appCost, int appSize) {
            this.appCost = appCost;
            this.appSize = appSize;
            if(appCost == 0) {
                eff = Integer.MAX_VALUE;
            } else {
                eff = appSize / appCost;
            }
        }

        @Override
        public int compareTo(App o) {
            return this.eff >= o.eff ? -1 : 1;
        }
    }
}