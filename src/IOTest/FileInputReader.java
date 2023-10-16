package IOTest;

import java.io.FileInputStream;
import java.io.FileReader;

public class FileInputReader {

    public static void main(String[] args) {
        try {
            String fileName = "C:\\Users\\seonbin\\IdeaProjects\\Algorithm\\src\\IOTest\\test.txt";
            FileInputStream fis = new FileInputStream(fileName);
            FileReader fr = new FileReader(fileName);

            int data = 0;
            // FileInputStream 을 통해서 파일을 읽는다.
            while ((data=fis.read()) != -1) {
                System.out.print((char) data);
            }
            System.out.println();
            fis.close();

            // FileReader 를 통해 파일 내용을 읽는다.
            while ((data=fr.read()) != -1) {
                System.out.print((char) data);
            }
            System.out.println();
            fr.close();
//            Hello, ìëíì¸ì?
//            Hello, 안녕하세요?
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
