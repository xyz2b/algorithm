package shellsort;

// 将多个元素按照固定间隔分组，对每个分组都执行插入排序，然后再缩小一倍间隔继续分组，继续对每个分组执行插入排序，直到分组间隔为1，再执行一次插入排序，就完成了排序过程
public class ShellSort {
    public void sort(int[] nums) {
        // 分组间隔
        int interval = nums.length / 2;
        // 间隔最终会为1，间隔为1再进行插入排序后就完成了排序过程
        while (interval > 0) {
            // 遍历多个分组的起始元素，[0, interval-1]
            for(int start = 0; start < interval; start++) {
                // 对每个分组执行插入排序，每个分组中的元素间隔为interval（即对，data[start, start+interval, start+2interval, start+3interval, ...]，执行插入排序）
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
            // 缩小一倍间隔继续分组
            interval = interval / 2;
        }
    }


    public void sort2(int[] nums) {
        // 分组间隔
        int interval = nums.length / 2;
        // 间隔最终会为1，间隔为1再进行插入排序后就完成了排序过程
        while (interval > 0) {
            // 对于多个分组的元素，混着处理，处理完一个分组的元素，直接处理下一个分组的元素，只需要在内层循环找到当前处理的元素所属于分组的元素进行比较即可（内层循环不需要改变）
            for(int i = interval; i < nums.length; i ++) {
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

            // 缩小一倍间隔继续分组
            interval = interval / 2;
        }
    }

    public void sort3(int[] nums) {
        // 分组间隔（使用不同的步长序列来优化希尔排序的性能）
        int interval = 1;
        while (interval < nums.length) interval = interval * 3 + 1;
        // 间隔最终会为1，间隔为1再进行插入排序后就完成了排序过程
        while (interval > 0) {
            // 对于多个分组的元素，混着处理，处理完一个分组的元素，直接处理下一个分组的元素，只需要在内层循环找到当前处理的元素所属于分组的元素进行比较即可（内层循环不需要改变）
            for(int i = interval; i < nums.length; i ++) {
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

            // 缩小一倍间隔继续分组
            interval = interval / 3;
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
        shellSort.sort3(data);
        System.out.println(shellSort.toString(data));
    }
}
