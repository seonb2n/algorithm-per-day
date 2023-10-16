package IOTest;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class DataStreamTest {

    public static void main(String[] args) {
        ByteArrayOutputStream bos = null;
        DataOutputStream dos = null;

        byte[] result = null;

        try {
            bos = new ByteArrayOutputStream();
            dos = new DataOutputStream(bos);
            dos.writeInt(10);
            dos.writeFloat(20.0f);
            dos.writeBoolean(true);
            dos.writeLong(1L);

            result = bos.toByteArray();

            String[] hex = new String[result.length];

            for (int i = 0; i < result.length; i++) {
                if (result[i] < 0) {
                    hex[i] = String.format("%02x", result[i] + 256);
                } else {
                    hex[i] = String.format("%02x", result[i]);
                }
            }

            System.out.println("10 진수 : " + Arrays.toString(result));
            System.out.println("16 진수 : " + Arrays.toString(hex));
//            10 진수 : [0, 0, 0, 10, 65, -96, 0, 0, 1]
//            16 진수 : [00, 00, 00, 0a, 41, a0, 00, 00, 01]

            dos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
