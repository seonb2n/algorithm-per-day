import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCaseNumber = Integer.parseInt(br.readLine());

        makePattern(testCaseNumber);

        System.out.print(sb);

    }

    public static void makePattern(int totalNumber) {
        for (int i = 1; i <= totalNumber; i++) {
            drawLine(totalNumber, i);
            sb.append("\n");
        }
    }

    //주어진 n 의 값에 해당하는 줄 그리기
    public static void drawLine(int totalNUmber, int currentNumber) {

        if(totalNUmber == 1) {
            sb.append("*");
        } else {
            totalNUmber = totalNUmber / 3;
            //만약 지금 줄이 공백이 포함된 줄이면
            if(currentNumber >= totalNUmber + 1 && currentNumber <= totalNUmber * 2) {
                //3번 호출하는 것이 아니라, 1번 띄고 1번 호출
                currentNumber = currentNumber % totalNUmber;
                drawLine(totalNUmber, currentNumber);
                drawBlank(totalNUmber);
                drawLine(totalNUmber, currentNumber);
            } else {
                //공백이 포함되지 않은 줄이면, 하위 패턴의 줄 그리기
                currentNumber = currentNumber % totalNUmber;
                drawLine(totalNUmber, currentNumber);
                drawLine(totalNUmber, currentNumber);
                drawLine(totalNUmber, currentNumber);
            }
        }
    }

    public static void drawBlank(int totalNumber) {
        for (int i = 0; i < totalNumber; i++) {
            sb.append(" ");
        }
    }

}
