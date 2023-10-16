package IOTest;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedStreamTest {

    public static void main(String[] args) {
        try {
            FileOutputStream fos = new FileOutputStream("123.txt");
            // 버퍼 크기가 5이기 떄문에 5까지만 출력된 상태로 프로그램이 종료된다.
            BufferedOutputStream bos = new BufferedOutputStream(fos, 5);
            for (int i = 0; i < 9; i++) {
                bos.write(i);
            }

            fos.close();
            // BufferedOutputStream 의 close 를 호출해주면 버퍼에 남아 있던 모든 내용이 호출된다.
            // 내부 로직에서 flush 를 호출하기 때문이다.
//            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
