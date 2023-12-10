package SerializableTest;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class MyObject implements Serializable {

    @JsonProperty
    String content;

    public MyObject(String content) {
        this.content = content;
    }
}
