package twothreetree;

public class TwoThreeTree {
    class Node {
        private int leftElement;
        private int rightElement;
        private boolean threeNode;

        private Node left;
        private Node middle;
        private Node right;


        public Node(int e) {
            this.leftElement = e;
            this.threeNode = false;
        }

        public Node(int e, Node left, Node right) {
            this.leftElement = e;
            this.threeNode = false;
            this.left = left;
            this.right = right;
        }

    }

    private Node root;
    private int size;

    public void add(int e) {
        root = add(root, e);
    }

    // 在以root为根的树中插入新节点，并返回插入后新的树根
    private Node add(Node node, int e) {
        // 根节点为空的情况
        if(node == null) {
            size++;
            return new Node(e);
        }

        Node ret = null;
        if(node.threeNode) {    // 当前节点是3节点
            if(e < node.leftElement) {
                if(node.left != null) {
                    node.left = add(node.left, e);
                }
            } else if (e > node.leftElement && e < node.rightElement) {
                if(node.middle != null) {
                    node.middle = add(node.middle, e);
                }
            } else if(e > node.rightElement){
                if (node.right != null) {
                    node.right = add(node.right, e);
                }
            }

            // 都没有走到上面的分支，说明最终插入的位置为node的空叶子节点，由于23树中新节点不能插入到空叶子节点的位置，所以插入位置应当为当前node，当前node是3节点
            // 插入的位置是一个3节点，先融合再分裂
            if(e < node.leftElement) {
                ret = new Node(node.leftElement, new Node(e), new Node(node.rightElement));
            } else if (e > node.leftElement && e < node.rightElement) {
                ret = new Node(e, new Node(node.leftElement), new Node(node.rightElement));
            } else if (e > node.rightElement){
                ret = new Node(node.rightElement, new Node(node.leftElement), new Node(e));
            }

        } else {    // 当前节点是2节点
            if (e > node.leftElement) {
                if(node.right != null) {
                    node.right = add(node.right, e);
                }
            } else if (e < node.leftElement) {
                if(node.left != null) {
                    node.left = add(node.left, e);
                }
            }

            // 都没有走到上面的分支，说明最终插入的位置为node的空叶子节点，由于23树中新节点不能插入到空叶子节点的位置，所以插入位置应当为当前node，当前node是2节点
            // 插入的位置是一个2节点，直接融合即可
            if(e > node.leftElement) {
                node.rightElement = e;
                node.threeNode = true;
                size++;
            } else if (e < node.leftElement){    // e <= node.leftElement
                node.rightElement = node.leftElement;
                node.leftElement = e;
                node.threeNode = true;
                size++;
            }
            ret = node;
        }

        // 融合3节点分裂出来的根节点，怎么判断子节点是三节点分裂出来的


        return ret;
    }
}