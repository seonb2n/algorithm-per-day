package ImmutableTest;

public class MutableClassTest {

    public static class A {
        String myField;

        public A(String myField) {
            this.myField = myField;
        }
    }

    public static final class B {
        private final A a;

        public B(A a) {
            // 생성자에서 필드 초기화
            this.a = a;
        }

        // Getter 메서드만 제공
        public A getA() {
            return a;
        }
    }

    public static void main(String[] args) {
        B b = new B(new A("field"));
        System.out.println(System.identityHashCode(b));

        b.a = new A("field2"); //cannot assign a value to final variable a
        System.out.println(System.identityHashCode(b));

    }

}
