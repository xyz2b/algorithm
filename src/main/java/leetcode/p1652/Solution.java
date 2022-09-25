package leetcode.p1652;

import java.util.Arrays;

public class Solution {
    public int[] decrypt(int[] code, int k) {
        if(k == 0) {
            Arrays.fill(code, 0);
            return code;
        }

        if(k == code.length - 1) {
            int sum = Arrays.stream(code).sum();

            for(int i = 0; i < code.length; i++) {
                code[i] = sum - code[i];
            }

            return code;
        }

        int[] rst = Arrays.copyOf(code, code.length);
        for(int i = 0; i < code.length; i++) {
            int sum = 0;
            boolean flag = k > 0;
            for(int j = 1; j <= Math.abs(k); j++) {
                if(flag) { // k为正
                    sum += code[(i + j) % code.length];
                } else {    // k为负
                    sum += code[((i - j) + code.length) % code.length];
                }
            }
            rst[i] = sum;
        }

        return rst;
    }

    public static int[] decrypt2(int[] code, int k) {
        if(k == 0) {
            Arrays.fill(code, 0);
            return code;
        }

        if(k == code.length - 1) {
            int sum = Arrays.stream(code).sum();

            for(int i = 0; i < code.length; i++) {
                code[i] = sum - code[i];
            }

            return code;
        }

        int[] rst = Arrays.copyOf(code, code.length);

        // 替换第一个元素
        int sum = 0;
        boolean flag = k > 0;
        k = Math.abs(k);
        for(int i = 1; i <= k; i++) {
            if(flag) { // k为正
                sum += code[(0 + i) % code.length];
            } else {    // k为负
                sum += code[((0 - i) + code.length) % code.length];
            }
        }
        rst[0] = sum;

        // 滑动窗口
        for(int i = 1; i < code.length; i++) {
            if(flag) { // k为正，后面一个元素需要替换成的元素 = 前一个元素替换之后的元素 - 当前元素 + 当前元素k区间内的最后一个元素
                rst[i] = rst[i-1] - code[i] + code[(i + k) % code.length];
            } else {    // k为负，后面一个元素需要替换成的元素 = 前一个元素替换之后的元素 - 前一个元素k区间内的最后一个元素 + 前一个元素
                rst[i] = rst[i-1] - code[((i - 1 - k) + code.length) % code.length] + code[i - 1];
            }
        }

        return rst;
    }

    public static void main(String[] args) {
        int[] code = new int[] {5,2,2,3,1};
        int k = 3;
        Solution solution = new Solution();
        int[] rst = Solution.decrypt2(code, k);
        for(int i : rst) {
            System.out.println(i);
        }
    }
}
