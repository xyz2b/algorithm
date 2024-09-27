package leetcode.p2516;

public class Solution {
    public int takeCharacters(String s, int k) {
        // 统计每种字符的数量cnts，表示取出的每种字符的个数
        // 双指针
        // l=r=0，r不断往右移动，r向右移动就表示要保留下的字符变多，所以要从cnts中减去对应字符数量
        // 1.如果移动过程中cnts中每一种字符个数都大于等于k，满足要求
        // 2.如果移动过程中cnts中存在一种字符个数小于k，则移动l，l向右移动就表示要取走的字符数变多，cnts中对应的字符个数加一，直到满足要求

        int[] cnts = new int[3];
        for(int i = 0; i < s.length(); i++) {
            cnts[s.charAt(i) - 'a']++;
        }

        int ret = Integer.MAX_VALUE;

        int l = 0, r = 0;
        while (r < s.length()) {
            cnts[s.charAt(r) - 'a']--;
            r++;

            for(int i = 0; i < cnts.length; i++) {
                if(cnts[i] < k) {
                    while (l < r && cnts[i] < k) {
                        cnts[s.charAt(l) - 'a']++;
                        l++;
                    }
                }
            }

            boolean all = true;
            for(int i = 0; i < cnts.length; i++) {
                if(cnts[i] < k) {
                    all = false;
                }
            }

            if(all) {
                int sum = 0;
                for(int i = 0; i < cnts.length; i++) {
                    sum += cnts[i];
                }
                ret = Math.min(ret, sum);
            }
        }

        return ret == Integer.MAX_VALUE ? -1 : ret;
    }

    public static void main(String[] args) {
        String s = "caccbbba";
        int k = 1;
        Solution solution = new Solution();
        System.out.println(solution.takeCharacters(s, k));
    }
}
