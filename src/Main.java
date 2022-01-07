import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testNumber = Integer.parseInt(br.readLine());

        int[] floor = new int[testNumber];
        int[] room = new int[testNumber];

        for (int i = 0; i < testNumber; i++) {
            floor[i] = Integer.parseInt(br.readLine());
            room[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < testNumber; i++) {
            System.out.println(findPeopleNumber(floor[i], room[i]));
        }
    }

    public static int findPeopleNumber(int floor, int room) {
        if(floor == 0) {
            return room;
        }

        if(room == 1) {
            return 1;
        }

        return findPeopleNumber(floor, room - 1) + findPeopleNumber(floor - 1, room);

    }
}
