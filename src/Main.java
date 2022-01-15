import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static StringBuilder sb;
    static int[] tiles;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        tiles = new int[1000001];

        tiles[1] = 1;
        tiles[2] = 2;
        tiles[3] = 3;
        tiles[4] = 5;

        int number = Integer.parseInt(br.readLine());

        sb.append(getNumber(number));

        System.out.println(sb);
    }

    private static int getNumber(int tileNumber) {
        if(tiles[tileNumber] != 0) {
            return tiles[tileNumber];
        } else {
            tiles[tileNumber] = ((getNumber(tileNumber-2) + getNumber(tileNumber - 1)) % 15746);
            return tiles[tileNumber];
        }
    }
}
