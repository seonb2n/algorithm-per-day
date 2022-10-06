import java.util.*;

class Solution {

    public static void main(String[] args) {
        long[] numbers = {42};
        solution(numbers);
    }

    public static int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            long number = numbers[i];
            String binary = Long.toBinaryString(number);
            if (binary.length() % 2 == 0) {
                binary = "0" + binary;
            }
            if (isPossible(binary.split(""))) {
                answer[i] = 1;
            }
        }
        return answer;
    }

    public static boolean isPossible(String[] split) {
        if (split.length == 3) {
            if (split[1].equals("0")) {
                return false;
            }
            return true;
        } else {
            int middle = (split.length-1) / 2;
            if (split[middle].equals("0")) {
                return false;
            }
            String[] leftNumbers = Arrays.copyOfRange(split, 0, middle);
            String[] rightNumbers = Arrays.copyOfRange(split, middle+1, split.length);

            //밑에 층이 생길 수 없는 경우이다.
            if (leftNumbers.length == 3 && rightNumbers.length == 3) {
                if (leftNumbers[0].equals("0") && leftNumbers[2].equals("0") && rightNumbers[0].equals("0") && rightNumbers[2].equals("0")) {
                    return false;
                }
            }
            if (rightNumbers.length % 2 == 0) {
                return false;
            }
            return isPossible(rightNumbers) && isPossible(leftNumbers);
        }
    }
}