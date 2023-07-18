import java.util.*;

class Solution {

    public static int solution(int[][] routes) {
        int answer = 0;
        Car[] carArr = new Car[routes.length];
        for (int i = 0; i < routes.length; i++) {
            carArr[i] = new Car(routes[i][0], routes[i][1]);
        }

        Arrays.sort(carArr);

        int cam = carArr[0].endPoint;
        answer++;
        for (Car car : carArr) {
            if (car.startPoint > cam) {
                answer++;
                cam = car.endPoint;
            }
        }

        return answer;
    }

    static class Car implements Comparable<Car> {
        int startPoint;
        int endPoint;

        public Car(int startPoint, int endPoint) {
            this.startPoint = startPoint;
            this.endPoint = endPoint;
        }

        @Override
        public int compareTo(Car o) {
            return this.endPoint - o.endPoint;
        }
    }

}