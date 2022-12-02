package leetcode.p1769;

// 双重循环
public class Solution {
    public int[] minOperations(String boxes) {
        int[] rst = new int[boxes.length()];
        for(int i = 0; i < boxes.length(); i++) {
            for(int j = 0; j < boxes.length(); j++) {
                if(boxes.charAt(j) == '1') {
                    rst[i] += Math.abs(j - i);
                }
            }
        }
        return rst;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] rst = solution.minOperations("001011");
        for (int i : rst) {
            System.out.println(i);
        }
    }
}
