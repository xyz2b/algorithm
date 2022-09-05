package leetcode.p652;

import javafx.util.Pair;

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
    /**
     * 使用序列化进行唯一表示(序列化的结果其实就是一颗树的hash值)
     *
     * 一种容易想到的方法是将每一棵子树都「序列化」成一个字符串，并且保证：
     * 相同的子树会被序列化成相同的子串；
     * 不同的子树会被序列化成不同的子串。
     * 那么我们只要使用一个哈希表存储所有子树的序列化结果，如果某一个结果出现了超过一次，我们就发现了一类重复子树。
     *
     * 两种常用的序列化方法：
     * 第一种方法是使用层序遍历的方法进行序列化，例如示例 1中的二叉树可以序列化为：
     * 1,2,3,4,null,2,4,null,null,4
     * 这也是力扣平台上测试代码时输入一棵二叉树的默认方法。
     *
     * 第二种方法是使用递归的方法进行序列化。我们可以将一棵以 x 为根节点值的子树序列化为：
     * x(左子树的序列化结果)(右子树的序列化结果)
     * 左右子树分别递归地进行序列化。如果子树为空，那么序列化结果为空串。示例 1中的二叉树可以序列化为：
     * 1(2(4()())())(3(2(4()())())(4()()))
     */

    // 以TreeNode为根的树序列化之后的结果为String
    private final Map<String, TreeNode> seen = new HashMap<String, TreeNode>();
    private final Set<TreeNode> repeat = new HashSet<TreeNode>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        dfs(root);
        return new ArrayList<>(repeat);
    }

    // 返回以node为根的树，序列化之后的结果
    private String dfs(TreeNode node) {
        if (node == null) {
            return null;
        }

        String left = dfs(node.left);
        String right = dfs(node.right);

        StringBuilder stringBuilder = new StringBuilder();
        // 树根
        stringBuilder.append(node.val);

        // 左子树
        stringBuilder.append("(");
        if (left != null) {
            stringBuilder.append(left);
        }
        stringBuilder.append(")");

        // 右子树
        stringBuilder.append("(");
        if (right != null) {
            stringBuilder.append(right);
        }
        stringBuilder.append(")");

        // 可以将序列化的结果当成以node为根的树的hash值
        String serialization = stringBuilder.toString();

        if (seen.containsKey(serialization)) {
            // 因为set中加入的是对象，所以相同节点值的不同对象的指针不同，所以是不同的东西，不会去重
            // 所以直接从map中取出那个一样的对象节点加入集合
            repeat.add(seen.get(serialization));
        } else {
            seen.put(serialization, node);
        }

        return serialization;
    }

    /**
     * 使用三元组进行唯一表示
     *
     * 通过方法一中的递归序列化技巧，我们可以进一步进行优化。
     * 我们可以用一个三元组直接表示一棵子树，即 (x, l, r)(x,l,r)，它们分别表示：
     *      根节点的值为 x；
     *      左子树的序号为 l；
     *      右子树的序号为 r。
     * 这里的「序号」指的是：每当我们发现一棵新的子树，就给这棵子树一个序号，用来表示其结构。那么两棵树是重复的，当且仅当它们对应的三元组完全相同。
     * 针对三元组来生成序列化的结果(序列化的结果其实就是一颗树的hash值)，这样会比较短。
     * 使用「序号」的好处在于同时减少了时间复杂度和空间复杂度。方法一的瓶颈在于生成的序列会变得非常长，而使用序号替换整个左子树和右子树的序列，可以使得每一个节点只使用常数大小的空间。
     */
    // 以序号为Integer，TreeNode为根的树，序列化的结果是String
    private final Map<String, Pair<TreeNode, Integer>> seen2 = new HashMap<String, Pair<TreeNode, Integer>>();
    // 自增的序号
    private int index = 0;
    public List<TreeNode> findDuplicateSubtrees2(TreeNode root) {
        dfs2(root);
        return new ArrayList<>(repeat);
    }

    // 返回以node为根的树的序号
    private int dfs2(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int[] tri = new int[] {node.val, dfs2(node.left), dfs2(node.right)};
        // 可以将序列化的结果当成以node为根的树的hash值
        String serialization = Arrays.toString(tri);
        if(seen2.containsKey(serialization)) {
            Pair<TreeNode, Integer> pair = seen2.get(serialization);
            repeat.add(pair.getKey());
            return pair.getValue();
        } else {
            seen2.put(serialization, new Pair<>(node, ++index));
            return index;
        }
    }
}