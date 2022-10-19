package avl;

import java.util.ArrayList;
import java.util.List;

public class AVLTree {
    class Node {
        private int e;
        private Node left;
        private Node right;
        private int height;

        public Node(int e) {
            this.e = e;
            left = right = null;
            height = 1;
        }

        public Node(int e, Node left, Node right) {
            this.e = e;
            this.left = left;
            this.right = right;
            height = 1;
        }
    }

    private Node root;
    private int size;

    public void add(int e) {
        root = add(root, e);
    }

    // 获取节点高度
    private int getHeight(Node node) {
        if (node == null)
            return 0;
        return node.height;
    }

    // 获取节点平衡度
    private int getBalanceFactor(Node node) {
        if(node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    // 是否是平衡二叉树
    public boolean isBalance() {
        return isBalance(root);
    }

    private boolean isBalance(Node node) {
        if(node == null) {
            return true;
        }

        if (Math.abs(getBalanceFactor(node)) > 1) {
            return false;
        }

        return isBalance(node.left) && isBalance(node.right);
    }

    // 是否是二分搜索树
    public boolean isBST() {
        List<Integer> list = new ArrayList<>();
        inOrder(root, list);

        for(int i = 1; i < list.size(); i++) {
            if(list.get(i) < list.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    private void inOrder(Node node, List<Integer> list) {
        if (node == null) {
            return;
        }
        inOrder(node.left, list);
        list.add(node.e);
        inOrder(node.right, list);
    }

    // 左旋
    private Node leftRotate(Node y) {
        Node x = y.right;
        Node t2 = x.left;

        // 左旋转
        x.left = y;
        y.right = t2;

        // 更新height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    // 右旋
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node t3 = x.right;

        // 左旋转
        x.right = y;
        y.left = t3;

        // 更新height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    // 向以node为根的二分搜索树中插入元素e，返回插入元素e之后的根
    public Node add(Node node, int e) {
        if (node == null) {
            size++;
            return new Node(e);
        }

        if (e > node.e) {
            node.right = add(node.right, e);
        } else if (e < node.e) {
            node.left = add(node.left, e);
        }

        // 维护当前节点的height
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;

        // 计算当前节点平衡因子
        int balanceFactor = getBalanceFactor(node);

        // LL
        if(balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            // 右旋
            return rightRotate(node);
        }

        // RR
        if(balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
            // 左旋
            return leftRotate(node);
        }

        // LR
        if(balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            // node.left左旋
            node.left = leftRotate(node.left);
            // node右旋
            return rightRotate(node);
        }

        // RL
        if(balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            // node.right右旋
            node.right = rightRotate(node.right);
            // node左旋
            return leftRotate(node);
        }

        return node;
    }

    public void delete(int e) {
        root = delete(root, e);
    }

    private Node delete(Node node, int e) {
        if(node == null) {
            return null;
        }

        // 在以node为根的树中删除e元素的节点之后，需要返回的新的根节点
        Node retNode;
        if(node.e > e) {
            node.left = delete(node.left, e);
            retNode = node;
        } else if (node.e < e) {
            node.right = delete(node.right, e);
            retNode = node;
        } else { // node.e == e // 找到待删除节点
            if(node.right == null) {    // 待删除节点只有左孩子
                Node leftNode = node.left;
                node.left = null;
                size--;
                retNode = leftNode;
            } else if (node.left == null) { // 待删除节点只有右孩子
                Node rightNode = node.right;
                node.right = null;
                size--;
                retNode = rightNode;
            } else {    // 待删除节点既有左孩子又有右孩子
                // 找到比待删除节点大的最小节点（后继），即待删除节点右子树的最小节点
                // 用这个节点顶替待删除的节点
                Node successor = findMin(node.right);
                successor.right = delete(node.right, successor.e);
                successor.left = node.left;
                node.left = node.right = null;
                retNode = successor;
            }
        }

        // 有可能node是叶子节点，同时它又是要被删除的节点，此时retNode就为null，需要判断一下
        if(retNode == null) {
            return null;
        }

        // 维护当前节点的height
        retNode.height = Math.max(getHeight(retNode.left), getHeight(retNode.right)) + 1;

        // 计算当前节点平衡因子
        int balanceFactor = getBalanceFactor(retNode);

        // LL
        if(balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0) {
            // 右旋
            return rightRotate(retNode);
        }

        // RR
        if(balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0) {
            // 左旋
            return leftRotate(retNode);
        }

        // LR
        if(balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
            // node.left左旋
            retNode.left = leftRotate(retNode.left);
            // node右旋
            return rightRotate(retNode);
        }

        // RL
        if(balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
            // node.right右旋
            retNode.right = rightRotate(retNode.right);
            // node左旋
            return leftRotate(retNode);
        }

        return retNode;
    }

    public int findMin() {
        return findMin(root).e;
    }

    private Node findMin(Node node) {
        if(node.left == null) {
            return node;
        }
        return findMin(node.left);
    }

    public int findMax() {
        return findMax(root).e;
    }

    private Node findMax(Node node) {
        if(node.right == null) {
            return node;
        }
        return findMin(node.right);
    }

    public static void main(String[] args) {
        AVLTree avl = new AVLTree();
        int[] nums = new int[] {41, 22, 58, 15, 33, 50, 63, 13, 37, 42, 53};
        for(int num : nums) {
            avl.add(num);
        }
        System.out.println(avl.isBST());
        System.out.println(avl.isBalance());
    }
}
