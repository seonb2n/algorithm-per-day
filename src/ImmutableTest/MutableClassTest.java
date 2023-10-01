package ImmutableTest;

public class MutableClassTest {

    public static class A {
        String myField;

        public A(String myField) {
            this.myField = myField;
        }

        void setField(String myField) {
            this.myField = myField;
        }
    }

    // final 키워드로 상속을 통한 변경 허용 안함
    public final static class B {

        // final 키워드로 객체의 변경에 대해 허용하지 않음
        private final A a;

        public B(A a) {
            // 생성자에서 필드 초기화
            this.a = a;
        }

        // Getter 메서드만 제공
        public A getA() {
            return new A(this.a.myField);
        }
    }

    public static void main(String[] args) {
        B b = new B(new A("field"));
        System.out.println(System.identityHashCode(b));

        b.getA().setField("field2"); //cannot assign a value to final variable a
        System.out.println(System.identityHashCode(b));

        System.out.println(b.getA().myField);
    }

}
