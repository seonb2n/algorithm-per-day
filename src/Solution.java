import java.time.LocalTime;
import java.util.*;

class Solution {

    static Map<Character, List<String>> rule;
    static Set<Integer> set;

    public static void main(String[] args) {
        String s = "DAS";
        System.out.println(s.substring(0, 4));
        System.out.println(s.indexOf("DA", 10));
        rule = new HashMap<>();
        rule.put('0', new ArrayList<>());
        rule.get('0').add("O");

        System.out.println("ads");

        List<String> rule = new ArrayList<>();
    }
    public static String solution(int n, int t, int m, String[] timetable) {
        //셔틀은 n 번 t 분 간격으로 옴
        //마지막 셔틀에는 무조건 타야 한다.
        LocalTime answer = LocalTime.now();
        int shuttle = 0;

        List<Person> personList = new ArrayList<>();

        for (int i = 0; i < timetable.length; i++) {
            String time = timetable[i];
            personList.add(new Person(time));
        }

        Collections.sort(personList);

        //시간마다 셔틀을 보낸다.
        LocalTime nowTime = LocalTime.parse("09:00");
        while (shuttle < n) {
            shuttle++;
            //지금 셔틀이 마지막일 때 셔틀에 탈 수 있는 사람들을 확인한다.
            if(shuttle == n) {
                int lastShuttlePerson = 0;
                int lastPerson = 0;
                for (int i = 0; i < personList.size(); i++) {
                    Person p = personList.get(i);
                    if(!p.isGone && lastShuttlePerson < m && (p.time.isBefore(nowTime)) || p.time.equals(nowTime)) {
                        p.isGone = true;
                        lastShuttlePerson++;
                        lastPerson = i;
                    }
                    if(lastShuttlePerson == m) {
                        break;
                    }
                }
                //마지막 셔틀에 모든 사람이 다 탔다면 콘은 셔틀을 탈 수 있는 시간에 나와야 한다.
                if(lastShuttlePerson == m) {
                    LocalTime lastTime = personList.get(lastPerson).time;
                    answer = lastTime.minusMinutes(1);
                }
                else {
                    answer = nowTime;
                }
            }
            else {
                int nowShuttlePerson = 0;
                for (int i = 0; i < personList.size(); i++) {
                    Person p = personList.get(i);
                    if(!p.isGone && nowShuttlePerson < m && (p.time.isBefore(nowTime)) || p.time.equals(nowTime)) {
                        p.isGone = true;
                        nowShuttlePerson++;
                    }
                    if(nowShuttlePerson == m) {
                        break;
                    }
                }
                nowTime = nowTime.plusMinutes(t);
            }
        }

        return answer.toString();
    }

    public static class Person implements Comparable<Person>{
        boolean isGone;
        LocalTime time;

        public Person(String s) {
            time = LocalTime.parse(s);
            isGone = false;
        }

        @Override
        public int compareTo(Person o) {
            return time.compareTo(o.time);
        }
    }
}