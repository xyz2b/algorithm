package shellsort;

// 将元素按照固定分隔分组，对每个分组都执行插入排序，然后再缩小一倍间隔继续分组，继续对每个分组执行插入排序，直到分组间隔为1，再执行一次插入排序，就完成了排序过程
public class ShellSort {
    public void sort(int[] nums) {
        // 间隔
        int interval = nums.length / 2;
        // 间隔最终会为1，间隔为1再进行插入排序后就完成了排序过程
        while (interval > 0) {
            // 遍历多个分组的起始元素，[0, interval-1]
            for(int start = 0; start < interval; start++) {
                // 对每个分组执行插入排序，每个分组中的元素间隔为interval（data[start, start+interval, start+2interval, start+3interval, ...]）
                for(int i = start + interval; i < nums.length; i += interval) {
                    // 将nums[i]插入到合适的位置
                    int temp = nums[i];
                    int j;
                    for(j = i; j >= interval; j -= interval) {
                        if(temp < nums[j - interval]) {
                            nums[j] = nums[j - interval];
                        } else {
                            break;
                        }
                    }
                    nums[j] = temp;
                }
            }
            // 缩小一倍间隔
            interval = interval / 2;
        }
    }

    private void swap(int[] data, int src, int dst) {
        int temp = data[src];
        data[src] = data[dst];
        data[dst] = temp;
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
        ShellSort shellSort = new ShellSort();
        shellSort.sort(data);
        System.out.println(shellSort.toString(data));
    }
}
