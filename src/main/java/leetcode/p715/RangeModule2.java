package leetcode.p715;

// 线段树+动态开点
class RangeModule2 {
    class Node {
        // 该节点所表示的区间的左端点
        public int start;
        // 该节点所表示区间的右端点
        public int end;
        // 表示当前区间是否被覆盖
        public boolean cover;
        // 表示当前区间内有多少个整数值
        public int sum;

        public Node leftNode;
        public Node rightNode;

        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    // 区间的最大值
    private int N = (int)1e9;
    private Node root;

    public RangeModule2() {
        root = new Node(1, N);
    }

    public void addRange(int left, int right) {
        update(root, left, right - 1, 1);
    }

    /**
     * 更新以root为根的节点，在[l,r]区域内代表的值为value
     *
     * @param value 1 表示覆盖；-1 表示取消覆盖
     * */
    private void update(Node root, int l, int r, int value) {
        // 如果root节点所表示的区间，被新加入的区间[l,r]所包含
        if (l <= root.start && r >= root.end) {
            root.cover = value == 1;
            root.sum = value;
            return;
        }

        int mid = l + (r - l) / 2;

    }


    public void removeRange(int left, int right) {

    }

    public boolean queryRange(int left, int right) {


        return false;
    }

    public static void main(String[] args) {
        RangeModule2 rangeModule = new RangeModule2();
        rangeModule.addRange(5, 8);
        System.out.println(rangeModule.toString());
        System.out.println(rangeModule.queryRange(3, 4));
        rangeModule.removeRange(5, 6);
        System.out.println(rangeModule.toString());
        rangeModule.removeRange(3, 6);
        System.out.println(rangeModule.toString());
        rangeModule.addRange(1, 3);
        System.out.println(rangeModule.toString());
        System.out.println(rangeModule.queryRange(2, 3));
        rangeModule.addRange(4, 8);
        System.out.println(rangeModule.toString());
        System.out.println(rangeModule.queryRange(2, 3));
        rangeModule.removeRange(4, 9);
        System.out.println(rangeModule.toString());
    }
}
