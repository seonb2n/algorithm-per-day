package IOTest;

import java.io.File;

public class FileNameFilterTest {

    static int deltedFiles = 0;

    public static void main(String[] args) {
        if (args.length != 1) {
            System.exit(0);
        }


    }

    static void delete(File dir, String ext) {
        File[] files = dir.listFiles();

        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                delete(files[i], ext);
            } else {


            }
        }
    }

}



