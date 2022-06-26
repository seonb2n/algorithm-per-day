import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    static BufferedReader br;
    static int N;
    static int[] numbers;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        numbers = new int[10];

        int startNumber = 1;
        int endNumber = N;
        int digit = 1;

        while (startNumber <= endNumber) {
            //끝자리를 9 로 만들 때까지 빼준다.
            while (endNumber % 10 != 9 && startNumber <= endNumber) {
                addNumber(endNumber, digit);
                endNumber--;
            }

            if(endNumber < startNumber) {
                break;
            }

            //끝자리를 0으로 만들떄까지 더해준다.
            while(startNumber % 10 != 0 && startNumber <= endNumber) {
                addNumber(startNumber, digit);
                startNumber++;
            }

            startNumber /= 10;
            endNumber /= 10;

            int addCount = (endNumber - startNumber + 1) * digit;

            for (int i = 0; i < numbers.length; i++) {
                numbers[i] += addCount;
            }

            digit = digit * 10;

        }

        for (int i = 0; i < numbers.length; i++) {
            sb.append(numbers[i]);
            sb.append(" ");
        }

        System.out.println(sb);
    }


    public static void addNumber(int x, int digit) {
        while (x > 0) {
            numbers[x % 10] += digit;
            x /= 10;
        }
    }
}
