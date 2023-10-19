package SingletonTest;

public class EnumSingletonPattern {

    public static void main(String[] args) {
        EnumSingleton singleton1 = EnumSingleton.INSTANCE;
        System.out.println(singleton1.hashCode());
        EnumSingleton singleton2 = EnumSingleton.INSTANCE;
        System.out.println(singleton2.hashCode());
    }

}

enum EnumSingleton {
    INSTANCE;

    public static void doSomething() {
        System.out.println("Class Called");
    }
}
