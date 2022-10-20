package twothreetree;

public class TwoThreeTree {
    class Node {
        private int leftElement;
        private int rightElement;
        private boolean threeNode;

        private Node left;
        private Node middle;
        private Node right;

        private boolean split;

        public Node(int e) {
            this.leftElement = e;
            this.threeNode = false;
        }

        public Node(int e, Node left, Node right, boolean split) {
            this.leftElement = e;
            this.threeNode = false;
            this.left = left;
            this.right = right;
            this.split = split;
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

        if(node.threeNode) {    // 当前节点是3节点
            if(e < node.leftElement) {
                if(node.left != null) {
                    // 返回到上一层递归时，融合，再分裂
                    // 1. 再分裂的根节点在当前3节点的左侧，继续分裂出去的根是3节点的左元素，右元素作为左元素的右孩子（当前3节点的中、右孩子，变成其左、右孩子），下层分裂的根节点作为左元素的左孩子
                    Node leftNode = add(node.left, e);
                    if(leftNode.split) {    // 下层返回的根节点是分裂而来的，此时就需要进行融合
                        leftNode.split = false;
                        return new Node(node.leftElement, leftNode, new Node(node.rightElement, node.middle, node.right, false), true);
                    }
                    node.left = leftNode;
                    return node;
                }
            } else if (e > node.leftElement && e < node.rightElement) {
                // 1. 分裂的根节点在当前3节点的中间，继续分裂出去的根是下层分裂的根节点，3节点的左元素作为其左孩子（下层分裂的根节点的左孩子作为其右孩子，3节点的左孩子作为其左孩子），右元素作为其右孩子（下层分裂的根节点的右孩子作为其左孩子，3节点的右孩子作为其右孩子）
                if(node.middle != null) {
                    Node middleNode = add(node.middle, e);
                    if(middleNode.split) {
                        middleNode.split = false;
                        return new Node(middleNode.leftElement, new Node(node.leftElement, node.left, middleNode.left, false), new Node(node.rightElement, middleNode.right, node.right, false), true);
                    }
                    node.middle = middleNode;
                    return node;
                }
            } else if(e > node.rightElement){
                // 1. 分裂的根节点在当前3节点的右侧，继续分裂出去的根是3节点的右元素，左元素作为右元素的左孩子（当前3节点的左、中孩子，变成其左、右孩子），下层分裂的根节点作为右元素的右孩子
                if (node.right != null) {
                    Node rightNode = add(node.right, e);
                    if(rightNode.split) {
                        rightNode.split = false;
                        return new Node(node.rightElement, new Node(node.leftElement, node.left, node.middle, false), rightNode, true);
                    }
                    node.right = rightNode;
                    return node;
                }
            }

            // 都没有走到上面的分支，说明最终插入的位置为node的空叶子节点，由于23树中新节点不能插入到空叶子节点的位置，所以插入位置应当为当前node，当前node是3节点
            // 插入的位置是一个3节点，先融合再分裂
            if(e < node.leftElement) {
                return new Node(node.leftElement, new Node(e), new Node(node.rightElement), true);
            } else if (e > node.leftElement && e < node.rightElement) {
                return new Node(e, new Node(node.leftElement), new Node(node.rightElement), true);
            } else if (e > node.rightElement){
                return new Node(node.rightElement, new Node(node.leftElement), new Node(e), true);
            }

        } else {    // 当前节点是2节点
            if (e > node.leftElement) {
                if(node.right != null) {
                    Node rightNode = add(node.right, e);
                    if(rightNode.split) {
                        rightNode.split = false;
                        node.rightElement = rightNode.leftElement;
                        node.right = rightNode.right;
                        node.middle = rightNode.left;
                        node.threeNode = true;
                    } else {
                        node.right = rightNode;
                    }
                    return node;
                }
            } else if (e < node.leftElement) {
                if(node.left != null) {
                    Node leftNode = add(node.left, e);
                    if(leftNode.split) {
                        leftNode.split = false;
                        node.rightElement = node.leftElement;
                        node.leftElement = leftNode.leftElement;
                        node.left = leftNode.left;
                        node.middle = leftNode.right;
                        node.threeNode = true;
                    } else {
                        node.left = leftNode;
                    }
                    return node;
                }
            }

            // 都没有走到上面的分支，说明最终插入的位置为node的空叶子节点，由于23树中新节点不能插入到空叶子节点的位置，所以插入位置应当为当前node，当前node是2节点
            // 插入的位置是一个2节点，直接融合即可
            if(e > node.leftElement) {
                node.rightElement = e;
                node.threeNode = true;
                size++;
                return node;
            } else if (e < node.leftElement){    // e <= node.leftElement
                node.rightElement = node.leftElement;
                node.leftElement = e;
                node.threeNode = true;
                size++;
                return node;
            }
        }

        // 融合3节点分裂出来的根节点，怎么判断子节点是三节点分裂出来的
        return node;
    }

    public static void main(String[] args) {
        TwoThreeTree twoThreeTree = new TwoThreeTree();
        twoThreeTree.add(35);
        twoThreeTree.add(15);
        twoThreeTree.add(12);
        twoThreeTree.add(10);
        twoThreeTree.add(9);
        twoThreeTree.add(8);
        twoThreeTree.add(7);
    }
}