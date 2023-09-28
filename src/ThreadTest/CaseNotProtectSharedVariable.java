package ThreadTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 변수를 Thread 에서 각각 값을 변경하는 경우
 */
public class CaseNotProtectSharedVariable {

    public static void main(String[] args) {
//        List<Integer> sharedList = new ArrayList<Integer>();
//
//        Runnable task = () -> {
//            for (int i = 0; i < 1000; i++) {
//                sharedList.add(i);
//            }
//        };
//
//        Thread thread1 = new Thread(task);
//        Thread thread2 = new Thread(task);
//
//        thread1.start();
//        thread2.start();
//
//        try {
//            thread1.join();
//            thread2.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("Size of ArrayList: " + sharedList.size());

        List<Integer> synchronizedList = Collections.synchronizedList(new ArrayList<>());

        Runnable task2 = () -> {
            for (int i = 0; i < 1000; i++) {
                synchronizedList.add(i);
            }
        };

        Thread thread3 = new Thread(task2);
        Thread thread4 = new Thread(task2);

        thread3.start();
        thread4.start();

        try {
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Size of ArrayList: " + synchronizedList.size());
    }

}
