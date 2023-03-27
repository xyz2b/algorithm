package leetcode.p144;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> rst = new ArrayList<>();
        pre(root, rst);
        return rst;
    }

    private void pre(TreeNode node, List<Integer> rst) {
        if (node == null) return;

        rst.add(node.val);
        pre(node.left, rst);
        pre(node.right, rst);
    }

    // 非递归
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> rst = new ArrayList<>();
        if (root == null) return rst;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();

            rst.add(node.val);
            // 反向
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }

        return rst;
    }

    // 非递归
    public List<Integer> preorderTraversal3(TreeNode root) {
        List<Integer> rst = new ArrayList<>();
        if (root == null) return rst;

        Stack<Command> stack = new Stack<>();
        stack.push(new Command(Command.CMD_GO, root));
        while (!stack.isEmpty()) {
            Command cmd = stack.pop();

            if(cmd.cmd == Command.CMD_PRINT) {
                rst.add(cmd.node.val);
            } else { // cmd.cmd == Command.CMD_GO
                if(cmd.node.right != null) stack.push(new Command(Command.CMD_GO, cmd.node.right));
                if(cmd.node.left != null) stack.push(new Command(Command.CMD_GO, cmd.node.left));
                stack.push(new Command(Command.CMD_PRINT, cmd.node));
            }
        }

        return rst;
    }
}

class Command {
    public static final int CMD_PRINT = 0;
    public static final int CMD_GO = 1;

    int cmd;
    TreeNode node;

    public Command(int cmd, TreeNode node) {
        this.cmd = cmd;
        this.node = node;
    }
}