import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static long number;
    static long[][] arr;
    static long[][] result;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        number = Long.parseLong(br.readLine());

        arr = new long[2][2];
        arr[0][0] = 1;
        arr[0][1] = 1;
        arr[1][0] = 1;

        result = new long[2][2];

        System.out.println(fibonacci(number));
    }

    private static long fibonacci(long number) {
        //number 의 크기만큼 arr 을 제곱한다.
        if(number == 0) {
            return 0;
        } else {
            result = getResult(arr, number);
            return result[1][0];
        }
    }

    private static long[][] getResult(long[][] mArr, long index) {
        if(index == 1) {
            return arr;
        }
        //지수의 절반만큼 제곱해준다.
        long[][] temp = getResult(mArr, index / 2);
        if(index % 2 == 0) {
            return divide(multiply(temp, temp));
        } else {
            return divide(multiply(divide(multiply(temp, temp)),  mArr));
        }
    }

    private static long[][] divide(long[][] arr) {
        long[][] temp = new long[2][2];

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                temp[i][j] = arr[i][j] % 1000000007;
            }
        }
        return temp;
    }

    private static long[][] multiply(long[][] arr1, long[][] arr2) {
        long[][] temp = new long[2][2];

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    temp[i][j] += arr1[i][k] * arr2[k][j];
                }
            }
        }
        return temp;
    }

}
