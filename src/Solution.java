import java.util.*;

class Solution {

    public static void main(String[] args) {
        int[][] routes = {{-20, -15}, {-14, -5}, {-18, -13}, {-5, -3}};
        solution(routes);
    }

    public static int solution(int[][] routes) {
        int answer = 0;

        Set<Integer> passedCar = new HashSet<>();
        while (true) {
            HashMap<Integer, List<Integer>> roadMap = new HashMap<>();
            for (int i = 0; i < routes.length; i++) {
                if (!passedCar.contains(i)) {
                    int startPoint = routes[i][0];
                    int endPoint = routes[i][1];

                    for (int j = startPoint; j <= endPoint; j++) {
                        var carNumberList = roadMap.getOrDefault(j, new ArrayList<>());
                        carNumberList.add(i);
                        roadMap.put(j, carNumberList);
                    }
                }
            }

            int maxPassed = 0;
            int maxPassedIndex = 0;

            for (int i : roadMap.keySet()) {
                if (maxPassed < roadMap.get(i).size()) {
                    maxPassed = roadMap.get(i).size();
                    maxPassedIndex = i;
                }
            }
            if (maxPassed == 0) break;

            answer++;
            passedCar.addAll(roadMap.get(maxPassedIndex));
        }

        return answer;
    }

}