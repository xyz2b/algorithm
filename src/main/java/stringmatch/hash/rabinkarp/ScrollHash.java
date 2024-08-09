package stringmatch.hash.rabinkarp;

public class ScrollHash {
    public int substring(String s, String t) {
        if(s.length() < t.length()) return -1;
        if(t.length() == 0) return 0;

        int B = 256;
        long MOD = (long) (1e9 + 7);
        int len = t.length();

        long tHash = 0;
        for(int j = 0; j < len; j++) {
            tHash = (tHash * B + t.charAt(j)) % MOD;
        }

        // 预处理，先计算出来 B ^ (len-1)
        long P = 1;
        for(int k = 0; k < len - 1; k++) {
            P = P * B % MOD;
        }

        long hash = 0;
        for(int i = 0; i < len - 1; i++) {
            hash = (hash * B + s.charAt(i)) % MOD;
        }

        for(int i = len - 1; i < s.length(); i++) {
            // 在末尾加新字符：(hashcode * B + newchar) % M
            hash = (hash * B + s.charAt(i)) % MOD;

            if(tHash == hash && s.substring(i - (len-1), i + 1).equals(t)) {
                return i - (len-1);
            }

            // 在开头减字符：(hashcode - oldchar * (B ^ (len - 1)) % MOD + MOD) % MOD
            hash = (hash - (s.charAt(i - (len-1)) * P % MOD + MOD)) % MOD;
        }

        return -1;
    }
}
