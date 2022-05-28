import java.util.Arrays;

class Solution {
    static Ship[] ships;
    static int maxTime;
    static int restFuel;
    static double ratioSum;

    public static void main(String[] args) {
        int[] powers = {20, 30};
        int[] distances = {750, 675};
        solution(8, powers, distances);
    }

    public static int solution(int fuel, int[] powers, int[] distances) {
        ships = new Ship[powers.length];
        maxTime = 0;
        ratioSum = 0;
        restFuel = fuel;

        for (int i = 0; i < powers.length; i++) {
            ships[i] = new Ship(powers[i], distances[i]);
            ratioSum += ships[i].ratio;
        }

        if(fuel == powers.length) {
            for (int i = 0; i < ships.length; i++) {
                double dis = ships[i].distance;
                int pw = ships[i].power;
                maxTime = Math.max(maxTime, (int)(dis - (pw / 2)) / pw + 1);
            }
            return (int)Math.ceil(maxTime);
        }

        Arrays.sort(ships);

        for (int i = 0; i < ships.length-1; i++) {
            int nowFuel = (int) Math.round(fuel * (ships[i].ratio / ratioSum));
            maxTime = Math.max(maxTime, getTime(i, nowFuel));
            restFuel -= nowFuel;
        }

        maxTime = Math.max(maxTime, getTime(ships.length-1, restFuel));

        return maxTime;
    }

    public static int getTime(int shipNumber, int fuel) {
        double dis = ships[shipNumber].distance;
        int pw = ships[shipNumber].power;

        double temp = dis / (pw * fuel);
        temp = temp + temp / 2;

        return (int)Math.ceil(temp);
    }

    public static class Ship implements Comparable<Ship>{
        int power;
        int distance;
        double ratio;

        public Ship(int power, int distance) {
            this.power = power;
            this.distance = distance;
            this.ratio = (double)distance / power;
        }

        @Override
        public int compareTo(Ship o) {
            return (int) (o.ratio - this.ratio);
        }
    }
}