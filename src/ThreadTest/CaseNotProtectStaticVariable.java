package ThreadTest;


import java.util.concurrent.atomic.AtomicInteger;

/**
 * 전역 변수를 스레드에서 각각 변경하는 경우
 */
public class CaseNotProtectStaticVariable {

    static class Counter {
        private AtomicInteger count = new AtomicInteger(0);

        public int increment() {
            return count.incrementAndGet();
        }

        public int getCount() {
            return count.get();
        }
    }

    static class SynchronizedCounter {
        private int count = 0;

        public synchronized int increment() {
            return ++count;
        }

        public int getCount() {
            return count;
        }
    }

    public static void main(String[] args) {
        Counter counter = new Counter();

        Runnable task = () -> {
            for (int i = 0; i < 100000; i++) {
                counter.increment();
            }
        };

        // 두 개의 스레드가 동시에 Counter 객체를 공유하면서 increment 메서드를 호출
        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Final Count: " + counter.getCount());


        SynchronizedCounter synchronizedCounter = new SynchronizedCounter();
        task = () -> {
            for (int i = 0; i < 100000; i++) {
                synchronizedCounter.increment();
            }
        };
        // synchronize 키워드로 스레드에서 전역 변수에 대한 접근을 해결
        Thread thread3 = new Thread(task);
        Thread thread4 = new Thread(task);

        thread3.start();
        thread4.start();

        try {
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Final Count: " + synchronizedCounter.getCount());
    }

}
