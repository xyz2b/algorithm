package leetcode.p513;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    // 因为要找到最后一层的最左边的元素，所以想到了树的层序遍历
    // 每次遍历一层的开始时，都记录下这个元素，这个元素就是当前层最左边的元素。当最后一层遍历完了，这个元素记录的就是最后一层最左边的元素
    // 怎么知道一层遍历完了？
    //      通过遍历上一层时记录的本层元素的总数量，即本层待遍历的元素数量。（遍历上一层时将上一层元素的左右孩子(上一层元素的左右孩子即下一层元素)的总数量进行计数）
    //      然后本层每遍历完一个元素，本层待遍历元素的数量就减一，当本层待遍历元素的数量为0时，当前层就遍历完毕，继续遍历下一层
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        int curLevelEleCount = 0;   // 本层待遍历元素个数
        int nextLevelEleCount = 0;  // 下一层待遍历元素个数
        int curLevelLeftEle = 0;    // 当前层最左侧的元素
        int flag = 0;               // 当前遍历的元素 是否是 新的一层最左边

        queue.add(root);
        curLevelEleCount++;
        curLevelLeftEle = root.val;

        while(!queue.isEmpty()) {
            TreeNode node = queue.remove();
            if(flag == 1) { // flag=1 代表当前从队列中取出的元素，即当前遍历的元素，就是当前层最左边的元素，需要记录下来
                curLevelLeftEle = node.val;
                flag = 0;
            }
            curLevelEleCount--; // 从队列中取出了一个当前层待遍历元素，所以本层待遍历元素减1

            if(node.left != null) {
                queue.add(node.left);
                nextLevelEleCount++;    // 将当前层遍历元素的孩子节点加入到队列中，所以下一层待遍历元素要加1
            }
            if(node.right != null) {
                queue.add(node.right);
                nextLevelEleCount++;    // 将当前层遍历元素的孩子节点加入到队列中，所以下一层待遍历元素要加1
            }

            if(curLevelEleCount == 0){  // 如果当前层待遍历元素为0，说明当前层遍历完毕，当前层的元素已经从队列中全部取出，之后从队列中取出的元素就是下一层的元素
                curLevelEleCount = nextLevelEleCount;   // 因为要开始遍历下一层了，所以将下一层待遍历元素数量 设置为当前层要遍历的元素数量
                nextLevelEleCount = 0;  // 清空下一层待遍历元素数量
                flag = 1;   // 设置flag=1，代表队列中下一个待遍历的元素就是下一层最左边的元素
            }
        }
        return curLevelLeftEle;
    }
}
