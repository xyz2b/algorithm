package skiplist;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SkipList<E extends Comparable<E>> {
    private static final int MAX_LEVEL = 32;
    private static final double P_FACTOR = 0.25;
    private final Random random = new Random();

    class SkipListNode {
        private List<SkipListNode> forwards;
        private E e;
        private SkipListNode backwards;

        public SkipListNode() {}

        public SkipListNode(int level, E e) {
            forwards = new ArrayList<>(level);
            for(int i = 0; i < level; i++) {
                forwards.add(new SkipListNode());
            }
            this.e = e;
        }
    }

    private SkipListNode head, tail;
    private int size;
    // 最大层数
    private int level;

    public SkipList() {
        // dummyHead
        head = tail = new SkipListNode(MAX_LEVEL, null);
        size = 0;
        level = 0;
    }

    public void add(E e) {
        List<SkipListNode> updates = new ArrayList<>(MAX_LEVEL);
        // updates数组中存放的是每一层小于且最接近 target 的元素节点
        // 添加元素时，该数组初始化时的值是虚拟头节点，因为如果某一层不存在 小于且最接近 target 的元素节点（即这一层没有元素节点），那么其实就是插入到虚拟头节点之后
        for(int i = 0; i < MAX_LEVEL; i++) {
            updates.add(head);
        }

        SkipListNode cur = head;
        for(int i = level - 1; i >= 0; i--) {
            // 找到第 i 层小于且最接近 target 的元素
            while (cur.forwards.get(i) != null && cur.forwards.get(i).e.compareTo(e) < 0) {
                cur = cur.forwards.get(i);
            }
            // 每一层查找的最后一个节点（每一层小于且最接近 target 的元素节点）
            updates.set(i, cur);
            // 下降一层
        }

        int lv = randomLevel();
        // 保存最大的层数
        level = Math.max(lv, level);
        SkipListNode newNode = new SkipListNode(lv, e);
        newNode.backwards = cur;
        for(int i = 0; i < lv; i++) {
            // 将新节点插入到 小于且最接近 target 元素节点的后面（类似链表的插入，将新节点每一层的forward指针指向 每一层小于且最接近 target 元素节点，将每一层小于且最接近 target 元素节点的forward指针指向新节点）
            // 将新节点第 i 层的 forward 指针指向 第 i 层小于且最接近 target 元素节点
            newNode.forwards.set(i, updates.get(i).forwards.get(i));
            // 对第 i 层的状态进行更新，将第 i 层小于且最接近 target 元素节点的 forward 指向新的节点
            updates.get(i).forwards.set(i, newNode);
        }
    }

    public boolean erase(E e) {
        List<SkipListNode> updates = new ArrayList<>(MAX_LEVEL);
        // updates数组中存放的是每一层小于且最接近 target 的元素节点
        // 在删除节点时，该数组初始化的值是为空节点
        for(int i = 0; i < MAX_LEVEL; i++) {
            updates.add(new SkipListNode());
        }

        SkipListNode cur = head;
        for(int i = level - 1; i >= 0; i--) {
            // 找到第 i 层小于且最接近 target 的元素
            while (cur.forwards.get(i) != null && cur.forwards.get(i).e.compareTo(e) < 0) {
                cur = cur.forwards.get(i);
            }
            // 每一层查找的最后一个节点
            updates.set(i, cur);
            // 下降一层
        }

        // 第 i 层小于且最接近 target 的元素 的下一个元素，如果下一个元素不存在或者值不相等，则说明不存在被删除的元素
        cur = cur.forwards.get(0);
        // 要删除的值不存在，返回false
        if (cur == null || !cur.e.equals(e)) {
            return false;
        }

        for(int i = 0; i < level; i++) {
            // 每一层小于且最接近 target 的元素节点 在第i层 不指向 当前要删除的节点，说明在第i层删除这个节点，对其他节点没影响，就不需要更新它们的forward指针
            if(updates.get(i).forwards.get(i) != cur) {
                break;
            }
            // 将 每一层小于且最接近 target 的元素节点 在第i层的 forward 指针 更新指向为 被删除节点在第i层的 forward 指针所指向的节点
            updates.get(i).forwards.set(i, cur.forwards.get(i));
        }
        // 更新最大层数
        // 并不知道删除的节点是否是层数最大层数的节点
        // 如果删除的节点是最大层数的节点，那么第二大层数又是多少
        // 此时可以通过dummyHead来探测，因为头节点的低level层都是有指向的
        // 如果被删除的节点不是最大层数的节点，那么头节点的低level层还是都有指向的
        // 如果被删除的节点是最大层数的节点，那么头节点的高几层（最大层数和第二大层数差值的层数）的 forward指向是null的， 那么有几层就将当前最大层数减去几层
        while (level > 1 && head.forwards.get(level - 1) == null) {
            level--;
        }

        // 更新backward
        cur.forwards.get(0).backwards = cur.backwards;
        return true;
    }

    public boolean search(E e) {
        SkipListNode cur = head;
        for(int i = level - 1; i >= 0; i--) {
            // 找到第 i 层小于且最接近 target 的元素
            while (cur.forwards.get(i) != null && cur.forwards.get(i).e.compareTo(e) < 0) {
                cur = cur.forwards.get(i);
            }
            // 下降一层
        }

        // 第 i 层小于且最接近 target 的元素 的下一个元素，如果下一个元素不存在或者值不相等，则说明不存在要查找的元素
        cur = cur.forwards.get(0);
        // 检测当前元素的值是否等于 target
        if (cur != null && cur.e.equals(e)) {
            return true;
        }

        return false;
    }

    private int randomLevel() {
        int lv = 1;
        // 随机生成lv
        while (random.nextDouble() < P_FACTOR && lv < MAX_LEVEL) {
            lv++;
        }
        return lv;
    }
}
