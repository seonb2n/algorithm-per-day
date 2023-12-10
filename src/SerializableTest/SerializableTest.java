package SerializableTest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SerializableTest {

    public static void main(String[] args) throws IOException {
        MyObject myObject = new MyObject("myContent");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(myObject);
        byte[] byteArray = bos.toByteArray();
        System.out.println(byteArray.toString());

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(myObject);
        byte[] jsonArray = jsonString.getBytes(StandardCharsets.UTF_8);
        System.out.println(jsonArray.toString());
    }

}
