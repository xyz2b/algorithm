package leetcode.p556;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public int nextGreaterElement(int n) {
        int t = n;
        List<Integer> list = new ArrayList<Integer>();
        // 将数字分解，数字的低位在List中也是低位（数字的个位，在list中就是第0位）
        while (true) {
            int i = t % 10;
            list.add(i);

            t = t / 10;

            if (t == 0) {
                break;
            }
        }

        // 找到第一个降序位置，降序: 当前元素值小于前一个元素值
        int p = -1;
        for(int i = 1; i < list.size(); i++) {
            if(list.get(i) < list.get(i - 1)) {
                p = i;
                break;
            }
        }

        if (p == -1) {
            return -1;
        }

        // 在第一个降序位置之前，找到第一个大于当前降序位置的元素，然后交换
        for (int i = 0; i < p; i++) {
            if (list.get(i) > list.get(p)) {
                int temp = list.get(i);
                list.set(i, list.get(p));
                list.set(p, temp);
                break;
            }
        }

        // 将第一个降序位置之前的元素翻转
        int j = p - 1, k = 0;
        while (k < j) {
            int temp = list.get(k);
            list.set(k, list.get(j));
            list.set(j, temp);

            j--;
            k++;
        }

        long rst = 0;
        for(int i = 0; i < list.size(); i++) {
            rst += list.get(i) * Math.pow(10, i);
        }

        // 判断交换后的值是否溢出，不是32位整数返回-1
        if (rst > Integer.MAX_VALUE) {
            return -1;
        }

        if (rst == n) {
            return -1;
        }

        return (int)rst;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.nextGreaterElement(2147483476));
        System.out.println(Integer.MAX_VALUE);
    }
}
