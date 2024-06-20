package leetcode.p2748;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {
    public int countBeautifulPairs(int[] nums) {
        int ret = 0;
        for(int i = 0; i < nums.length - 1; i++) {
            while (nums[i] >= 10) {
                nums[i] = nums[i] / 10;
            }
            for(int j = i + 1; j < nums.length; j++) {
                if(gcd(nums[i], nums[j] % 10) == 1) {
                    ret++;
                }
            }
        }
        return ret;
    }

    public int countBeautifulPairs2(int[] nums) {
        int ret = 0;
        Map<Integer, Set<Integer>> map = new HashMap<>();
        // 题目的意思是只有一位数字的互质，即0-9的互质，可以直接给计算出0-9的互质结果，存储起来，然后后续直接查表即可
        // 1和任何数字都互质，0不与任何数字互质
        for(int i = 2; i <= 8; i++) {
            for(int j = i + 1; j <= 9; j++) {
                if(gcd(i, j) == 1) {
                    map.putIfAbsent(i, new HashSet<>());
                    map.get(i).add(j);
                    map.putIfAbsent(j, new HashSet<>());
                    map.get(j).add(i);
                }
            }
        }

        for(int i = 0; i < nums.length - 1; i++) {
            while (nums[i] >= 10) {
                nums[i] = nums[i] / 10;
            }
            if(nums[i] == 1) {
                ret += (nums.length - i - 1);
                continue;
            }
            for(int j = i + 1; j < nums.length; j++) {
                int jn = nums[j] % 10;
                if(jn == 1) {
                    ret++;
                } else if(jn != 0) {
                    if(map.get(nums[i]).contains(jn)) {
                        ret++;
                    }
                }
            }

        }
        return ret;
    }

    public int countBeautifulPairs3(int[] nums) {
        int res = 0;
        int[] cnt = new int[10];
        for (int x : nums) {
            // 每个数值最后一个数字(高位) 只能是 1 - 9，所以统计每个数值最后一个数字(高位)出现的次数
            // 然后判断每个数值第一个数字(低位)是否和1-9互质即可，如果互质，就将结果加上1-9出现的次数即可
            for (int y = 1; y <= 9; y++) {
                if (gcd(x % 10, y) == 1) {
                    res += cnt[y];
                }
            }
            while (x >= 10) {   // 求出每个数值最后一个数字
                x /= 10;
            }
            cnt[x]++;   // 统计每个数值最后一个数字(高位)出现的次数
        }
        return res;
    }

    public int gcd (int a,int b) {
        if(b == 0)
            return a;
        else
            return gcd(b,a % b);
    }

    public static void main(String[] args) {
        int[] nums = {31,25,72,79,74};
        Solution solution = new Solution();
        System.out.println(solution.countBeautifulPairs(nums));
    }
}
