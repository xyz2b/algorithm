package leetcode.p1764;

public class Solution {
    // 使用字符串KMP匹配算法，来解决子数组匹配问题
    // group[i]是子数组，nums是源数组
    // 去nums数组中匹配是否存在 group[i] 的子数组，可以使用KMP子串匹配算法
    public boolean canChoose(int[][] groups, int[] nums) {
        int l = 0, r = nums.length - 1;
        for(int i = 0; i < groups.length; i++) {
            // nums数组遍历完了，还没找到所有的groups中的子数组
            if(l > r) {
                return false;
            }

            int index = search(nums, l, r, groups[i]);
            // nums数组中没有groups[i]的子数组
            if(index == -1) {
                return false;
            }

            // nums数组中找到了group[i]的子数组，更新l，使其执行匹配到的子数组的后面一位，然后继续从nums中该位置往后匹配下一个子数组group[i+1]
            l = index + groups[i].length;
        }

        return true;
    }

    // kmp
    // 在nums [r,l] 之间搜寻子数组 t
    public int search(int[] nums, int l, int r, int[] t) {
        if(r - l + 1 < t.length) {
            return -1;
        }
        // 空串
        if(t.length <= 0) {
            return 0;
        }

        // 求解目标字符串的lps数组
        int[] lps = getLps(t);

        // i是源字符s的索引，该索引不会倒退，要么是维持不变，要么是向前增长
        // j是目标字符串t的索引，该索引会来回跳跃
        // 如果i和j字符相同，就增加i和j索引
        // 每次遇到i和j不相同的字符，就保持i不变，j变为 [0...j-1]最长border 的后一个索引，即[0...j-1]最长border的长度，由lps数组得到
        int i = l, j = 0;

        // 如果s字符串匹配完了，t字符串却没有匹配完，说明s中不存在t的子串
        while (i <= r && j < t.length) {
            if(nums[i] == t[j]) {
                i++;
                j++;

                // 如果t字符串匹配完了，说明得出了结果，s中存在t的子串，此时i索引位于s中匹配到的子串的后一个位置，那么匹配到的子串的起始位置就是i-t.length
                if(j == t.length) {
                    return i - t.length;
                }
            } else if(j > 0) { // 如果j不在t的开头，就保持i不变，j变为[0...j-1]最长border的长度
                j = lps[j - 1];
            } else {    // j == 0，如果j等于0，说明i和t开头就不相等，此时需要继续比较i+1和t
                i++;
            }
        }

        return -1;
    }

    // 求解lps数组
    private int[] getLps(int[] t) {
        // 求解目标字符串的lps数组
        // lps[i] = t[0...i]的最长border的长度
        // (一个子串即是该字符串的前缀又是后缀，该子串既不能是空串也不是是整个字符串，border就是该子串，比如abcdab，ab即是前缀又是后缀，ab就是该字符串的border)
        int[] lps = new int[t.length];
        lps[0] = 0; // 第一个字符没有border，一个字符的子串要么是空要么是它自己都不符合border的定义，所以第一个字符的最长的border长度为0

        // 比较 i位置 和 [0...i-1]最长border长度的后一个字符，即lps[i-1]位置
        for(int i = 1; i < t.length; i++) {
            // [0...i-1]最长border的长度为lps[i-1]，这个border的最后一个字符索引为lps[i-1] - 1，所以这个border后一个字符索引是lps[i-1]
            //  所以该border的最长border应该是lps[lps[i-1]-1]，这个次长border后一个字符索引是lps[lps[i-1]-1]
            // 需要比较的是当前i和最长border后一个字符
            // a就表示最长border的后一个字符的索引
            int a = lps[i-1];
            while (a > 0 && t[i] != t[a]) {  // 一直找次长border，直到找到一个次长border后面一个字符和当前遍历的i处字符相同
                a = lps[a - 1];
            }
            // 上面循环退出可能是遇到t.charAt(i) == s.charAt(a)，也可能是a == 0了
            // 如果a == 0说明[0...i]没有border，长度为0
            // 如果t.charAt(i) == s.charAt(a)，说明[0...i]有border，长度为a + 1
            if(t[i] == t[a]) lps[i] = a + 1;

        }
        return lps;
    }

    public static void main(String[] args) {
        int[][] groups = new int[][] {{1,-1,-1},{3,-2,0}};
        int[] nums = new int[] {1,-1,0,1,-1,-1,3,-2,0};
        Solution solution = new Solution();
        System.out.println(solution.canChoose(groups, nums));
    }
}
