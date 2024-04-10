package leetcode.p1702;

class Solution {
    public String maximumBinaryString(String binary) {
        // 贪心
        // 从左往右遍历，先找到第一个0，然后再找到下一个0，这两个0中间的1可以通过将10->01，从而使这两个0相邻，比如 0110 -> 0011
        // 相邻的0可以通过00->10，使结果变大，0110 -> 0011 -> 1011
        // 上面的过程可以简化为 0110 -> 1011，即把第一个0的位置(i=0)变成1，第二个0的位置(j=3)变成1，第一个0的后一个位置(i+1=1)变为0
        // 直到字符串中没有第二个0，或者到达字符串结尾
        char[] s = binary.toCharArray();
        int n = binary.length();

        int j = 1;
        for (int i = 0; i < n; i++){
            if(s[i] == '0') {
                while (j < n) {
                    if (j > i && s[j] == '0') { // 有可能在遍历的过程中 j 会小于等于 i，此时需要先把j加到大于i，再做是否为0的判断
                        s[j] = '1';
                        s[i] = '1';
                        s[i + 1] = '0';
                        j++;
                        break;
                    } else {
                        j++;
                    }
                }
            } else {
                j++;
            }
        }

        return new String(s);
    }
}