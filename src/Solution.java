// https://leetcode.com/problems/flatten-nested-list-iterator


import java.util.*;

public class NestedIterator implements Iterator<Integer> {
    Queue<Integer> queue = new LinkedList<>();

    // 생성자
    public NestedIterator(List<NestedInteger> nestedList) {
        for (NestedInteger i : nestedList) {
            addQueue(i);
        }
    }

    void addQueue(NestedInteger nestedInteger) {
        if (nestedInteger.isInteger()) {
            queue.add(nestedInteger.getInteger());
        } else {
            for (NestedInteger nestedIntegerInList : nestedInteger.getList()) {
                addQueue(nestedIntegerInList);
            }
        }
    }

    @Override
    public Integer next() {
        // hasNext()가 true를 반환한 경우에만 호출된다고 가정
        return queue.poll();
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }
}

interface NestedInteger {
    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return empty list if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}
