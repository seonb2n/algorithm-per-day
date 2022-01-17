import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static StringBuilder sb;
    static long[][] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int caseNumber = Integer.parseInt(br.readLine());
        numbers = new long[caseNumber + 1][10];

        for (int i = 1; i <= caseNumber; i++) {
            for (int j = 0; j <= 9; j++) {
                numbers[i][j] = (getNumber(i, j)) % 1000000000;
            }
        }

        long result = 0;
        for (int i = 0; i <= 9; i++) {
            result += numbers[caseNumber][i];
        }
        sb.append(result % 1000000000);
        System.out.println(sb);
    }

    private static long getNumber(int i, int j) {
        //i자리수 이면서 j로 끝나는 숫자의 개수
        if (i == 1 && j == 0) {
            return 0;
        } else if (i == 1) {
            return 1;
        }
        int k = i - 1;
        switch (j) {
            case 0:
                return numbers[k][1];
            case 1:
                return numbers[k][0] + numbers[k][2];
            case 2:
                return numbers[k][1] + numbers[k][3];
            case 3:
                return numbers[k][2] + numbers[k][4];
            case 4:
                return numbers[k][3] + numbers[k][5];
            case 5:
                return numbers[k][4] + numbers[k][6];
            case 6:
                return numbers[k][5] + numbers[k][7];
            case 7:
                return numbers[k][6] + numbers[k][8];
            case 8:
                return numbers[k][7] + numbers[k][9];
            case 9:
                return numbers[k][8];
        }
        return 0;
    }
}
