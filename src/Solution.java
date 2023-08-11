import java.util.*;

class Solution {

    public static void main(String[] args) {
        int[] times = {1,2,3};
        solution(times, 4);
    }

    public static int solution(int[] food_times, long k) {
        List<Node> foodNode = new LinkedList<>();

        for (int i = 0; i < food_times.length; i++) {
            foodNode.add(new Node(i + 1, food_times[i]));
        }
        // 음식 오름차순 정렬
        foodNode.sort((o1, o2) -> o1.food - o2.food);

        // 저번 순환 횟수
        long nowRotate = 0;
        // 총 음식 개수
        int length = foodNode.size();
        // 현재까지 소요된 시간
        long totalSpentTime = 0;
        // 현재 먹은 음식
        int index = 0;
        for (Node n : foodNode) {
            //n 번째 음식을 다 먹는데 걸리는 시간
            //이번 음식을 다 먹는데 걸리는 시간은 저번 차례의 음식을 다 먹는데 걸리는 시간을 빼줘야 한다.
            long timeToEat = (n.food - nowRotate) * length;
            nowRotate = n.food;
            totalSpentTime += timeToEat;
            // 지금까지 소요된 시간이 k 보다 크다면
            if (k - totalSpentTime >= 0) {
                index++;
                length--;
            }
            // 현재 시간 중에 k 가 끝남
            else {
                long restTime = k - (totalSpentTime - timeToEat);
                restTime = restTime % length;
                //남은 놈들을 다시 index 번호로 원복해줘야 함
                //index 이전 음식은 모두 넘었으니 빼줘야 함
                foodNode = foodNode.subList(index, foodNode.size());
                foodNode.sort((o1, o2) -> o1.index - o2.index);
                return foodNode.get((int) restTime).index;
            }
        }

        return -1;
    }

    static class Node {
        int index;
        int food;

        public Node(int index, int food) {
            this.index = index;
            this.food = food;
        }
    }
}
