import java.util.*;

class Solution {

    static boolean[] isUsedTicket;
    static List<String> answerList;

    public static void main(String[] args) {
        String[][] src = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};
        solution(src);
    }

    public static String[] solution(String[][] tickets) {

        isUsedTicket = new boolean[tickets.length];

        answerList = new ArrayList<String>();

        for (int i = 0; i < tickets.length; i++) {
            if (tickets[i][0].equals("ICN")) {
                isUsedTicket[i] = true;
                String route = "ICN";
                dfs(tickets[i][1], route + " " + tickets[i][1], 1, tickets);
                isUsedTicket[i] = false;
            }
        }

        Collections.sort(answerList);

        return answerList.get(0).split(" ");
    }

    static void dfs(String nowAirport, String nowRoute, int nowCnt, String[][] tickets) {
        if (nowCnt == tickets.length) {
            answerList.add(nowRoute);
        }
        for (int i = 0; i < tickets.length; i++) {
            if (tickets[i][0].equals(nowAirport) && !isUsedTicket[i]) {
                isUsedTicket[i] = true;
                dfs(tickets[i][1], nowRoute + " " + tickets[i][1], nowCnt+1, tickets);
                isUsedTicket[i] = false;
            }
        }

    }
}