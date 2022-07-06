package leetcode.p715;

/**
 * 给线段树设置一个cover字段，表示当前区间是否完全覆盖，
 * 每次addRange都将范围内的区间置为true，表示该区间被覆盖，
 * 每次removeRange都将区间置为false，表示区间未被覆盖，
 * 每次queryRange只需要判断cover字段是否完全覆盖即可
 * */
public class RangeModule2 {

    /**
     * 线段树的结点
     */
    static class Node {
        //左范围
        private int leftX;
        //右范围
        private int rightX;
        //是否覆盖
        private boolean cover;
        //懒标记 -1表示未覆盖 1表示覆盖 0表示无懒更新
        private int lazy;
        //左子树和右子树
        Node leftChild, rightChild;

        public Node(int leftX, int rightX) {
            this.leftX = leftX;
            this.rightX = rightX;
        }
    }

    private Node root;


    /**
     * 区间更新
     *
     * @param root  树的根
     * @param left  左边界
     * @param right 右边界
     * @param value 更新值     1 覆盖    -1 取消覆盖
     */
    public void update(Node root, int left, int right, int value) {
        // 不在范围内 直接返回
        if (root.leftX > right || root.rightX < left) {
            return;
        }
        // 修改的区间[left, right]包含当前节点的区间[leftX, rightX]
        if (root.leftX >= left && root.rightX <= right) {
            root.lazy = value;
            root.cover = value != -1;
        } else {
            // 动态开点
            lazyCreate(root);
            // 下传lazy
            pushDown(root);
            // 更新左子树
            update(root.leftChild, left, right, value);
            // 更新右子树
            update(root.rightChild, left, right, value);
            // 上传结果
            pushUp(root);
        }
    }

    public boolean query(Node root, int left, int right) {
        // root节点的区间包含所查询的区间
        if (left <= root.leftX && root.rightX <= right) {
            return root.cover;
        }
        // 动态开点
        lazyCreate(root);
        // 下传lazy
        pushDown(root);
        int mid = root.leftX + (root.rightX - root.leftX) / 2;

        // 查询区间[left,right]全部落在左孩子所代表的区间[leftX, mid]内
        if (right <= mid) {
            return query(root.leftChild, left, right);
        } else if (left > mid) {    // 查询区间[left,right]全部落在右孩子所代表的区间[mid+1, rightX]内
            return query(root.rightChild, left, right);
        } else {
            // [left, right]没有全部落在左孩子所代表的区间[leftX, mid]内，也没有全部落在右孩子所代表的区间[mid+1, rightX]内
            // 一部分落在左孩子所代表的区间内，一部分落在右孩子所代表的区间内，两边都要查，最后将两边的结果merge成一个结果返回(这里的merge操作就是判断是否左右孩子的区间都有覆盖，与的操作)
            return query(root.leftChild, left, mid) && query(root.rightChild, mid + 1, right);
        }
    }

    /**
     * 创建左右子树
     *
     * @param root
     */
    public void lazyCreate(Node root) {
        int mid = root.leftX + (root.rightX - root.leftX) / 2;
        if (root.leftChild == null) {
            root.leftChild = new Node(root.leftX, mid);
        }
        if (root.rightChild == null) {
            root.rightChild = new Node(mid + 1, root.rightX);
        }
    }

    /**
     * 下传lazy
     *
     * @param root
     */
    public void pushDown(Node root) {
        if (root.lazy == 0) {
            return;
        }
        int value = root.lazy;
        root.leftChild.lazy = value;
        root.rightChild.lazy = value;
        if (value == -1) {
            root.leftChild.cover = false;
            root.rightChild.cover = false;
        } else {
            root.leftChild.cover = true;
            root.rightChild.cover = true;
        }
        root.lazy = 0;
    }

    /**
     * 上传结果
     *
     * @param root
     */
    public void pushUp(Node root) {
        root.cover = root.leftChild.cover && root.rightChild.cover;
    }

    public RangeModule2() {
        root = new Node(0, (int) (1e9));
    }

    public void addRange(int left, int right) {
        update(root, left, right - 1, 1);
    }

    public boolean queryRange(int left, int right) {
        return query(root, left, right - 1);
    }

    public void removeRange(int left, int right) {
        update(root, left, right - 1, -1);
    }

}