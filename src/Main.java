import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCaseNumber = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine(), " ");
        int[] values = new int[testCaseNumber];

        //값 부여
        for (int i = 0; i < testCaseNumber; i++) {
            int number = Integer.parseInt(stringTokenizer.nextToken());
            values[i] = number;
        }

        
        int[] sortedValues = values.clone();
        Arrays.sort(sortedValues);

        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int rank = 0;

        hashMap.put(sortedValues[0], rank);
        for (int i = 1; i < sortedValues.length; i++) {
            if(sortedValues[i] != sortedValues[i-1]) {
                rank++;
            }
            hashMap.put(sortedValues[i], rank);
        }

        for (int i = 0; i < values.length; i++) {
            sb.append(hashMap.get(values[i]) + " ");
        }

        System.out.println(sb);
    }

}
