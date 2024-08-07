package leetcode.p572;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}


public class Solution {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        Queue<TreeNode> queue = new ArrayDeque<>();

        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if(isSameTree(node, subRoot)) {
                return true;
            }

            if(node.left != null) {
                queue.offer(node.left);
            }

            if(node.right != null) {
                queue.offer(node.right);
            }
        }

        return false;
    }

    private boolean isSameTree(TreeNode node1, TreeNode node2) {
        if(node1 == null && node2 == null) {
            return true;
        } else if(node1 == null && node2 != null) {
            return false;
        } else if(node1 != null && node2 == null) {
            return false;
        }

        if(node1.val != node2.val) {
            return false;
        }

        boolean left = isSameTree(node1.left, node2.left);
        boolean right = isSameTree(node1.right, node2.right);

        return left & right;
    }


    // 深度优先遍历，然后转成子串匹配问题，判断subRoot是不是root的子串即可(kmp)
    // 但是这里存在一个问题：假设 s 由两个点组成，1 是根，2 是 1 的左孩子；t 也由两个点组成，1 是根，2 是 1 的右孩子。
    // 这样一来 s 和 t 的深度优先搜索序列相同，可是 t 并不是 s 的某一棵子树。
    // 为了解决这个问题，我们可以引入两个空值 lNull 和 rNull，当一个节点的左孩子或者右孩子为空的时候，就插入这两个空值，这样深度优先搜索序列就唯一对应一棵树。
    // 处理完之后，就可以通过判断「s 的深度优先搜索序列包含 t 的深度优先搜索序列」来判断答案。
    private int lNull, rNull, maxElement;
    public boolean isSubtree2(TreeNode root, TreeNode subRoot) {
        maxElement = Integer.MIN_VALUE;
        getMaxElement(root);
        getMaxElement(subRoot);
        lNull = maxElement + 1;
        rNull = maxElement + 2;

        List<Integer> rQ = new ArrayList<>();
        List<Integer> sQ = new ArrayList<>();

        dfs(root, rQ);
        dfs(subRoot, sQ);

        return kmp(rQ, sQ);
    }

    private void getMaxElement(TreeNode node) {
        if(node == null) {
            return;
        }

        maxElement = Math.max(maxElement, node.val);
        getMaxElement(node.left);
        getMaxElement(node.right);
    }

    private boolean kmp(List<Integer> rQ, List<Integer> sQ) {
        int[] lps = getLps(sQ);

        // 匹配失败时，rQ的搜索索引i不需要回退，只需要将sQ的搜索索引j回退为j之前最长对称前缀的长度即可，即最长对称前缀后一个字符
        int i = 0, j = 0;
        while (i < rQ.size()) {
            if (!Objects.equals(rQ.get(i), sQ.get(j))) {    // 这里不能直接用==，因为是Integer对象，==是比较对象是不是同一个，地址是否相同，而不是值是否相同
                if(j == 0) {    // j == 0 时表明第一个字符就不相等，没有对称前缀，继续比较rQ下一个字符即可
                    i++;
                } else {
                    j = lps[j-1];
                }
            } else {
                i++;
                j++;
                if(j == sQ.size()) return true;
            }
        }
        return false;
    }

    // 最长对称前缀
    private int[] getLps(List<Integer> sQ) {
        int[] lps = new int[sQ.size()];
        for(int i = 1; i < sQ.size(); i++) {
            int a = lps[i-1];
            while (a > 0 && sQ.get(i) != sQ.get(a)) {
                a = lps[a - 1];
            }
            if(sQ.get(i) == sQ.get(a)) lps[i] = a + 1;
        }
        return lps;
    }

    private void dfs(TreeNode node, List<Integer> q) {
        if(node == null) {
            return;
        }

        q.add(node.val);
        if(node.left != null) {
            dfs(node.left, q);
        } else {
            q.add(lNull);
        }

        if(node.right != null) {
            dfs(node.right, q);
        } else {
            q.add(rNull);
        }
    }

    public TreeNode createTree(Integer[] tree) {
        TreeNode root = new TreeNode(tree[0]);

        // 层序遍历得到的结果
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.addLast(root);

        for(int i = 1; i < tree.length; i+=2) {
            TreeNode node = deque.pollFirst();
            if(tree[i] != null) {
                node.left = new TreeNode(tree[i]);
                deque.addLast(node.left);
            }
            if(i + 1 < tree.length && tree[i + 1] != null) {
                node.right = new TreeNode(tree[i+1]);
                deque.addLast(node.right);
            }
        }

        return root;
    }


    public static void main(String[] args) {
        Integer[] s = {-130,-182,2,-198,-155,-97,127,-199,-193,-168,-137,-117,-38,9,131,null,null,-195,-183,-173,-166,-143,-134,-119,-110,-63,-17,null,30,null,184,-197,-194,-192,null,-176,-169,null,-164,-152,-140,-136,-133,-124,null,-115,-98,-75,-58,-22,-12,11,68,147,188,null,null,null,null,null,-190,null,null,-172,null,null,-160,-154,-151,null,-139,null,null,null,-132,-125,null,-116,-112,-100,null,-94,-73,-62,-44,-24,-18,-15,-3,10,19,48,103,135,158,185,189,null,-187,null,null,-163,-157,null,null,null,-144,null,-138,null,null,-129,null,null,null,null,null,-109,null,null,-76,-74,-65,null,-61,-45,-42,-27,null,-21,null,null,-13,-4,1,null,null,14,25,37,57,70,104,null,146,148,175,null,186,null,191,null,null,null,null,null,null,-145,null,null,null,null,-128,null,-101,-88,null,null,null,-66,-64,null,-60,-49,null,null,null,-36,-26,null,-20,null,null,-7,null,0,null,null,16,23,null,35,43,51,67,69,86,null,111,141,null,null,150,171,179,null,null,null,196,null,null,null,-126,null,null,-89,-81,null,null,null,null,null,null,-55,-46,null,-28,null,null,null,null,-8,-5,null,null,null,null,null,null,34,null,39,null,50,53,59,null,null,null,83,90,106,120,null,142,149,153,165,174,177,181,null,null,null,null,-91,null,-85,-79,-56,-54,-47,null,-33,null,null,null,-6,null,31,null,null,40,null,null,null,null,null,null,78,84,87,96,105,108,118,121,null,null,null,null,null,156,161,170,null,null,null,178,180,null,-93,null,null,null,null,-77,null,null,null,-52,null,null,null,-29,null,null,null,32,null,41,72,80,null,null,null,null,95,102,null,null,107,null,113,null,null,122,null,null,159,162,167,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,71,null,null,82,92,null,99,null,null,null,null,114,null,123};
        Integer[] t = {-4,-7,null,-8,-5,null,null,-6};

        Solution solution = new Solution();
        TreeNode root = solution.createTree(s);
        TreeNode subRoot = solution.createTree(t);
        System.out.println(solution.isSubtree2(root, subRoot));
    }
}
