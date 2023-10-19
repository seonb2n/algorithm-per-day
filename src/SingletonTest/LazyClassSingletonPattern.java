package SingletonTest;

public class LazyClassSingletonPattern {

    public static void main(String[] args) {
        LazySingleton singleton = LazySingleton.getInstance();
        System.out.println(singleton.toString());
        // SingletonTest.ClassicSingleton@36d64342
        LazySingleton singleton2 = LazySingleton.getInstance();
        System.out.println(singleton2.toString());
        // SingletonTest.ClassicSingleton@36d64342
    }

}

class LazySingleton {
    private static LazySingleton instance;

    private LazySingleton() {
        // private 생성자
    }

    public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
