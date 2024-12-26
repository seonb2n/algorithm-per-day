// https://leetcode.com/problems/permutations=ii

import java.util.*;

class Solution {
    public String[] solution(String[] expressions) {
        // X가 포함된 수식들을 찾아서 순서대로 저장
        List<String> xExpressions = new ArrayList<>();
        for (String expr : expressions) {
            if (expr.contains("X")) {
                xExpressions.add(expr);
            }
        }

        // 각 진법(2-9)에 대해 검증
        Map<String, Set<String>> possibleResults = new HashMap<>();
        for (int base = 2; base <= 9; base++) {
            if (isValidBase(expressions, base)) {
                // 현재 진법이 유효하다면 X가 있는 수식들의 결과를 계산
                for (String expr : xExpressions) {
                    String result = calculateResult(expr, base);
                    possibleResults.computeIfAbsent(expr, k -> new HashSet<>()).add(result);
                }
            }
        }

        // 결과 배열 생성
        String[] answer = new String[xExpressions.size()];
        for (int i = 0; i < xExpressions.size(); i++) {
            String expr = xExpressions.get(i);
            Set<String> results = possibleResults.get(expr);

            if (results.size() == 1) {
                // 결과가 하나인 경우
                answer[i] = expr.substring(0, expr.indexOf("=") + 1) + " " + results.iterator().next();
            } else {
                // 결과가 여러 개인 경우
                answer[i] = expr.substring(0, expr.indexOf("=") + 1) + " ?";
            }
        }

        return answer;
    }

    private boolean isValidBase(String[] expressions, int base) {
        // 주어진 진법으로 모든 수식이 유효한지 검증
        for (String expr : expressions) {
            String[] parts = expr.split(" ");
            String num1 = parts[0];
            String op = parts[1];
            String num2 = parts[2];
            String result = parts[4];

            // 해당 진법에서 사용할 수 없는 숫자가 있는지 확인
            if (!isValidNumber(num1, base) || !isValidNumber(num2, base) ||
                    (!result.equals("X") && !isValidNumber(result, base))) {
                return false;
            }

            // X가 아닌 경우 계산 결과가 일치하는지 확인
            if (!result.equals("X")) {
                int n1 = convertToDecimal(num1, base);
                int n2 = convertToDecimal(num2, base);
                int expectedResult = convertToDecimal(result, base);
                int actualResult = op.equals("+") ? n1 + n2 : n1 - n2;

                if (expectedResult != actualResult) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValidNumber(String num, int base) {
        for (char c : num.toCharArray()) {
            if (Character.isDigit(c) && (c - '0') >= base) {
                return false;
            }
        }
        return true;
    }

    private String calculateResult(String expression, int base) {
        String[] parts = expression.split(" ");
        int num1 = convertToDecimal(parts[0], base);
        int num2 = convertToDecimal(parts[2], base);
        int result = parts[1].equals("+") ? num1 + num2 : num1 - num2;

        return convertFromDecimal(result, base);
    }

    private int convertToDecimal(String number, int base) {
        int result = 0;
        int sign = 1;

        if (number.startsWith("-")) {
            sign = -1;
            number = number.substring(1);
        }

        for (char c : number.toCharArray()) {
            result = result * base + (c - '0');
        }
        return sign * result;
    }

    private String convertFromDecimal(int decimal, int base) {
        if (decimal == 0) return "0";

        StringBuilder result = new StringBuilder();
        boolean isNegative = decimal < 0;
        decimal = Math.abs(decimal);

        while (decimal > 0) {
            result.insert(0, decimal % base);
            decimal /= base;
        }

        if (isNegative) {
            result.insert(0, "-");
        }

        return result.toString();
    }
}
