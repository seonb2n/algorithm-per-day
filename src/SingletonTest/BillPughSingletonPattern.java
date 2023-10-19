package SingletonTest;

public class BillPughSingletonPattern {

    public static void main(String[] args) {
        BillPughSingleton singleton = BillPughSingleton.getInstance();
        System.out.println(singleton.toString());
        // SingletonTest.BillPughSingleton@36d64342
        BillPughSingleton singleton2 = BillPughSingleton.getInstance();
        System.out.println(singleton2.toString());
        // SingletonTest.BillPughSingleton@36d64342
    }
}

class BillPughSingleton {

    private BillPughSingleton() {}

    private static class SingletonHelper {
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }

    public static BillPughSingleton getInstance() {
        return SingletonHelper.INSTANCE;
    }

}
