package leetcode.p745;

class WordFilter2 {
    private String[] words;

    public WordFilter2(String[] words) {
        this.words = words;
    }

    public int f(String pref, String suff) {
        int rst = -1;

        for (int i = 0; i < words.length; i++) {
            String word = words[i];

            // 前缀后缀指针
            int p = 0;
            int s = suff.length() - 1;

            // 被遍历字符串的左右索引
            int l = 0;
            int r = word.length() - 1;
            // l < r，被遍历字符串word的左右指针不能交叉，不然就不是前缀后缀了
            // 不能遍历超出pref和suff字符串
            while (l <= r && p <= pref.length() - 1 && s >= 0) {
                if (word.charAt(l) == pref.charAt(p)) {
                    p++;
                    l++;
                } else {
                    break;
                }
                if (word.charAt(r) == suff.charAt(s)) {
                    s--;
                    r--;
                } else {
                    break;
                }
            }

            if (p == pref.length() && s == -1) {
                rst = Math.max(rst, i);
            }
        }
        return rst;
    }

    public static void main(String[] args) {
        WordFilter2 wordFilter2 = new WordFilter2(new String[] {"wbyh","wbyh","yrue","yrue","wbyh","wbyh","yrue","wbyh","wbyh","wbyh"});
        System.out.println(wordFilter2.f("y","e"));
    }
}
