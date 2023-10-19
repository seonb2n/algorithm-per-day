package SingletonTest;

public class EagerClassSingletonPattern {
    public static void main(String[] args) {
        EagerSingleton singleton = EagerSingleton.getInstance();
        System.out.println(singleton.toString());
        // SingletonTest.ClassicSingleton@36d64342
        EagerSingleton singleton2 = EagerSingleton.getInstance();
        System.out.println(singleton2.toString());
        // SingletonTest.ClassicSingleton@36d64342
    }

}

class EagerSingleton {
    private static EagerSingleton instance = new EagerSingleton();

    private EagerSingleton() {
        // private 생성자
    }

    public static EagerSingleton getInstance() {
        return instance;
    }
}