package leetcode.p870;

import java.util.Arrays;

class Solution {
    public int[] advantageCount(int[] nums1, int[] nums2) {
        int n = nums1.length;
        Integer[] idx1 = new Integer[n];
        Integer[] idx2 = new Integer[n];
        for (int i = 0; i < n; ++i) {
            idx1[i] = i;
            idx2[i] = i;
        }
        // 使用源数组的索引进行排序，这样就可以在排序后的数组中对应到源数组中对应索引位置的元素
        /**
         * 我们首先分别将数组nums1和nums2进行倒序排序，随后只需要不断考虑这两个数组的首个元素：
         *   如果 nums1 的首个元素大于 nums2 的首个元素，那么就将它们在答案中对应起来，同时从数组中移除这两个元素，并增加一点「优势」；
         *   如果 nums1 的首个元素小于等于 nums2 的首个元素，那么移除 nums1 的首个元素。
         * 当 nums1 中没有元素时，遍历结束。
         *
         * 这样做的正确性在于：
         *  对于第一种情况，由于num1是有序的，那么num1的任意元素大于nums2的首个元素
         *      如果我们不与 nums2 的首个元素配对，由于 nums2 是有序的，之后的元素会更大，这样并不划算
         *      如果我们与 num2 的首个元素配对，我们使用 nums1 的首个元素可以使得剩余的元素尽可能大，之后可以获得更多「优势」
         *  对于第二种情况，由于 nums2 是有序的，那么 nums1 的首个元素小于等于 nums2 中的任意元素，因此 nums1 的首个元素无法增加任何「优势」，可以直接移除
         *
         * 在本题中，由于 nums1 中的每一个元素都要与 nums2 中的元素配对，而我们是按照顺序考虑 nums2 中的元素的。因此在遍历结束后，nums2 中剩余的元素实际上是原先 nums2 的一个后缀。
         * 因此当 nums1 的首个元素无法配对时，我们给它配对一个 nums2 的尾元素即可，并将该尾元素移除。
         *
         * 在实际的代码编写中，我们无需真正地「移除」元素。
         * 对于 nums1，我们使用一个循环依次遍历其中的每个元素；
         * 对于 nums2，我们可以使用双指针 left 和 right。如果 nums1 的首个元素可以增加「优势」，就配对 left 对应的元素并向右移动一个位置；
         * 如果无法配对，就配对 right 对应的元素并向左移动一个位置。
         * */
        Arrays.sort(idx1, (i, j) -> nums1[i] - nums1[j]);
        Arrays.sort(idx2, (i, j) -> nums2[i] - nums2[j]);

        int[] ans = new int[n];
        int left = 0, right = n - 1;
        for (int i = 0; i < n; ++i) {
            // 如果 nums1 的首个元素可以增加优势，就配对 nums2 left 对应的元素并向右移动一个位置
            if (nums1[idx1[i]] > nums2[idx2[left]]) {
                // nums2的idx2[left]索引位置，配对nums1的idx1[i]索引位置的元素，即将结果数组idx2[left]索引位置放上nums1的idx1[i]索引位置的元素
                ans[idx2[left]] = nums1[idx1[i]];
                ++left;
            } else {    // 如果无法增加优势，就配对 nums2 right 对应的元素并向左移动一个位置，即将结果数组idx2[right]索引位置放上nums1的idx1[i]索引位置的元素
                ans[idx2[right]] = nums1[idx1[i]];
                --right;
            }
        }
        return ans;
    }
}
