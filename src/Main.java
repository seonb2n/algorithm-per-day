import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        User[] users = new User[id_list.length];
        Map<String, Integer> userNameIdSet = new HashMap<>();

        for (int i = 0; i < id_list.length; i++) {
            User new_User = new User(i, id_list[i], id_list.length);
            users[i] = new_User;
            userNameIdSet.put(id_list[i], i);
        }

        for (int i = 0; i < report.length; i++) {
            String[] s = report[i].split(" ");
            //이미 해당 유저가 신고한 경우라면 reportedNumber 는 올라가지 않는다.
            if(!users[userNameIdSet.get(s[0])].reportUserId[userNameIdSet.get(s[1])]) {
                users[userNameIdSet.get(s[0])].reportUserId[userNameIdSet.get(s[1])] = true;
                users[userNameIdSet.get(s[1])].reportedNumber++;
            }
        }

        int[] answer = new int[id_list.length];


        for (int i = 0; i < id_list.length; i++) {
            if(users[i].reportedNumber >= k) {
                //해당 유저를 정지시킨 이들을 찾아야 한다.
                for (int j = 0; j < id_list.length; j++) {
                    if(users[j].reportUserId[i]) {
                        answer[j]++;
                    }
                }
            }
        }


        return answer;
    }

    public static class User {
        int id;
        String userName;
        int reportedNumber;
        boolean[] reportUserId;

        public User(int id, String userName, int userNumber) {
            this.id = id;
            this.userName = userName;
            reportUserId = new boolean[userNumber];
        }
    }
}