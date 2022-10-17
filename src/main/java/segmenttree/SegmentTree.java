package segmenttree;

import java.util.Arrays;

public class SegmentTree {
    private int[] tree;
    private int[] data;
    // treeIndex所对应的懒标记，标识treeIndex所代表的区间内所有节点值都需要在后续进行更新，默认为0
    private int[] lazy;
    private Merge merge;

    /**
     * @param data data数组的索引就是区间端点，比如索引为0，就表示[0,0]这个区间，就只有一个值，比如索引为1，就表示[1,1]这个区间
     *             对应的值就是区间节点所代表的值（自定义，可以是区间内所有值的和，也可以是区间内所有值的最大值）
     * @param merge 将两个元素merge成一个元素的方法（自定义）
     * */
    public SegmentTree(int[] data, Merge merge) {
        this.merge = merge;
        this.data = data;

        // tree的数组大小是计算得来的，对于固定的区间是固定的，
        //      一颗线段树的节点数量大约为4倍的区间端点数量不到（区间端点的数量就是data数组索引的最大值+1，即data数组的长度）
        this.tree = new int[this.data.length * 4];
        this.lazy = new int[this.data.length * 4];

        // 在0位置创建表示区间[0, arr.length-1]的线段树，即从根节点开始创建整个线段树
        buildSegmentTree(0, 0, this.data.length - 1);
    }

    public int getSize() {
        return data.length;
    }

    /**
     * 在treeIndex位置创建表示区间[l...r]的线段树
     * @param treeIndex 所要创建的线段树的根节点
     * @param l 所要创建的线段树根节点所表示的区间范围左端点
     * @param r 所要创建的线段树根节点所表示的区间范围右端点
     * */
    private void buildSegmentTree(int treeIndex, int l, int r) {
        // 区间重合，说明区间中只有一个元素
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }

        int leftChildIndex = leftChild(treeIndex);
        int rightChildIndex = rightChild(treeIndex);

        // 区间是连续的，算出来区间的中点
        int mid = l + (r - l) / 2;
        // 在 treeIndex左孩子 位置创建 区间左端点到区间中点 这段区间的线段树
        buildSegmentTree(leftChildIndex, l, mid);
        // 在 treeIndex右孩子 位置创建 区间中点+1到区间右端点 这段区间的线段树
        buildSegmentTree(rightChildIndex, mid + 1, r);

