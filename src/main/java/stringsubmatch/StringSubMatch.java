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

        public static void main(String[] args) {
        String s = "dsadasd";
        String t = "dasd";
        StringSubMatch stringSubMatch = new StringSubMatch();
        System.out.println(stringSubMatch.search2(s, t));
    }
}
