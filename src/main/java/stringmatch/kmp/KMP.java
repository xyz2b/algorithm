package stringmatch.kmp;

public class KMP {

    public int substring(String s, String t) {
        int[] lps = getLps(t);

        int i = 0, j = 0;
        while (i < s.length()) {
            if(s.charAt(i) == s.charAt(j)) {
                i++;
                j++;
                if(j == t.length()) return i - t.length();
            } else {
                if(j == 0) {
                    i++;
                } else {
                    j = lps[j - 1];
                }
            }
        }

        return -1;
    }


    private int[] getLps(String t) {
        int[] lps = new int[t.length()];

        for(int i = 1; i < t.length(); i++) {
            int a = lps[i-1];
            while (a > 0 && t.charAt(i) != t.charAt(a)) {
                a = lps[a - 1];
            }
            if(t.charAt(i) == t.charAt(a)) lps[i] = a + 1;
        }

        return lps;
    }
}
