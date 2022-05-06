import java.util.*;

class Solution {
    static List<Long> numberList;
    static List<Long> tempNumberList;
    static List<Character> expList;
    static List<Character> tempExpList;

    public static void main(String[] args) {
        String s = "200-300-500-600*40+500+500";
        solution(s);
    }

    public static long solution(String expression) {
        String[] cases = {"+-*", "+*-", "*-+", "*+-", "-*+", "-+*"};
        numberList = new LinkedList<>();
        expList = new LinkedList<>();

        String[] split = expression.split("[*+-]");

        for (int i = 0; i < split.length; i++) {
            numberList.add(Long.parseLong(split[i]));
        }

        split = expression.split("[0-9]");

        for (int i = 0; i < split.length; i++) {
            if(!split[i].equals(""))
                expList.add(split[i].charAt(0));
        }

        long answer = 0;

        for (int i = 0; i < 6; i++) {
            tempNumberList = new LinkedList<>();
            tempNumberList.addAll(numberList);
            tempExpList = new LinkedList<>();
            tempExpList.addAll(expList);
            String nowCase = cases[i];
            calculateWith(nowCase.charAt(0));
            calculateWith(nowCase.charAt(1));
            calculateWith(nowCase.charAt(2));

            answer = Math.max(answer, Math.abs(tempNumberList.get(0)));
        }

        return answer;
    }

    public static void calculateWith(char exp) {

        int i = 0;
        while (tempExpList.contains(exp)) {
            if(tempExpList.get(i) == exp) {
                Long firstNumber = tempNumberList.get(i);
                Long secondNumber = tempNumberList.get(i+1);
                Long result = 0L;
                switch (exp) {
                    case '+':
                        result = firstNumber + secondNumber;
                        break;
                    case '-':
                        result = firstNumber - secondNumber;
                        break;
                    case '*':
                        result = firstNumber * secondNumber;
                        break;
                }
                tempNumberList.add(i, result);
                tempNumberList.remove(i+1);
                tempNumberList.remove(i+1);
                tempExpList.remove(i);
            }
            else {
                i++;
            }
        }
    }

}