package insertionsort;

public class InsertionSort {
    public void sort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            for (int j = i; j > 0; j--) {
                if (nums[j] < nums[j-1]) {
                    swap(nums, j, j-1);
                }
            }
        }
    }

    public void sort2(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            // 将nums[i]插入到合适的位置
            int temp = nums[i];
            int j;
            for (j = i; j > 0; j--) {
                if (temp < nums[j-1]) {
                    nums[j] = nums[j-1];
                } else {
                    break;
                }
            }
            nums[j] = temp;
        }
    }

    private void swap(int[] nums, int src, int dst) {
        int temp = nums[src];
        nums[src] = nums[dst];
        nums[dst] = temp;
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
        int[] data = {4, 453, 532, 3412312, 5364, 7, 31};
        InsertionSort insertionSort = new InsertionSort();
        insertionSort.sort2(data);
        System.out.println(insertionSort.toString(data));
    }
}
