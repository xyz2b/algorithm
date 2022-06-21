package leetcode.p1108;

public class Solution {
    public String defangIPaddr(String address) {
        char[] str = new char[address.length() + 3 * 2];

        int index = 0;
        for(int i = 0; i < address.length(); i ++) {
            char c = address.charAt(i);
            if (c == '.') {
                str[index] = '[';
                str[++index] = '.';
                str[++index] = ']';
            } else {
                str[index] = c;
            }
            index++;
        }

        return String.valueOf(str);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String address = "255.100.50.0";
        System.out.println(solution.defangIPaddr(address));
    }
}
