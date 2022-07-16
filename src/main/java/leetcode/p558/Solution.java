package leetcode.p558;

// Definition for a QuadTree node.
class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;

    public Node() {}

    public Node(boolean _val,boolean _isLeaf,Node _topLeft,Node _topRight,Node _bottomLeft,Node _bottomRight) {
        val = _val;
        isLeaf = _isLeaf;
        topLeft = _topLeft;
        topRight = _topRight;
        bottomLeft = _bottomLeft;
        bottomRight = _bottomRight;
    }
};

public class Solution {
    // x可能为0，也可能为1
    // 1 | x 的结果 都是 1
    // 0 | x 的结果 都是 x
    public Node intersect(Node quadTree1, Node quadTree2) {
        // quadTree1是叶子节点
        if(quadTree1.isLeaf) {
            // quadTree1值为1，那么不管quadTree2是不是子节点，也不管quadTree2节点的值或者其子节点是0还是1，那么两个或之后的结果都为1，直接返回value为1的叶子节点即可
            if(quadTree1.val) {
                return new Node(true, true, null, null, null, null);
            }
            // quadTree1值为0，那么或的结果就是quadTree2
            return quadTree2;
        }
        // quadTree2是叶子节点
        if (quadTree2.isLeaf) {
            if (quadTree2.val) {
                return new Node(true, true, null, null, null, null);
            }
            return quadTree1;
        }

        // quadTree1和quadTree2都不是叶子节点
        // 不是叶子节点，一定有四个叉
        Node topLeft = intersect(quadTree1.topLeft, quadTree2.topLeft);
        Node topRight = intersect(quadTree1.topRight, quadTree2.topRight);
        Node bottomLeft = intersect(quadTree1.bottomLeft, quadTree2.bottomLeft);
        Node bottomRight = intersect(quadTree1.bottomRight, quadTree2.bottomRight);

        // 下面逻辑是根据上面递归返回的结果，构造父节点的过程
        // 上面递归返回的四个节点都是叶子节点，并且值相同，那么就可以合并成一个叶子节点
        if (topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf && topLeft.val == topRight.val && topRight.val == bottomLeft.val && bottomLeft.val == bottomRight.val) {
            return new Node(topLeft.val, true, null, null, null, null);
        }

        // 如果上面递归返回的四个节点不都是叶子节点，或者值并不相同，那么就不能合并，就需要创建一个上面四个节点的父节点
        return new Node(false, false, topLeft, topRight, bottomLeft, bottomRight);
    }


}
