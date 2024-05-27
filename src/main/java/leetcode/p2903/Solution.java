package leetcode.p2903;

public class Solution {
    // 一遍遍历，只看最大值和最小值即可，如果最大值和最小值之间的valueDifference都达不到要求，那就没有达到要求的了
    public int[] findIndices(int[] nums, int indexDifference, int valueDifference) {
        int maxIndex = 0;
        int minIndex = 0;

        // 不妨设 j≥i。这样，为了满足下标条件，j的取值范围为 [indexDifference,n−1]。
        // 对于某个固定的 j，i 的取值范围为 [0,j−indexDifference]。随着 j的不断增大，i 的取值范围是不断增大的。
        // 而在满足下标条件之外，为了满足 ∣nums[i]−nums[j]∣>=valueDifference，我们只需要记录 nums[i] 的最大值和最小值即可。
        // 如果 nums[i] 的最大值和最小值都不能满足第二个条件，那么其他值也不能满足条件。
        // 在遍历过程中，如果满足条件则返回 [i,j]。如果遍历完成后仍未找到满足条件的下标对则返回 [−1,−1]。
        for(int j = indexDifference; j < nums.length; j++) {
            int i = j - indexDifference;
            if(nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
            if(Math.abs(nums[j] - nums[maxIndex]) >= valueDifference) {
                return new int[] {j, maxIndex};
            }

            if(nums[i] < nums[minIndex]) {
                minIndex = i;
            }
            if(Math.abs(nums[j] - nums[minIndex]) >= valueDifference) {
                return new int[] {j, minIndex};
            }
        }

        return new int[] {-1, -1};
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        int indexDifference = 2;
        int valueDifference = 4;
        Solution solution = new Solution();
        System.out.println(solution.findIndices(nums, indexDifference, valueDifference)[0]);
        System.out.println(solution.findIndices(nums, indexDifference, valueDifference)[1]);
    }
}
