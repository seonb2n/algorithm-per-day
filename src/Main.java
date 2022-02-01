import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static int arrSize;
    static long index;
    static int[][] arr;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        st = new StringTokenizer(br.readLine(), " ");

        arrSize = Integer.parseInt(st.nextToken());
        index = Long.parseLong(st.nextToken());
        arr = new int[arrSize][arrSize];

        for (int i = 0; i < arrSize; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int[][] result = getResult(arr, index);

        for (int i = 0; i < arrSize; i++) {
            for (int j = 0; j < arrSize; j++) {
                sb.append(result[i][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);

    }

    //인자로 받은 arr 에 대해서 index 만큼 제곱하는 함수
    private static int[][] getResult(int[][] arr, long index) {
        if(index == 1) {
            return divide(arr);
        }

        //지수의 절반만큼 제곱해준다.
        int[][] temp = getResult(arr, index / 2);

        if(index % 2 == 0) {
            return divide(multiply(temp, temp));
        } else {
            return divide(multiply(divide(multiply(temp, temp)), arr));
        }
    }

    private static int[][] divide(int[][] arr) {
        int[][] temp = new int[arrSize][arrSize];

        for (int i = 0; i < arrSize; i++) {
            for (int j = 0; j < arrSize; j++) {
                temp[i][j] = arr[i][j] % 1000;
            }
        }
        return temp;
    }

    private static int[][] multiply(int[][] arr1, int[][] arr2) {
        int[][] temp = new int[arrSize][arrSize];

        for (int i = 0; i < arrSize; i++) {
            for (int j = 0; j < arrSize; j++) {
                for (int k = 0; k < arrSize; k++) {
                    temp[i][j] += arr1[i][k] * arr2[k][j];
                }
            }
        }

        return temp;
    }

}
