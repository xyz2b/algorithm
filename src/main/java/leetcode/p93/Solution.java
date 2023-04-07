package leetcode.p93;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    // 暴力解法，遍历所有可能
    private List<String> rst = new ArrayList<>();
    public List<String> restoreIpAddresses(String s) {
        if(s.equals("")) return rst;
        address(s, 0, "", 0);
        return rst;
    }

    // 自顶向下的递归
    // ip中保存了s[0, index-1]拼装所得来的ip字符串
    // isIp保存了，已经拼接完成ip的第几部分，ip总共4部分
    // 获得digits[0,index]拼装得到的ip字符串
    public void address(String s, int index, String ip, int isIp) {
        // ip总共四部分，已经拼装完成四部分
        if(isIp == 4) {
            if(index >= s.length()) {   // 因为要用完字符串中所有的字符，所以如果ip拼完了4部分，但是s字符串中的字符没用完，那也是非法的ip
                rst.add(ip);
            }
            return;
        }

        int n = 0;
        // 因为ip中每一部分，最多是三位，所以这个循环最多循环3次
        while(index < s.length() && n >= 0 && n <= 255) {
            n = n * 10 + s.charAt(index) - '0';
            // 如果数字大于255，或者小于0，则是不合法的ip，就直接退出
            if(n < 0 || n > 255) {
                break;
            }

            // 如果遇到字符是0，则ip的这一部分只能是0，不能在扩展其他位
            if(n == 0) {
                String nIp = ip + n;
                // ip最后一部分，不需要在后面再加点了
                if(isIp + 1 < 4) {
                    nIp = nIp + ".";
                }
                address(s, index + 1, nIp, isIp + 1);
                break;
            }

            // 1 <= n <= 255
            String nIp = ip + n;
            // ip最后一部分，不需要在后面再加点了
            if(isIp + 1 < 4) {
                nIp = nIp + ".";
            }
            address(s, index + 1, nIp, isIp + 1);
            index++;
        }
    }

    public static void main(String[] args) {
        String s = "0000";
        Solution solution = new Solution();
        System.out.println(solution.restoreIpAddresses(s));
    }
}
