package leetcode.p341;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class NestedIterator2 implements Iterator<Integer> {
    private List<Integer> list = new ArrayList<>();
    private int index = 0;

    // 栈
    public NestedIterator2(List<NestedInteger> nestedList) {
        Stack<NestedInteger> stack = new Stack<>();

        for(NestedInteger i : nestedList) {
            stack.push(i);

            while (!stack.isEmpty()) {
                NestedInteger e = stack.pop();
                if(e.isInteger()) {
                    list.add(e.getInteger());
                } else {
                    List<NestedInteger> n = e.getList();
                    for(int x = n.size() - 1; x >= 0; x--) {
                        stack.push(n.get(x));
                    }
                }
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
