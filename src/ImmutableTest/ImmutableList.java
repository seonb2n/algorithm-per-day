package ImmutableTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 객체를 Immutable 로
 */
public class ImmutableList {

    // 외부에서 접근할 수 없도록 private 으로 만든다.
    private final List<MutableText> list;

    // 생성자를 통해 생성된 객체는 복사해서 생성한다.
    public ImmutableList(ArrayList<MutableText> list) {
        this.list = list.stream().map(obj -> new MutableText(obj.getText())).collect(Collectors.toList());
    }


    // 반환하는 경우에도 객체를 복사해서 전달한다.
    public List getList() {
        return this.list.stream().map(obj -> new MutableText(obj.getText())).collect(Collectors.toList());
    }

    // 다른 객체로부터 복사할 때도 복사 생성자로 구현한다.
    public ImmutableList(ImmutableList other) {
        this.list = new ArrayList(other.getList());
    }

}
