package leetcode.p779;

public class Solution {
    // 每一层第一个元素必然是0，因为顶层元素是0
    public int kthGrammar(int n, int k) {
        // 存储 当前层 决定 下一层第i个元素 的元素位置。位置索引从1开始（第n层第k个元素）
        int[] topLevelIndexes = new int[n];
        // 第n层的位置即k，置于索引0处。第1层的位置置于索引n-1处
        topLevelIndexes[0] = k;

        int topLevelIndex = k;
        int index = 1;
        for(int i = 0; i < n - 1; i++) {
            // 第n-1层中 决定 第n层第k个元素 的元素位置，其实就是找第n层第k个节点的父节点，一直往上找，直到找到根节点，topLevelIndexes数组中奇数记录的是第n层第k个节点的所有祖先节点，一直到根节点（记录的是每个祖先节点在当前层的索引位置）
            if(topLevelIndex % 2 == 0) {    // 第n层的位置索引是偶数，则第n-1层的索引位置是topLevelIndex / 2
                topLevelIndex = topLevelIndex / 2;
            } else { // 第n层的位置索引是奇数，则第n-1层的索引位置是topLevelIndex / 2 + 1
                topLevelIndex = topLevelIndex / 2 + 1;
            }
            topLevelIndexes[index] = topLevelIndex;
            index++;
        }

        // nums此时存储的是第1层的孩子节点的元素值，0索引为左孩子，1索引为右孩子
        int[] nums = new int[2];
        nums[0] = 0;
        nums[1] = 1;
        // num此时存储的是第1层的元素值
        int num = 0;
        for(int i = n - 1; i >= 0; i--) {   // 第一轮循环还是第一层，第二轮循环num才是第二层的元素值
            // 当前层节点 是 上一层节点的 左孩子 还是 右孩子（0左，1右）
            int j;
            if(topLevelIndexes[i] % 2 == 0) {   // 偶数位置就是取上层对应元素的右孩子
                j = 1;
            } else {    // 奇数位置就是取上层对应元素的左孩子
                j = 0;
            }
            // 当前层节点的元素值，根据是上一层节点的左孩子还是右孩子得到
            num = nums[j];
            // 设置当前节点左右孩子的元素值
            if(num == 1) {
                nums[0] = 1;
                nums[1] = 0;
            } else { // num == 0
                nums[0] = 0;
                nums[1] = 1;
            }
        }

        return num;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.kthGrammar(30,434991989));
    }
}
