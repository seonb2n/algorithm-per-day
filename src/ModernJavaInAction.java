import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Map.entry;

public class ModernJavaInAction {

    public static void main(String[] args) {
        // 두 개의 HashMap 을 합치는 방법. 만약 두 HashMap 의 키가 같다면
        Map<String, String> family = Map.ofEntries(
                entry("A", "Apple"),
                entry("B", "Banana"),
                entry("C", "Cupcake")
        );
        Map<String, String> friends = Map.ofEntries(
                entry("C", "Cat"),
                entry("D", "Diamond"),
                entry("E", "Egg")
        );
        Map<String, String> everyone = new HashMap<>(family);
        friends.forEach((k, v) -> everyone.merge(k, v, (v1, v2) -> v1 + " || " + v2));
        System.out.println(everyone);

        LocalDate date = LocalDate.now().plusDays(5L);
        System.out.println(date.with(new NextWorkingDay()));
    }

    /**
     * 다음 일할 날을 구할 수 있는 class. 금요일 다음은 월요일이다.
     */
    public static class NextWorkingDay implements TemporalAdjuster {
        @Override
        public Temporal adjustInto(Temporal temporal) {
            // 오늘 날짜
            DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            int addDay = 1;
            if (dow == DayOfWeek.FRIDAY) addDay = 3;
            if (dow == DayOfWeek.SATURDAY) addDay = 2;
            return temporal.plus(addDay, ChronoUnit.DAYS);
        }
    }

    public String getCarInsuranceName(Optional<Person> person) {
        return person.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
    }

    public class Person {
        Optional<Car> car;

        public Optional<Car> getCar() {
            return car;
        }
    }

    public class Car {
        Optional<Insurance> insurance;

        public Optional<Insurance> getInsurance() {
            return insurance;
        }
    }

    public class Insurance {
        String name;

        public String getName() {
            return name;
        }
    }

}

