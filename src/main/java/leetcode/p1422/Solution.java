package leetcode.p1422;

public class Solution {
    public int maxScore(String s) {
        int[] sum = new int[s.length()+1];

        // 存储当前遍历位置（包含当前位置）之前有多少个0
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '0') {
                sum[i + 1] = sum[i] + 1;
            } else {
                sum[i + 1] = sum[i];
            }
        }

        // 统计当前遍历位置前面0的数量和后面1的数量的和
        // 前面0数量的和(包含当前元素)就等于sum中对应位置的值(sum[i])
        // 后面1数量的和(不包含当前元素)就等于(后面元素的个数(总长度减去当前遍历位置) - 后面0的数量(最后一个元素(包含当前元素)前面0的个数(sum[sum.length-1]) - 当前遍历元素(包含当前元素)前面0的个数( sum[i])))
        int rst = 0;
        // 因为必须分割成两部分，所以从字符串的第一个位置开始往后遍历（至少会分割出去第一个字符），并且只能遍历到最后一个位置之前的一个位置（至少会分割出去最后一个字符）
        for(int i = 1; i < sum.length - 1; i++) {
            int beforeZeroCount = sum[i];
            int afterOneCount = (s.length() - i) - (sum[sum.length-1] - sum[i]);
            rst = Math.max(rst , beforeZeroCount + afterOneCount);
        }

        return rst;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxScore("0100"));
    }
}
