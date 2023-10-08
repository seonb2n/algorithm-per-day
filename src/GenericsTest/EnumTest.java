package GenericsTest;

public class EnumTest {


}

enum Transporation {

    BUS(100) {
        int fare(int distance) { return distance * BASIC_FARE; }
    },
    TAXI(200) {
        int fare(int distance) { return distance * BASIC_FARE; }
    }
    ;

    protected final int BASIC_FARE;

    Transporation(int basicFare) {
        BASIC_FARE = basicFare;
    }

    public int getBasicFare() {
        return BASIC_FARE;
    }

    abstract int fare(int distance);
}
