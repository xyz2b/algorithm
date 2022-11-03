package stringsubmatch;

// 在s中查找t子串，并返回第一个找到的索引位置
public class StringSubMatch {
    // 暴力搜索法
    public int search(String s, String t) {
        if(s.length() < t.length()) {
            return -1;
        }
        // 空串
        if(t.length() <= 0) {
            return 0;
        }

        for(int i = 0; i + t.length() - 1 <= s.length() - 1; i++) {

            if(equal(s, i, t.length() - 1, t)) {
                return i;
            }
        }
        return -1;
    }

    private boolean equal(String s, int l, int r, String t) {
        for(int i = 0; i < t.length(); i++) {
            if(t.charAt(i) != s.charAt(i + l)) {
                return false;
            }
        }
        return true;
    }

    // 滚动hash字符串匹配
    public int search2(String s, String t) {
        if(s.length() < t.length()) {
            return -1;
        }
        // 空串
        if(t.length() <= 0) {
            return 0;
        }

        // 数据宽度，因为ASCII码字符只有256种可能，可以看出进制，如果数据都是十进制的数字，那么B就是10
        int B = 256;
        long MOD = (long) 1e9 + 7;

        // 先计算t字符串的hash值
        long thash = 0;
        for(int i = 0; i < t.length(); i++) {
            thash = (thash * B + t.charAt(i)) % MOD;
        }

        // 再计算s字符串最前面t.length() - 1个字符组成的子串的hash值
        long hash = 0;
        for(int i = 0; i < t.length() - 1; i++) {
            hash = (hash * B + s.charAt(i)) % MOD;
        }

        // 计算长度为t.length()第一个字符的阶数
        long P = 1;
        for(int i = 0; i < t.length() - 1; i++) {
            P = (P * B) % MOD;
        }

        // 滚动
        // i是s中长为t.length()的子串的最后一个字符
        for(int i = t.length() - 1; i < s.length() ; i++) {
            // 计算s子串最后加上第t.length()个字符组成的子串的hash值
            // 类比十进制数字，1234，后面再加上一个数字5，12345 = 1234 * 10 + 5
            hash = (hash * B + s.charAt(i)) % MOD;

            // 此时计算出来的hash就是s中长度为t.length的子串的hash值，
            // 此时只需要比较当前子串的hash值与t的hash值是否相同，如果相同说明两个字符串可能相同，还需要通过一位位比较得到最终确定解果，因为可能会产生hash冲突，仅凭hash值决定不了是否真的相同
            if(hash == thash && equal(s, i - t.length() + 1, i, t)) {
                return i - t.length() + 1;
            }

            // 计算s子串去除第一个字符之后形成的子串的hash值
            // 类比十进制数字，12345，去掉第一个数字，2345 = 12345 - 1 * 10^4
            // +MOD 是因为前面的减法是两个取模之后的数字的减法，可能为负，加MOD之后变成正（比如 12 % 10 = 2，7 % 10 = 7，2 - 7 = -5）
            // %MOD 是因为前面两个的减法可能是正的，如果再加上一个MOD，就超出了MOD的范围，就再取一个模，这样不管减法结果是正还是负取模之后的结果也不会变（比如 -5 + 10 = 5 % 10 = 5，5 + 10 = 15 % 10 = 5）
            hash = ((hash - (s.charAt(i - t.length() + 1) * P) % MOD) + MOD) % MOD;
        }

        return -1;
    }

    // kmp
    public int search3(String s, String t) {
        if(s.length() < t.length()) {
            return -1;
        }
        // 空串
        if(t.length() <= 0) {
            return 0;
        }

        // 求解目标字符串的lps数组
        int[] lps = getLps(t);

        // i是源字符s的索引，该索引不会倒退，要么是维持不变，要么是向前增长
        // j是目标字符串t的索引，该索引会来回跳跃
        // 如果i和j字符相同，就增加i和j索引
        // 每次遇到i和j不相同的字符，就保持i不变，j变为 [0...j-1]最长border 的后一个索引，即[0...j-1]最长border的长度，由lps数组得到
        int i = 0, j = 0;

        // 如果s字符串匹配完了，t字符串却没有匹配完，说明s中不存在t的子串
        while (i < s.length() && j < t.length()) {
            if(s.charAt(i) == t.charAt(j)) {
                i++;
                j++;

                // 如果t字符串匹配完了，说明得出了结果，s中存在t的子串，此时i索引位于s中匹配到的子串的后一个位置，那么匹配到的子串的起始位置就是i-t.length
                if(j == t.length()) {
                    return i - t.length();
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
    private int[] getLps(String t) {
        // 求解目标字符串的lps数组
        // lps[i] = t[0...i]的最长border的长度
        // (一个子串即是该字符串的前缀又是后缀，该子串既不能是空串也不是是整个字符串，border就是该子串，比如abcdab，ab即是前缀又是后缀，ab就是该字符串的border)
        int[] lps = new int[t.length()];
        lps[0] = 0; // 第一个字符没有border，一个字符的子串要么是空要么是它自己都不符合border的定义，所以第一个字符的最长的border长度为0

        // 比较 i位置 和 [0...i-1]最长border长度的后一个字符，即lps[i-1]位置
        for(int i = 1; i < t.length(); i++) {
            // [0...i-1]最长border的长度为lps[i-1]，这个border的最后一个字符索引为lps[i-1] - 1，所以这个border后一个字符索引是lps[i-1]
            //  所以该border的最长border应该是lps[lps[i-1]-1]，这个次长border后一个字符索引是lps[lps[i-1]-1]
            // 需要比较的是当前i和最长border后一个字符
            // a就表示最长border的后一个字符的索引
            int a = lps[i-1];
            while (a > 0 && t.charAt(i) != t.charAt(a)) {  // 一直找次长border，直到找到一个次长border后面一个字符和当前遍历的i处字符相同
                a = lps[a - 1];
            }
            // 上面循环退出可能是遇到t.charAt(i) == s.charAt(a)，也可能是a == 0了
            // 如果a == 0说明[0...i]没有border，长度为0
            // 如果t.charAt(i) == s.charAt(a)，说明[0...i]有border，长度为a + 1
            if(t.charAt(i) == t.charAt(a)) lps[i] = a + 1;

        }
        return lps;
    }

    public static void main(String[] args) {
        String s = "aabaaeaabaaca";
        String t = "aabaacdd";
        StringSubMatch stringSubMatch = new StringSubMatch();
        System.out.println(stringSubMatch.search3(s, t));
    }
}
