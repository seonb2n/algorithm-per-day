package LambdaTest;

public class LambdaArgTest {

    public static void main(String[] args) {
        Outer outer = new Outer();
        Outer.Inner inner = outer.new Inner();
        inner.method(100);
    }

}

class Outer {
    int val = 10;

    class Inner {
        int val = 20;

        void method(int i) {
            int val = 30;
            //i = 10;

            MyFunction f = () -> {
                System.out.println("i : " + i); //100
                System.out.println("val : " + val); //30
                System.out.println("this.val : " + this.val); //20
                System.out.println("Outer.this.val : " + Outer.this.val); //10
                // i = 1;
                // val = 1;
            };
            f.myMethod();
        }
    }
}

@FunctionalInterface
interface MyFunction {
    void myMethod();
}
