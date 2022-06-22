package binarysearchtree;

public class BinarySearchTree {
    class Node {
        private int e;
        private Node left;
        private Node right;

        public Node(int e) {
            this.e = e;
            left = right = null;
        }

        public Node(int e, Node left, Node right) {
            this.e = e;
            this.left = left;
            this.right = right;
        }
    }

    private Node root;
    private int size;

    public void add(int e) {
        root = add(root, e);
        size++;
    }

    // 向以node为根的二分搜索树中插入元素e，返回插入元素e之后的根
    public Node add(Node node, int e) {
        if (node == null) {
            return new Node(e);
        }

        if (e > node.e) {
            node.right = add(node.right, e);
        } else if (e < node.e) {
            node.left = add(node.left, e);
        }

        return node;
    }

    public void preOder() {
        preOder(root);
    }

    public void preOder(Node node) {
        if (node == null) {
            return;
        }
        preOder(node.left);
        System.out.println(node.e);
        preOder(node.right);
    }

    public int floor(int e) {
         int[] floor = new int[1];
         floor[0] = -1;
         floor(root, e, floor);

         return floor[0];
    }

    public void floor(Node node, int e, int[] floor) {
        if (node == null) {
            return;
        }

        if (node.e >= e) {
            floor(node.left, e, floor);
        } else {    // node.e < e，将小于e的元素都记录下来，最后留下来的就是最后一个小于e的元素，也就是小于e的最大元素
            floor[0] = node.e;
            floor(node.right, e, floor);
        }
    }

    public int ceil(int e) {
        int[] ceil = new int[1];
        ceil[0] = -1;
        ceil(root, e, ceil);

        return ceil[0];
    }

    public void ceil(Node node, int e, int[] ceil) {
        if (node == null) {
            return;
        }

        if (node.e > e)   {// node.e > e，将大于e的元素都记录下来，最后留下来的就是最后一个大于e的元素，也就是大于e的最小元素
            ceil[0] = node.e;
            ceil(node.left, e, ceil);
        } else {
            ceil(node.right, e, ceil);
        }
    }

    public int kMax(int k) {
        int[] rst = new int[1];
        int[] kK = new int[1];
        kK[0] = k;
        rst[0] = -1;
        kMax(root, kK, rst);
        return rst[0];
    }

    public void kMax(Node node, int[] kK, int[] rst) {
        if (node == null) {
            return;
        }

        kMax(node.left, kK, rst);
        kK[0]--;
        if (kK[0] == 0 && rst[0] == -1) {   // 二分搜索树的中序遍历的结果是顺序的，所以只需要在中序遍历打印元素值的时候（第一次打印是最小值，第二次打印是第二小值，以此类推，因为是顺序打印的），去做k值减减操作，当k为0时，就说明这就是第k小的值
            rst[0] = node.e;
            return;
        }
        kMax(node.right, kK, rst);
    }

    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        int[] nums = new int[] {41, 22, 58, 15, 33, 50, 63, 13, 37, 42, 53};
        for(int num : nums) {
            binarySearchTree.add(num);
        }
        binarySearchTree.preOder();
        System.out.println(binarySearchTree.floor(53));
        System.out.println(binarySearchTree.ceil(13));

        System.out.println(binarySearchTree.kMax(12));
    }
}
