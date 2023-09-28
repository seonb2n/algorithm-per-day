package ImmutableTest;

import java.util.ArrayList;

/**
 * Date 객체를 Immutable 로
 */
public class MutableList {

    private final ArrayList list;

    public MutableList(ArrayList list) {
        this.list = list;
    }

    public ArrayList getList() {
        return this.list;
    }

    public MutableList(MutableList other) {
        this.list = other.getList();
    }

}
