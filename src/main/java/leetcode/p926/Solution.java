package leetcode.p926;

// 前缀和
public class Solution {
    public int minFlipsMonoIncr(String s) {
        // 计算字符串中，在每个位置之前有多少个1
        // 这样就可以同时计算出，在字符串某个位置上，该位置左边（包含该位置）有多少个1，该位置右边（不包含该位置）有多少个1，该位置右边（不包含该位置）有多少个0
        // 反转：就是将某个位置左边的1反转成0，将该位置右边的0反转成1，然后看哪个位置两边的翻转次数最小

        int length = s.length();
        int sumOneBefore[] = new int[length + 1];

        // 计算每个位置之前（包含该位置）上有多少个1
        // sumOneBefore数组从第二个位置开始存储，字符串中第一个字符之前（包含该位置）上有多少个1，依次类推（sumOneBefore数组第一个位置初始化为0即可）
        for (int i = 0; i < length; i++) {
            sumOneBefore[i + 1] = sumOneBefore[i] + (s.charAt(i) == '1' ? 1 : 0);
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i <= length; i++) {
            //                  i=0时，对应到字符串中的-1的位置；i=1，对应到字符串中的0的位置
            //                  i=0时，i-1=-1的意思就是左边没有元素，右边是整个字符串
            //                  i=length时，i-1=length-1的意思就是左边是整个字符串，右边没有元素
            //                  i-1位置之前（包含该位置）有多少个1      i-1位置之后总共有多少个字符            i-1位置之后（不包含i-1位置） 到 字符串末尾的位置之间有多少个1
            ans = Math.min(ans,   sumOneBefore[i]                 +   ((length - i)               - (sumOneBefore[length] - sumOneBefore[i])));
        }

        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "00011000";
        System.out.println(solution.minFlipsMonoIncr(s));
        s = "00110";
        System.out.println(solution.minFlipsMonoIncr(s));
    }
}
