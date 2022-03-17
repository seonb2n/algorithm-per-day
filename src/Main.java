import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static BufferedReader br;
    static StringBuilder sb;
    static StringTokenizer st;
    static int maxResult;
    static int N;
    static int K;
    static int M;
    static int[] numbers;
    static int[] maxNumbers;


    public static void main(String[]args) throws IOException {
        sb = new StringBuilder();
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ");
        String tempN = st.nextToken();
        N = Integer.parseInt(tempN);
        K = Integer.parseInt(st.nextToken());
        M = tempN.split("").length;

        maxResult = 0;

        List<Integer> list = new ArrayList<>();
        list.add(N);

        BFS(0, list);

        if(maxResult == 0) {
            System.out.println(-1);
        } else {
            System.out.println(maxResult);
        }
    }

    //BFS 로 K 단계에 해당하는 만큼 진행
    static void BFS(int nowLevel, List<Integer> numbersList) {
        int size = numbersList.size();
        if(nowLevel == K) {
            for (int i = 0; i < size; i++) {
                maxResult = Math.max(numbersList.get(i), maxResult);
            }
        }

        else {
            List<Integer> tempList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                int nowValue = numbersList.get(i);
                for (int j = 0; j < M-1; j++) {
                    for (int k = j+1; k < M; k++) {
                        int[] newValue = numberToArr(nowValue);
                        newValue = swap(j, k, newValue);
                        if(!tempList.contains(arrToNumber(newValue)) && newValue[0] != 0) {
                            tempList.add(arrToNumber(newValue));
                        }
                    }
                }
            }
            BFS(nowLevel+1, tempList);
        }
    }

    static int[] swap(int a, int b, int[] targetArr) {
        int temp = targetArr[a];
        targetArr[a] = targetArr[b];
        targetArr[b] = temp;
        return targetArr;
    }

    static int[] numberToArr(int number) {
        return Arrays.stream(String.valueOf(number).split("")).mapToInt(Integer::new).toArray();
    }

    static int arrToNumber(int[] numberArr) {
        String str = "";
        for (int i = 0; i < numberArr.length; i++) {
            str += numberArr[i];
        }
        return Integer.parseInt(str);
    }
}
