package bucketsort;

// 桶排序
// 只适用于元素值的大小较小的情况，因为桶的大小就是元素值最大的那个元素的
// 元素值大于等于0
public class BucketSortSmallValue {
    public void sort(int[] nums) {
        int max = getMax(nums);

        int cnt[] = new int[max + 1];

        for (int i = 0; i < nums.length; i++) {
            cnt[nums[i]]++;
        }

        int index = 0;
        for(int i = 0; i < cnt.length; i++) {
            for (int j = 1; j <= cnt[i]; j++) {
                nums[index] = i;

                index++;
            }
        }
    }

    private int getMax(int[] data) {
        int max = 0;

        for (int i = 0; i < data.length; i++) {
            if (data[i] > max) {
                max = data[i];
            }
        }

        return max;
    }

    public String toString(int[] data) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i = 0; i < data.length; i++) {
            stringBuilder.append(data[i]).append(", ");
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        BucketSortSmallValue bucketSortSmallValue = new BucketSortSmallValue();
        int[] heights = {1, 1, 4, 2, 1, 3, 0, 0};
        bucketSortSmallValue.sort(heights);
        System.out.println(bucketSortSmallValue.toString(heights));
    }
}