        // 区间节点所代表的值，根据业务自定义，这里定义为区间内所有值的和
//        tree[treeIndex] = tree[leftChildIndex] + tree[rightChildIndex];
        // 如果定义为区间内所有值的最大值
//        tree[treeIndex] = Math.max(tree[leftChildIndex], tree[rightChildIndex]);
        // 区间节点所代表的值，根据业务自定义的merge操作来定
        tree[treeIndex] = merge.merge(tree[leftChildIndex], tree[rightChildIndex]);
    }


    /**
     * 返回[l, r]区间所代表的值
     * @param queryL 查询区间的左端点
     * @param queryR 查询区间的右端点
     * @return 返回的是查询区间所代表的值
     * */
    public int query(int queryL, int queryR) {
        return query(0, 0, data.length - 1, queryL, queryR);
    }

    /**
     * 在treeIndex根节点所代表的[l, r]区间内，查询[queryL, queryR]区间所代表的值
     * (treeIndex, l, r这三个节点是线段树的信息，treeIndex是线段树节点的索引，l是该线段树节点所代表的区间左端点，r是该线段树节点所代表的区间右端点)
     * @param treeIndex 根节点
     * @param l 根节点所代表的区间左端点
     * @param r 根节点所代表的区间右端点
     * @param queryL 查询区间的左端点
     * @param queryR 查询区间的右端点
     * @return 返回的是查询区间所代表的值
     * */
    private int query(int treeIndex, int l, int r, int queryL, int queryR) {
        if (l == queryL && r == queryR) {
            return tree[treeIndex];
        }

        pushDown(treeIndex, l, r);

        int mid = l + (r - l) / 2;
        int leftChildIndex = leftChild(treeIndex);
        int rightChildIndex = rightChild(treeIndex);

        // [queryL, queryR]全部落在右孩子所代表的区间[mid+1, r]内
        if (queryL >= mid + 1) {
            return query(rightChildIndex, mid + 1, r, queryL, queryR);
        } else if (queryR <= mid) {     // [queryL, queryR]全部落在左孩子所代表的区间[l, mid]内
            return query(leftChildIndex, l, mid, queryL, queryR);
        }

        // [queryL, queryR]没有全部落在左孩子所代表的区间[l, mid]内，也没有全部落在右孩子所代表的区间[mid+1, r]内
        // 一部分落在左孩子所代表的区间内，一部分落在右孩子所代表的区间内，两边都要查，最后将两边的结果merge成一个结果返回
        // leftChildIndex节点所代表的区间[l, mid]
        int leftResult = query(leftChildIndex, l, mid, queryL, mid);
        // rightChildIndex节点所代表的区间[mid+1, r]
        int rightResult = query(rightChildIndex, mid + 1, r, mid + 1, queryR);
        return merge.merge(leftResult, rightResult);
    }

    /**
     * 更新data数组索引为index的值为e
     * @param index data数组的索引
     * @param e 更新后的值
     * */
    public void set(int index, int e) {
        data[index] = e;
        set(0, 0, data.length - 1, index, e);
    }

    /**
     * 在treeIndex根节点所代表的[l, r]区间内，更新[index, index]区间所代表的值为e
     * (data数组中的索引就是线段树中的区间端点)
     * @param treeIndex 根节点
     * @param l 根节点所代表的区间左端点
     * @param r 根节点所代表的区间右端点
     * @param index 需要更新区间的端点，更新[index, index]区间所代表的值
     * @param e 更新之后的值
     * */
    public void set(int treeIndex, int l, int r, int index, int e) {
        if (l == r) {
            tree[treeIndex] = e;
            return;
        }

        int mid = l + (r - l) / 2;
        int leftChildIndex = leftChild(treeIndex);
        int rightChildIndex = rightChild(treeIndex);

        // 在右孩子[mid+1, r]中
        if (index >= mid + 1) {
            set(rightChildIndex, mid + 1, r, index, e);
        } else {    // 在左孩子[l, mid]中
            set(leftChildIndex, l, mid, index, e);
        }

        // 因为可能修改完之后，左右孩子的值发生了变化，至于是左孩子还是右孩子的值发生变化不用管
        // 对于当前节点，只需要重新merge一下左右孩子的值即可
        tree[treeIndex] = merge.merge(tree[leftChildIndex], tree[rightChildIndex]);
    }

    /**
     * 将[left,right]区间中对应的值都加e
     * @param left 更新区间的左边界
     * @param right 更新区间的右边界
     * @param e 增加的值
     * */
    public void add(int left, int right, int e) {
        add(0, 0, data.length - 1, left, right, e);
    }

    /**
     * 在treeIndex根节点所代表的[l, r]区间内，将[left,right]区间中对应的值都加e
     * (data数组中的索引就是线段树中的区间端点)
     * @param treeIndex 根节点
     * @param l 根节点所代表的区间左端点
     * @param r 根节点所代表的区间右端点
     * @param left 更新区间的左边界
     * @param right 更新区间的右边界
     * @param e 增加的值
     * */
    public void add(int treeIndex, int l, int r, int left, int right, int e) {
        // 正好需要更新的区间是treeIndex所代表的区间，则直接更新treeIndex所代表区间的值，然后设置treeIndex的lazy标记，标识treeIndex所表示的区间中每个节点的值都需要加e
        if (l == left && right == r) {
            lazy[treeIndex] = e;
            // 因为treeIndex节点所代表的区间中每个叶子节点的值都增加了e
            tree[treeIndex] += (right - left + 1) * e;
            return;
        }

        pushDown(treeIndex, l, r);

        int mid = l + (r - l) / 2;
        int leftChildIndex = leftChild(treeIndex);
        int rightChildIndex = rightChild(treeIndex);

        // 全在右孩子[mid+1, r]中
        if (left >= mid + 1) {
            add(rightChildIndex, mid + 1, r, left, right, e);
        } else if(right <= mid) { // 全在左孩子[l, mid]中
            add(leftChildIndex, l, mid, left, right, e);
        } else {    // 一部分在左孩子中，一部分在右孩子中
            add(leftChildIndex, l, mid, left, mid, e);
            add(rightChildIndex, mid + 1, r, mid + 1, right, e);
        }

        // 因为可能修改完之后，左右孩子的值发生了变化，至于是左孩子还是右孩子的值发生变化不用管
        // 对于当前节点，只需要重新merge一下左右孩子的值即可
        tree[treeIndex] = merge.merge(tree[leftChildIndex], tree[rightChildIndex]);
    }

    // 从treeIndex节点向子节点传播懒标记，[l, r]是treeIndex所代表的区间，即treeIndex所代表区间内的节点之前没有进行更新
    private void pushDown(int treeIndex, int l, int r) {
        // 当该节点存在懒标记，才开始向子节点传播懒标记
        if(lazy[treeIndex] != 0) {
            int mid = l + (r - l) / 2;
            lazy[leftChild(treeIndex)] = lazy[rightChild(treeIndex)] = lazy[treeIndex];
            // treeIndex左孩子所代表的区间为[l, mid]
            tree[leftChild(treeIndex)] += (mid - l + 1) * lazy[leftChild(treeIndex)];
            // treeIndex右孩子所代表的区间为[mid + 1, r]
            tree[rightChild(treeIndex)] += (r - mid) * lazy[rightChild(treeIndex)];
            // treeIndex的懒标记已经传播给子节点，去除其懒标记
            lazy[treeIndex] = 0;
        }
    }

    /**
     * 计算treeIndex位置节点的左孩子索引
     * @param treeIndex 需要计算左孩子索引的节点位置
     * @return treeIndex位置节点的左孩子索引
     * */
    private int leftChild(int treeIndex) {
        return 2 * treeIndex + 1;
    }

    /**
     * 计算treeIndex位置节点的右孩子索引
     * @param treeIndex 需要计算右孩子索引的节点位置
     * @return treeIndex位置节点的右孩子索引
     * */
    private int rightChild(int treeIndex) {
        return 2 * treeIndex + 2;
    }

    @Override
    public String toString() {
        StringBuilder treeBuilder = new StringBuilder();
        treeBuilder.append("tree: [");
        for(int i = 0; i < tree.length; i ++) {
            treeBuilder.append(tree[i]);

            if(i != tree.length - 1) {
                treeBuilder.append(", ");
            }
        }
        treeBuilder.append("]\n");
        treeBuilder.append("lazy: [");
        for(int i = 0; i < lazy.length; i ++) {
            treeBuilder.append(lazy[i]);

            if(i != lazy.length - 1) {
                treeBuilder.append(", ");
            }
        }
        treeBuilder.append("]\n");
        return treeBuilder.toString();
    }

    public static void main(String[] args) {
        int[] data = new int[] {-2, 0, 3, -5, 2, -1};
        SegmentTree segmentTree = new SegmentTree(data, (a, b) -> a + b);
        System.out.println(segmentTree);
        segmentTree.add(1,4, 3);
        System.out.println(segmentTree);
        System.out.println(segmentTree.query(2,3));
        System.out.println(segmentTree);

    }
}
