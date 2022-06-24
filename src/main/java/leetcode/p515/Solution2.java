package leetcode.p515;

import java.util.ArrayList;
import java.util.List;

public class Solution2 {
    List<Integer> rst = new ArrayList<>();

    // 深度优先搜索
    public List<Integer> largestValues(TreeNode root) {
        if(root == null) {
            return rst;
        }

        dfs(root, 0);

        return rst;

    }

    public void dfs(TreeNode node, int curHeight) {
        // 判断当前遍历的元素是否为新的一层的第一个元素，如果是，将当前元素加入到结果集
        if(curHeight == rst.size()) {   // 如开始遍历第0层，rst此时是空的，size也为0，相等就是开始遍历新的一层的意思；如开始遍历第1层，rst此时只有第0层的元素，size为1
            // 处于新的一层
            rst.add(node.val);
        } else {    // 如果当前遍历元素不是新的一层的第一个元素，那就从当前结果集中取出当前层目前的最大元素，进行比较，如果当前遍历元素比较大，就替换
            // 还在当前层，以当前层数为索引在结果集中对应的元素就是目前当前层最大的元素
            rst.set(curHeight, Math.max(rst.get(curHeight), node.val));
        }

        if(node.left != null) {
            dfs(node.left, curHeight+1);
        }

        if(node.right != null) {
            dfs(node.right, curHeight+1);
        }
    }
}
