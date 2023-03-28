package leetcode.p341;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NestedIterator implements Iterator<Integer> {
    private List<Integer> list = new ArrayList<>();
    private int index = 0;

    public NestedIterator(List<NestedInteger> nestedList) {
        parseNestedList(nestedList);
    }

    // 递归
    private void parseNestedList(List<NestedInteger> nestedList) {
        for(NestedInteger i : nestedList) {
            if(i.isInteger()) {
                list.add(i.getInteger());
            } else {
                parseNestedList(i.getList());
            }
        }
    }

    @Override
    public Integer next() {
        Integer rst = list.get(index);
        index++;
        return rst;
    }

    @Override
    public boolean hasNext() {
        return index < list.size();
    }
}
