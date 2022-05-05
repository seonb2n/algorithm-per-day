class Solution {


    public static int solution(String s) {
        
        String result = s;
        
        if(s.contains("one")) {
            result = s.replaceAll("one", "1");
        }
        
        if(s.contains("two")) {
            result = result.replaceAll("two", "2");
        }

        if(s.contains("three")) {
            result = result.replaceAll("three", "3");
        }

        if(s.contains("four")) {
            result = result.replaceAll("four", "4");
        }

        if(s.contains("five")) {
            result = result.replaceAll("five", "5");
        }

        if(s.contains("six")) {
            result = result.replaceAll("six", "6");
        }

        if(s.contains("seven")) {
            result = result.replaceAll("seven", "7");
        }

        if(s.contains("eight")) {
            result = result.replaceAll("eight", "8");
        }

        if(s.contains("nine")) {
            result = result.replaceAll("nine", "9");
        }

        if(s.contains("zero")) {
            result = result.replaceAll("zero", "0");
        }

        return Integer.parseInt(result);
    }
}