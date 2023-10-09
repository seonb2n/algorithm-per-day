package ThreadTest;

public class ThreadInterruptTest {

    public static void main(String[] args) {
        ThreadEx1 th1= new ThreadEx1();
        ThreadEx2 th2 = new ThreadEx2();
        th1.start();
        th2.start();

        try {
            th1.sleep(2000); // 현재 실행중인 쓰레드에 대해 작동하기 때문에, main 쓰레드가 영향을 받음
        } catch (InterruptedException e) {

        }

        System.out.println("<main 종료>");
    }

}

class ThreadEx1 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.print("-");
        }
        System.out.println("<th1 종료>");
    }
}

class ThreadEx2 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.print("|");
        }
        System.out.println("<th2 종료>");
    }
}
