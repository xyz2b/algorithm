package leetcode.p1206;

import java.util.Arrays;
import java.util.Random;

public class Skiplist {
    private static final int MAX_LEVEL = 32;
    private static final double P_FACTOR = 0.25;
    private final Random random = new Random();

    class SkipListNode {
        private SkipListNode[] forwards;
        private int e;
        private SkipListNode backwards;

        public SkipListNode(int level, int e) {
            forwards = new SkipListNode[level];
            this.e = e;
        }
    }

    private SkipListNode head, tail;
    private int size;
    // 最大层数
    private int level;

    public Skiplist() {
        // dummyHead
        head = tail = new SkipListNode(MAX_LEVEL, -1);
        size = 0;
        level = 0;
    }

    public void add(int e) {
        SkipListNode[] updates = new SkipListNode[MAX_LEVEL];
        Arrays.fill(updates, head);

        SkipListNode cur = head;
        for(int i = level - 1; i >= 0; i--) {
            // 找到第 i 层小于且最接近 target 的元素
            while (cur.forwards[i] != null && cur.forwards[i].e < e) {
                cur = cur.forwards[i];
            }
            // 每一层查找的最后一个节点
            updates[i] = cur;
            // 下降一层
        }

        int lv = randomLevel();
        // 保存最大的层数
        level = Math.max(lv, level);
        SkipListNode newNode = new SkipListNode(lv, e);
        newNode.backwards = cur;
        for(int i = 0; i < lv; i++) {
            // 对第 i 层的状态进行更新，将当前元素的 forward 指向新的节点
            newNode.forwards[i] = updates[i].forwards[i];
            updates[i].forwards[i] = newNode;
        }
        size++;
    }

    public boolean erase(int e) {
        SkipListNode[] updates = new SkipListNode[MAX_LEVEL];

        SkipListNode cur = head;
        for(int i = level - 1; i >= 0; i--) {
            // 找到第 i 层小于且最接近 target 的元素
            while (cur.forwards[i] != null && cur.forwards[i].e < e) {
                cur = cur.forwards[i];
            }
            // 每一层查找的最后一个节点
            updates[i] = cur;
            // 下降一层
        }

        // 第 i 层小于且最接近 target 的元素 的下一个元素，如果下一个元素不存在或者值不相等，则说明不存在被删除的元素
        cur = cur.forwards[0];
        // 要删除的值不存在，返回false
        if (cur == null || cur.e != e) {
            return false;
        }

        for(int i = 0; i < level; i++) {
            // 每一层查找的最后一个节点 在当前层 不指向 当前要删除的节点，说明删除这个节点，对其他节点没影响，就不需要更新它们的forward指针
            // 只有每一层查找的最后一个节点在 当前层 才会指向要删除的这个节点
            if(updates[i].forwards[i] != cur) {
                break;
            }
            // 将 每一层查找的最后一个节点 在当前层的 forward 指针 指向 被删除节点在当前层的 forward 指针
            updates[i].forwards[i] = cur.forwards[i];
        }
        // 更新最大层数
        // 并不知道删除的节点是否是层数最大层数的节点
        // 如果删除的节点是最大层数的节点，那么第二大层数又是多少
        // 此时可以通过dummyHead来探测，因为头节点的低level层都是有指向的
        // 如果被删除的节点不是最大层数的节点，那么头节点的低level层还是都有指向的
        // 如果被删除的节点是最大层数的节点，那么头节点的高几层（最大层数和第二大层数差值的层数）的 forward指向是null的， 那么有几层就将当前最大层数减去几层
        while (level > 1 && head.forwards[level - 1] == null) {
            level--;
        }
        // 更新backward
        cur.forwards[0].backwards = cur.backwards;
        size--;
        return true;
    }

    public boolean search(int e) {
        SkipListNode cur = head;
        for(int i = level - 1; i >= 0; i--) {
            // 找到第 i 层小于且最接近 target 的元素
            while (cur.forwards[i] != null && cur.forwards[i].e < e) {
                cur = cur.forwards[i];
            }
            // 下降一层
        }

        // 第 i 层小于且最接近 target 的元素 的下一个元素，如果下一个元素不存在或者值不相等，则说明不存在要查找的元素
        cur = cur.forwards[0];
        // 检测当前元素的值是否等于 target
        if (cur != null && cur.e == e) {
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
