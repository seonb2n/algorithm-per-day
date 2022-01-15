import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Main {

    static StringBuilder sb;
    static List<wLocalVariable> resultList;
    static int a;
    static int b;
    static int c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        resultList = new ArrayList<>();

        while (!(a==-1 & b==-1 & c==-1)) {
            int[] numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            a = numbers[0];
            b = numbers[1];
            c = numbers[2];
            if(a== -1 & b== -1 & c==-1) {
                break;
            }
            wLocalVariable wLocalVariable = new wLocalVariable(a, b, c);
            sb.append(wLocalVariable);
            sb.append(getW(wLocalVariable));
            sb.append("\n");
        }


        System.out.println(sb);
    }

    private static int getW(wLocalVariable wLocalVariable) {
        if(wLocalVariable.a <= 0 || wLocalVariable.b <= 0 || wLocalVariable.c <= 0) {
            return 1;
        }

        Optional<wLocalVariable> w = resultList.stream().filter(o ->
            o.a == wLocalVariable.a && o.b == wLocalVariable.b && o.c == wLocalVariable.c)
                .findFirst();

        if(w.isPresent()) {
            return w.get().result;
        } else {
           return callMethodW(wLocalVariable);
        }
    }

    private static int callMethodW(wLocalVariable wLocalVariable) {
        int a = wLocalVariable.a;
        int b = wLocalVariable.b;
        int c = wLocalVariable.c;

        if(a > 20 || b > 20 || c > 20) {
            wLocalVariable localVariable = new wLocalVariable(20, 20, 20);
            localVariable.result = getW(localVariable);
            resultList.add(localVariable);
            return localVariable.result;

        } else if(a < b && b < c) {
            wLocalVariable localVariable = new wLocalVariable(a, b, c);
            localVariable.result = getW(new wLocalVariable(a, b, c-1)) + getW(new wLocalVariable(a, b-1, c-1)) - getW(new wLocalVariable(a, b-1, c));
            resultList.add(localVariable);
            return localVariable.result;
        } else {
            wLocalVariable localVariable = new wLocalVariable(a, b, c);
            localVariable.result = getW(new wLocalVariable(a-1, b, c)) + getW(new wLocalVariable(a-1, b-1, c)) + getW(new wLocalVariable(a-1, b, c-1)) - getW(new wLocalVariable(a-1, b-1, c-1));
            resultList.add(localVariable);
            return localVariable.result;
        }

    }

    static class wLocalVariable {
        int a;
        int b;
        int c;
        public wLocalVariable(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        int result;

        @Override
        public String toString() {
            return "w(" + a + ", "+ b +", "+ c + ") = ";
        }
    }
}
