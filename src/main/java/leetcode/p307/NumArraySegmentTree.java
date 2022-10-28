package leetcode.p307;

// 线段树
class NumArraySegmentTree {

    private int[] tree;
    private int[] data;
    private int length;

    public NumArraySegmentTree(int[] nums) {
        data = nums;
        length = nums.length;
        // 线段树大概需要开4倍的空间
        tree = new int[length * 4];
        buildSegmentTree(0, 0, length - 1);
    }

    // 构建以node为根的线段树，根代表的区间为[l,r]
    private void buildSegmentTree(int node, int l, int r) {
        if(l == r) {
            tree[node] = data[l];
            return;
        }

        int mid = l + (r - l) / 2;
        buildSegmentTree(leftChild(node), l, mid);
        buildSegmentTree(rightChild(node), mid + 1, r);

        tree[node] = tree[leftChild(node)] + tree[rightChild(node)];
    }

    public int sumRange(int left, int right) {
        return find(0, 0, length - 1, left, right);
    }

    // 查找以node为根(区间范围：[l, r])的线段树中，查询[left,right]区间的值
    private int find(int node, int l, int r, int left, int right) {
        if(l == left && r == right) {
            return tree[node];
        }

        int mid = l + (r - l) / 2;
        if(left > mid) {    // [left, right]全在node右子树所表示的区间
            return find(rightChild(node), mid + 1, r, left, right);
        } else if(right < mid + 1) { // [left, right]全在node左子树所表示的区间
            return find(leftChild(node), l, mid, left, right);
        }

        int leftSum = find(leftChild(node), l, mid, left, mid);
        int rightSum = find(rightChild(node), mid + 1, r, mid + 1, right);
        return leftSum + rightSum;
    }

    public void update(int index, int val) {
        update(0, 0 , length - 1, index, val);
    }

    // 将以node为根(区间范围：[l, r])的线段树中，index位置的元素值更新为val
    private void update(int node, int l, int r, int index, int val) {
        if(l == index && r == index) {
            tree[node] = val;
            data[index] = val;
            return;
        }

        int mid = l + (r - l) / 2;
        if(index < mid + 1) {
            update(leftChild(node), l, mid, index, val);
        } else if (index > mid) {
            update(rightChild(node), mid + 1, r, index, val);
        }

        tree[node] = tree[leftChild(node)] + tree[rightChild(node)];
    }

    private int leftChild(int index) {
        return index * 2 + 1;
    }

    private int rightChild(int index) {
        return index * 2 + 2;
    }

    public static void main(String[] args) {
        NumArraySegmentTree numArray = new NumArraySegmentTree(new int[] {1, 3, 5});
        numArray.sumRange(0, 2); // 返回 1 + 3 + 5 = 9
        numArray.update(1, 2);   // nums = [1,2,5]
        numArray.sumRange(0, 2); // 返回 1 + 2 + 5 = 8

    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */
