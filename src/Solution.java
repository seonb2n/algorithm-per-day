import java.util.*;

class Solution {

    public static void main(String[] args) {
        String test = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        System.out.println(solution(test));
    }

    public static int solution(String s) {
        String origin = s;
        int stringSize = s.length();
        int answer = stringSize;

        for (int i = 1; i <= stringSize; i++) {
            //i 크기만큼 압축을 진행한다.
            String nowPattern = origin.substring(0, i);
            int nowPatternNumber = 1;
            int nowAnswer = stringSize;
            //문자열을 처음부터 끝까지 훓는다.
            int nowIndex = i;
            while (nowIndex < stringSize) {
                String nextPattern = origin.substring(nowIndex, Math.min(nowIndex + i, stringSize));
                //다음 글자가 지금 패턴과 일치한다면
                if(nowPattern.equals(nextPattern)) {
                    nowPatternNumber++;
                }
                else {
                    //일치하지 않는 경우에는 지금 패턴을 업데이트해준다.
                    nowAnswer = nowAnswer - Math.max(0, (i * nowPatternNumber - (i + String.valueOf(nowPatternNumber).length())));
                    nowPattern = nextPattern;
                    nowPatternNumber = 1;
                }
                nowIndex = nowIndex + i;
            }
            //마지막 패턴에 대한 처리도 해줘야 함.
            nowAnswer = nowAnswer - Math.max(0, (i * nowPatternNumber - (i + String.valueOf(nowPatternNumber).length())));
            answer = Math.min(answer, nowAnswer);

        }

        return answer;
    }

}