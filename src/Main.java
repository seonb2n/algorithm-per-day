import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCaseNumber = Integer.parseInt(br.readLine());
        List<String> plates = new ArrayList<>();

        for (int i = 0; i < testCaseNumber; i++) {
            plates.add(String.valueOf(i + 1));
        }

        movePlates(testCaseNumber, "1", "3");

        System.out.println(count);
        System.out.print(sb);
    }

    public static void movePlates(int platesNumber, String fromTop, String toTop) {
        if (platesNumber == 1) {
            sb.append(fromTop + " " + toTop);
            sb.append("\n");
            count++;
        } else {

            String middleTop = getMiddleTop(fromTop, toTop);
            //n - 1번째 까지의 원판을 fromTop -> middleTop 으로 옮긴다.
            movePlates((platesNumber - 1), fromTop, middleTop);

            //n 번째 원판을 fromTop > toTop 으로 옮긴다.
            sb.append(fromTop + " " + toTop);
            sb.append("\n");
            count++;

            //n - 1번째 까지의 원판을 middleTop -> toTop으로 옮긴다.
            movePlates((platesNumber - 1), middleTop, toTop);

        }
    }

    public static String getMiddleTop(String toTop, String fromTop) {
        return String.valueOf(6 - Integer.parseInt(toTop) - Integer.parseInt(fromTop));
    }
}
