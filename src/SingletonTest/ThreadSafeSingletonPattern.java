package SingletonTest;

public class ThreadSafeSingletonPattern {

    public static void main(String[] args) {
        ThreadSafeSingleton singleton = ThreadSafeSingleton.getInstance();
        System.out.println(singleton.toString());
        // SingletonTest.ThreadSafeSingleton@36d64342
        ThreadSafeSingleton singleton2 = ThreadSafeSingleton.getInstance();
        System.out.println(singleton2.toString());
        // SingletonTest.ThreadSafeSingleton@36d64342
    }

}

class ThreadSafeSingleton {
    private static ThreadSafeSingleton instance;

    private ThreadSafeSingleton() {}

    // getInstance 를 매번 synchronized 로 처리하는 것은 성능에 좋지 않음
//    public static synchronized ThreadSafeSingleton getInstance() {
//        if (instance == null) {
//            instance = new ThreadSafeSingleton();
//        }
//
//        return instance;
//    }

    public static ThreadSafeSingleton getInstance() {
        if (instance == null) {
            synchronized (ThreadSafeSingleton.class) {
                if (instance == null) {
                    instance = new ThreadSafeSingleton();
                }
            }
        }
        return instance;
    }

}
