package leetcode.p1051;

public class Solution2 {
    // 桶排序
    /**
     * 比如原始身高数组如下
     * [1, 1, 4, 2, 1, 3]
     * 统计每个身高的数量数量的数组如下（该数组下标就是身高，对应的值就是该身高的学生数量，身高从1开始，所以该数组0索引无用）
     * [0, 3, 1, 1, 1]
     * 表示身高为1的学生数量是3，身高为2的学生数量是1，身高为3的学生数量是1，身高为4的学生数量是1
     * 可以推导出排序后的数组应该是下面这种组合
     * 3个值为1的元素，1个值为2的元素，1个值为3的元素，1个值为4的元素
     * 即[1, 1, 1, 2, 3, 4]
     * */
    public int heightChecker2(int[] heights) {
        int max = getMax(heights);

        // 统计各个身高的学生数量
        // 身高从1开始，数组的元素数量就是 最大身高+1
        // cnt数组下标就是身高，对应的值就是该身高的学生数量
        int cnt[] = new int[max + 1];
        for(int i = 0; i < heights.length; i++) {
            cnt[heights[i]]++;
        }

        int index = 0;
        int result = 0;
        // 遍历统计身高学生数量的数组
        for (int i = 1; i < cnt.length; i ++) {
            // 每个身高的学生数量
            for (int j = 1; j <= cnt[i]; j++) {
                // 与原数组对比
                if (heights[index] != i) {
                    result++;
                }
                index++;
            }
        }
        return result;
    }

    private int getMax(int[] data) {
        int max = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i] > max) {
                max = data[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] heights = {1, 1, 4, 2, 1, 3};
        System.out.println(solution.heightChecker(heights));
    }
}
