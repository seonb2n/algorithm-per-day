import java.util.*;

class Solution {

    static int[] appeachArrow;
    static int[] ryanArrow;
    static int[] resultArrow;
    static int max = 0;

    public static void main(String[] args) {
        int[] a = {0,0,1,2,0,1,1,1,1,1,1};
        solution(9, a);
    }

    public static int[] solution(int n, int[] info) {
        appeachArrow = info;
        ryanArrow = new int[11];
        resultArrow = new int[11];

        DFS(10, n);

        if (max <= 0) {
            int[] a = {-1};
            return a;
        }

        return resultArrow;
    }

    public static void DFS(int nowScore, int nowArrow) {
        if(nowScore == 0) {
            ryanArrow[10] = nowArrow;
            int temp = numDiff(ryanArrow);
            if(temp > max) {
                max = temp;
                resultArrow = ryanArrow.clone();
                return;
            }
            else if(temp == max) {
                for (int i = 10; i >= 0; i--) {
                    if(resultArrow[i] < ryanArrow[i]) {
                        resultArrow = ryanArrow.clone();
                        return;
                    }
                    else if(resultArrow[i] > ryanArrow[i]) {
                        return;
                    }
                }
            }
            else {
                return;
            }
        }

        if(appeachArrow[10-nowScore] < nowArrow) {
            //지금 숫자를 포함하거나 안하거나 두 가지 경우를 선택할 수 있다.
            ryanArrow[10-nowScore] = appeachArrow[10-nowScore] + 1;
            int tempArrow = nowArrow - ryanArrow[10-nowScore];
            DFS(nowScore-1, tempArrow);
        }

        ryanArrow[10-nowScore] = 0;
        DFS(nowScore-1, nowArrow);

    }


    public static int numDiff(int[] ryanArrow) {
        int ryanResult = 0;
        int appeachResult = 0;
        for (int i = 0; i < 10; i++) {
            if(ryanArrow[i] > appeachArrow[i]) {
                ryanResult += (10-i);
            }
            else if(ryanArrow[i] < appeachArrow[i]) {
                appeachResult += (10-i);
            }
        }

        return ryanResult - appeachResult;
    }
}