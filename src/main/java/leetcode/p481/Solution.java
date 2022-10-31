package leetcode.p481;
public class Solution {
    // 按照题意那种形式进行分组
    // 双指针
    public int magicalString(int n) {
        if(n < 4) {
            return 1;
        }

        // 初始化字符串 s=122
        int[] str = new int[n];
        str[0] = 1;
        str[1] = 2;
        str[2] = 2;

        // 用指针 i 来指向现在需要构造的对应的组的大小，用指针 j 来指向现在需要构造的对应组的位置，此时 i=2，j=3。
        // 因为相邻组中的数字一定不会相同，所以我们可以通过 j 的前一个位置的数来判断当前需要填入的组中的数字。
        // 每个分组中的数字都是相同的。
        int i = 2, j = 3, rst = 1;
        while (j < n) {
            // 当前分组应该写入的数字，与前面一个数字相反
            int num = str[j - 1] == 1 ? 2 : 1;

            int size = 0;
            // str[i]就是当前分组元素的个数
            while (j < n && size < str[i]) {
                str[j] = num;
                if(num == 1) {
                    rst++;
                }
                j++;
                size++;
            }
            i++;
        }

        return rst;
    }

    public static void main(String[] args) {
        int n = 7;
        Solution solution = new Solution();
        System.out.println(solution.magicalString(n));
    }
}
