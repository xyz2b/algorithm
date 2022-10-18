package segmenttree;

import java.util.Stack;

public class DynamicSegmentTree {
    class Node {
        // 该节点表示的值
        private int val;
        // 该节点所表示的区间
        private int l;
        private int r;
        // 该节点的lazy标记
        private int lazy;
        // 左右孩子
        private Node left;
        private Node right;

        public Node() {

        }

        public Node(int l, int r, int val) {
            this.l = l;
            this.r = r;
            this.val = val;
            lazy = 0;
            left = null;
            right = null;
        }

        public Node(int l, int r, int val, Node left, Node right) {
            this.l = l;
            this.r = r;
            this.val = val;
            lazy = 0;
            this.left = left;
            this.right = right;
        }
    }

    private Node root;
    private Merge merge;

    public DynamicSegmentTree(int left, int right, Merge merge) {
        root = new Node(left, right, 0);
        this.merge = merge;
    }

    public void set(int left, int right, int e) {
        set(root, left, right, e);
    }

    public void set(Node node, int left, int right, int e) {
        // 正好需要更新的区间是node所代表的区间，则直接更新node所代表区间的值，然后设置node的lazy标记，标识node所表示的区间中每个节点的值都需要设置为e
        if (node.l == left && right == node.r) {
            node.lazy = e;
            // 因为node节点所代表的区间中每个叶子节点的值都设置为了e
            node.val = (right - left + 1) * e;
            return;
        }

        pushDownSet(node);

        int mid = node.l + (node.r - node.l) / 2;

        // 全在右孩子[mid+1, r]中
        if (left >= mid + 1) {
            set(node.right, left, right, e);
        } else if(right <= mid) { // 全在左孩子[l, mid]中
            set(node.left, left, right, e);
        } else {    // 一部分在左孩子中，一部分在右孩子中
            set(node.left, left, mid, e);
            set(node.right, mid + 1, right, e);
        }

        // 因为可能修改完之后，左右孩子的值发生了变化，至于是左孩子还是右孩子的值发生变化不用管
        // 对于当前节点，只需要重新merge一下左右孩子的值即可
        node.val = merge.merge(node.left.val, node.right.val);
    }

    // 从node节点向子节点传播懒标记，即node所代表区间内的节点之前没有进行更新
    private void pushDownSet(Node node) {
        int mid = node.l + (node.r - node.l) / 2;
        // 动态创建
        if(node.left == null) node.left = new Node(node.l, mid, 0);
        if(node.right == null) node.right = new Node(mid + 1, node.r, 0);

        // 当该节点存在懒标记，才开始向子节点传播懒标记
        if(node.lazy != 0) {
            node.left.lazy = node.right.lazy = node.lazy;
            // node左孩子所代表的区间为[l, mid]
            node.left.val = (mid - node.l + 1) * node.left.lazy;
            // node右孩子所代表的区间为[mid + 1, r]
            node.right.val = (node.r - mid) * node.right.lazy;
            // node的懒标记已经传播给子节点，去除其懒标记
            node.lazy = 0;
        }
    }

    public int query(int queryL, int queryR) {
        return query(root, queryL, queryR);
    }

    private int query(Node node, int queryL, int queryR) {
        if (node.l == queryL && node.r == queryR) {
            return node.val;
        }

        pushDownSet(node);

        int mid = node.l + (node.r - node.l) / 2;

        // [queryL, queryR]全部落在右孩子所代表的区间[mid+1, r]内
        if (queryL >= mid + 1) {
            return query(node.right, queryL, queryR);
        } else if (queryR <= mid) {     // [queryL, queryR]全部落在左孩子所代表的区间[l, mid]内
            return query(node.left, queryL, queryR);
        }

        // [queryL, queryR]没有全部落在左孩子所代表的区间[l, mid]内，也没有全部落在右孩子所代表的区间[mid+1, r]内
        // 一部分落在左孩子所代表的区间内，一部分落在右孩子所代表的区间内，两边都要查，最后将两边的结果merge成一个结果返回
        // leftChildIndex节点所代表的区间[l, mid]
        int leftResult = query(node.left, queryL, mid);
        // rightChildIndex节点所代表的区间[mid+1, r]
        int rightResult = query(node.right, mid + 1, queryR);
        return merge.merge(leftResult, rightResult);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            stringBuilder.append("l:" + node.l + ", r: " + node.r + ", val: " + node.val + ", lazy: " + node.lazy + "\n");
            if(node.left != null)
                stack.push(node.left);
            if(node.right != null)
                stack.push(node.right);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        DynamicSegmentTree dynamicSegmentTree = new DynamicSegmentTree(0, 1000000, (a, b) -> a + b);
        dynamicSegmentTree.query(3, 5);
        System.out.println(dynamicSegmentTree);
    }
}
