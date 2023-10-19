package SingletonTest;

public class StaticBlockSingletonPattern {

    public static void main(String[] args) {
        StaticBlockSingleton singleton = StaticBlockSingleton.getInstance();
        System.out.println(singleton.toString());
        // SingletonTest.ClassicSingleton@36d64342
        StaticBlockSingleton singleton2 = StaticBlockSingleton.getInstance();
        System.out.println(singleton2.toString());
        // SingletonTest.ClassicSingleton@36d64342
    }

}

class StaticBlockSingleton {
    private static StaticBlockSingleton instance;

    private StaticBlockSingleton() {}

    static {

        try {
            instance = new StaticBlockSingleton();
        } catch (Exception e) {
            throw new RuntimeException("Exception Occured");
        }
    }

    public static StaticBlockSingleton getInstance() {
        return instance;
    }
}