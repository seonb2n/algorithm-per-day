import java.io.*;
import java.util.*;

class Main {

    static BufferedReader br;
    static BufferedWriter bw;

    /**
     https://www.acmicpc.net/problem/28086
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String problem = br.readLine();
        String[] split = new String[2];
        String op = null;
        if (problem.contains("+")) {
            split = problem.split("\\+");
            op = "+";
        }
        if (problem.contains("-")) {
            split = problem.split("-");
            op = "-";
        }
        if (problem.contains("*")) {
            split = problem.split("\\*");
            op = "\\*";
        }
        if (problem.contains("/")) {
            split = problem.split("/");
            op = "/";
        }

        for (int i = 0; i < split.length; i++) {
            System.out.println(split[i]);
        }

        bw.close();
    }

}
