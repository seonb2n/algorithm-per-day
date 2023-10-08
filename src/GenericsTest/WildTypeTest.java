package GenericsTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class WildTypeTest {
    public static void main(String[] args) {
        FruitBox<Apple> appleBox = new FruitBox<Apple>();
        appleBox.add(new Apple("GreenApple", 200));
        appleBox.add(new Apple("GreenApple", 100));
        appleBox.add(new Apple("GreenApple", 300));

        Collections.sort(appleBox.getList());
        System.out.println(appleBox.getList());
    }
}

class Fruit implements Comparable<Fruit>{
    String name;
    int weight;

    Fruit(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    @Override
    public int compareTo(Fruit o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }
}

class Apple extends Fruit {
    Apple(String name, int weight) {
        super(name, weight);
    }
}


class FruitBox<T> {
    List<T> list = new ArrayList<>();

    void add(T t) {
        list.add(t);
    }

    public List<T> getList() {
        return list;
    }
}
