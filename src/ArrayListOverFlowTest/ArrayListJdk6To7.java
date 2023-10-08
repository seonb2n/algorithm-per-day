package ArrayListOverFlowTest;

public class ArrayListJdk6To7 {

    public static void main(String[] args) {

        int oldCapacity = Integer.MAX_VALUE / 2;
        System.out.println(oldCapacity); //1073741823
        int jdk6NewCapacity = oldCapacity * 3 / 2;
        System.out.println(jdk6NewCapacity); //-536870913
        int jdk7NewCapacity = oldCapacity + (oldCapacity >> 1);
        System.out.println(jdk7NewCapacity); // 1610612734
        jdk7NewCapacity = jdk7NewCapacity + (oldCapacity >> 1);
        System.out.println(jdk7NewCapacity); // 2147483645
        jdk7NewCapacity = jdk7NewCapacity + (oldCapacity >> 1);
        System.out.println(jdk7NewCapacity); // -1610612740
    }

}
