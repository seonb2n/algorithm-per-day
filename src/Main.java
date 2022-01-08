import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testNumber = Integer.parseInt(br.readLine());

        int five = 0;
        int three = 0;

        five = testNumber / 5;

        while(five >= 0) {
            if((testNumber - three * 3) % 5 == 0) {
                break;
            }

            three++;

        }

        if(testNumber - three * 3 >= 0) {
            five = (testNumber - three * 3) / 5;
        }

        if(testNumber == five * 5 + three * 3) {
            System.out.println(five + three);
        } else{
            System.out.println(-1);
        }

    }

}
