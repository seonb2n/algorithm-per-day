import java.util.*;

class Solution {

    static int basicFee;
    static int basicTime;
    static int addTime;
    static int addFee;
    static int[] answer;

    public static int[] solution(int[] fees, String[] records) {
        basicTime = fees[0];
        basicFee = fees[1];
        addTime = fees[2];
        addFee = fees[3];

        List<Integer> carNumbers = new ArrayList<>();
        HashMap<Integer, Car> carNumberId = new HashMap<>();

        for (int i = 0; i < records.length; i++) {
            String[] str = records[i].split(" ");

            //처음 들어온 차
            if(!carNumbers.contains(Integer.parseInt(str[1]))) {
                carNumberId.put(Integer.parseInt(str[1]), new Car(str[1], str[0]));
                carNumbers.add(Integer.parseInt(str[1]));
            }
            //이미 존재하는 차
            else {
                //나가는 경우
                if(str[2].equals("OUT")) {
                    Car car = carNumberId.get(Integer.parseInt(str[1]));
                    car.carOutTime = str[0];
                    car.carTotalTime += timeToMinutes(car.carOutTime) - timeToMinutes(car.carInTime);
                    car.isOut = true;
                }
                //들어오는 경우
                else {
                    Car car = carNumberId.get(Integer.parseInt(str[1]));
                    car.carInTime = str[0];
                    car.isOut = false;
                }
            }
        }

        carNumbers.sort(Comparator.naturalOrder());
        answer = new int[carNumbers.size()];

        //아직 안나간 차에 대해서 totalTime 을 갱신한다.
        for (int i = 0; i < carNumbers.size(); i++) {
            Car car = carNumberId.get(carNumbers.get(i));
            if (!car.isOut) {
                car.carTotalTime += (timeToMinutes("23:59") - timeToMinutes(car.carInTime));
            }
            //totalTime 에 해당하는 요금을 부과한다.
            answer[i] = calculateFee(car);
        }

        return answer;
    }

    static int calculateFee(Car car) {
        int time = car.carTotalTime;
        if(time <= basicTime) {
            return basicFee;
        } else {
            if((time - basicTime) % addTime == 0) {
               return basicFee + (time - basicTime) / addTime * addFee;
            }
            else {
                return basicFee + (time - basicTime) / addTime * addFee + addFee;
            }
        }
    }

    static int timeToMinutes(String time) {
        String[] str = time.split(":");
        return Integer.parseInt(str[0]) * 60 + Integer.parseInt(str[1]);
    }

    static class Car implements Comparable<Car>{
        int fee;
        String carNumber;
        String carInTime;
        String carOutTime;
        int carTotalTime;
        boolean isOut;

        @Override
        public int compareTo(Car o) {
            int number1 = Integer.parseInt(carNumber);
            int number2 = Integer.parseInt(o.carNumber);
            if(number1 > number2) {
                return 1;
            }
            else {
                return -1;
            }
        }

        public Car(String carNumber, String carInTime) {
            fee = 0;
            this.carNumber = carNumber;
            this.carInTime = carInTime;
            this.carOutTime = "23:59";
        }
    }
}