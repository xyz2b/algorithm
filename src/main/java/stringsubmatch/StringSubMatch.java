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

    public static void main(String[] args) {
        String s = "dsadasd";
        String t = "dacs";
        StringSubMatch stringSubMatch = new StringSubMatch();
        System.out.println(stringSubMatch.search(s, t));
    }
}
