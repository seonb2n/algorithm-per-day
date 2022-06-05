import java.util.Arrays;
import java.util.HashSet;

class Solution {
    static HashSet<String> answerSet = new HashSet<>();
    static String[] userIds;
    static String[] bannedIds;

    public static void main(String[] args) {
        String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] banned_id = {"*rodo", "*rodo", "******"};
        solution(user_id, banned_id);
    }

    public static int solution(String[] user_id, String[] banned_id) {
        boolean[] isVisited = new boolean[user_id.length];

        userIds = new String[user_id.length];
        System.arraycopy(user_id, 0, userIds, 0, user_id.length);
        bannedIds = new String[banned_id.length];
        System.arraycopy(banned_id, 0, bannedIds, 0, banned_id.length);

        //dfs 로 banned_id 와 user_id 를 비교하고
        //비교한 결과를 hashSet 으로 추가한다.
        StringBuilder sb = new StringBuilder();
        DFS(isVisited, sb, 0, banned_id.length);


        return answerSet.size();
    }

    public static boolean isSame(String bannedId, String userId) {
        if (bannedId.length() != userId.length()) {
            return false;
        }
        for (int i = 0; i < bannedId.length(); i++) {
            if (bannedId.charAt(i) != '*' && (bannedId.charAt(i) != userId.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static void DFS(boolean[] nowVisit, StringBuilder sb, int nowIndex, int lastIndex) {
        if (nowIndex == lastIndex) {
            //중복된 값이 없도록 sb 에 있던 값들을 정렬해줘야 한다.
            int[] ans = new int[sb.length()];
            for (int i = 0; i < sb.length(); i++) {
                ans[i] = Character.getNumericValue(sb.charAt(i));
            }
            Arrays.sort(ans);
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < ans.length; i++) {
                result.append(ans[i]);
            }
            answerSet.add(result.toString());
        } else {
            String nowBannedId = bannedIds[nowIndex];
            for (int i = 0; i < userIds.length; i++) {
                if (!nowVisit[i] && isSame(nowBannedId, userIds[i])) {
                    nowVisit[i] = true;
                    sb.append(i);
                    DFS(nowVisit, sb, nowIndex + 1, lastIndex);
                    nowVisit[i] = false;
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
        }
    }

}