package leetcode.p145;

import leetcode.p144.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> rst = new ArrayList<>();
        post(root, rst);
        return rst;
    }

    private void post(TreeNode node, List<Integer> rst) {
        if(node == null) return;

        post(node.left, rst);
        post(node.right, rst);
        rst.add(node.val);
    }

    // 非递归
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> rst = new ArrayList<>();
        if (root == null) return rst;

        Stack<Command> stack = new Stack<>();
        stack.push(new Command(Command.CMD_GO, root));
        while (!stack.isEmpty()) {
            Command cmd = stack.pop();

            if(cmd.cmd == Command.CMD_PRINT) {
                rst.add(cmd.node.val);
            } else { // cmd.cmd == Command.CMD_GO
                stack.push(new Command(Command.CMD_PRINT, cmd.node));
                if(cmd.node.right != null) stack.push(new Command(Command.CMD_GO, cmd.node.right));
                if(cmd.node.left != null) stack.push(new Command(Command.CMD_GO, cmd.node.left));
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
