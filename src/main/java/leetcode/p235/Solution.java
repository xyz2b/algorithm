package leetcode.p235;

import leetcode.p144.TreeNode;

public class Solution {
    /**
     * p和q都小于根节点root，说明最近公共祖先一定不是root，与此同时也一定不在root的右子树中，所以就可以继续在root的左子树中寻找
     * p和q都大于根节点root，说明最近公共祖先一定不是root，与此同时也一定不在root的左子树中，所以就可以继续在root的右子树中寻找
     * p和q一个大于root，一个小于root，此时root就是它俩的最近公共祖先
     * p和根节点root相等，不管q大于还是小于root，此时p就是它俩的最近公共祖先
     * q和根节点root相等，不管p大于还是小于root，此时q就是它俩的最近公共祖先
     *
     * 综合来看，只要p和q不是同时大于或小于root，那么root就是它俩的最近公共祖先
     * */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) {
            return null;
        }

        // 找到p的路径 和 找到q的路径 的分叉点
        if(p.val > root.val && q.val > root.val) {  // 两个节点的值都大于root.val，则最近公共祖先必定不是root，而是在root.right子树中
            return lowestCommonAncestor(root.right, p, q);
        } else if (p.val > root.val && q.val < root.val) {  // 一个节点的值大于root.val，一个节点的值小于root.val，则root就是寻找这两个节点的路径分叉点
            return root;
        } else if (p.val > root.val && q.val == root.val) { // 一个节点的值等于root.val，一个节点的值大于或小于root.val，则说明大于或小于的节点还在root的右子树或左子树中，而有一个等于root的，说明root就是最近的公共祖先
            return root;
//            return q;
        } else if (p.val < root.val && q.val > root.val) { // 一个节点的值大于root.val，一个节点的值小于root.val，则root就是寻找这两个节点的路径分叉点
            return root;
        } else if(p.val < root.val && q.val < root.val) {   // 两个节点的值都小于root.val，则最近公共祖先必定不是root，而是在root.left子树中
            return lowestCommonAncestor(root.left, p, q);
        } else if(p.val < root.val && q.val == root.val) {  // 一个节点的值等于root.val，一个节点的值大于或小于root.val，则说明大于或小于的节点还在root的右子树或左子树中，而有一个等于root的，说明root就是最近的公共祖先
            return root;
//            return q;
        } else if(p.val == root.val && q.val > root.val) {  // 一个节点的值等于root.val，一个节点的值大于或小于root.val，则说明大于或小于的节点还在root的右子树或左子树中，而有一个等于root的，说明root就是最近的公共祖先
            return root;
//            return p;
        } else if(p.val == root.val && q.val < root.val) { // 一个节点的值等于root.val，一个节点的值大于或小于root.val，则说明大于或小于的节点还在root的右子树或左子树中，而有一个等于root的，说明root就是最近的公共祖先
            return root;
//            return p;
        } else { // p.val == root.val && q.val == root.val，两个节点的值都等于root，说明root就是公共祖
            return root;
        }
    }

    // 综合的逻辑
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) {
            return null;
        }

        // 找到p的路径 和 找到q的路径 的分叉点
        if(p.val > root.val && q.val > root.val) {  // 两个节点的值都大于root.val，则最近公共祖先必定不是root，而是在root.right子树中
            return lowestCommonAncestor2(root.right, p, q);
        }  else if(p.val < root.val && q.val < root.val) {   // 两个节点的值都小于root.val，则最近公共祖先必定不是root，而是在root.left子树中
            return lowestCommonAncestor2(root.left, p, q);
        } else { // 只要p和q不是同时大于或小于root，那么root就是它俩的最近公共祖先
            return root;
        }
    }

}
