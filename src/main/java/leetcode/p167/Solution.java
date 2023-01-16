package leetcode.p167;

public class Solution {
    // 双指针碰撞（对撞指针）
    // 一个指针指向数组开头（左指针），一个指针指向数组末尾（右指针）
    // 如果当两个值的和大于target，说明要减少和的大小，因为数组是有序的，只能将右指针往左移（减少右指针的值，即右指针左移，如果左指针右移会大于target），才能减少和
    // 如果当两个值的和小于target，说明要增大和的大小，因为数组是有序的，只能将左指针往右移（增大左指针的值，即左指针右移，如果右指针左移会小于target），才能增加和
    // 如果当两个指针相遇还没有解，则说明无解
    public int[] twoSum(int[] numbers, int target) {
        int l = 0, r = numbers.length - 1;
        int[] rst = new int[2];
        while (l < r) {
            if(numbers[l] + numbers[r] == target) {
                rst[0] = l + 1;
                rst[1] = r + 1;
                return rst;
            } else if (numbers[l] + numbers[r] > target) {
                r--;
            } else { // numbers[l] + numbers[r] < target
                l++;
            }
        }

        return rst;
    }

    public static void main(String[] args) {
        int[] numbers = new int[] {-1,0};
        int target = -1;
        Solution solution = new Solution();
        int[] rst = solution.twoSum(numbers, target);
        System.out.println("[" + rst[0] + ", " + rst[1] + "]");
    }
}
