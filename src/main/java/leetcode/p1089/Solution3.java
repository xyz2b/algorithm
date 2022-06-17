package leetcode.p1089;

public class Solution3 {
    /**
     * 先计算出 当前数组完成0复制和移送数据之后
     * 当前数组中最后一个还能在结果中的元素索引位置
     * 然后从最后一个还能在结果中的元素位置开始往原数组前面遍历，然后把它们放到结果中相应的位置，该位置之后的元素都是被移出去的元素
     *
     *
     * 因为如果有0 最后一个还能在结果数组中的数据位置一定不是最后一个
     * 如果没有0 最后一个还能在结果数组中的数据位置一定是最后一个 相同的位置相互替换也没有问题
     * */
    public void duplicateZeros(int[] arr) {
        int length = arr.length;
        int top = 0;
        int i = -1;

        // 找到最后一个还能在结果数组中的元素索引 i
        // 元素为0，top+2，元素不为0，top+1
        // top最大值不能超过现有数组元素的最大索引
        while (top < length) {
            i++;
            if (arr[i] == 0) {
                top = top + 2;
            } else {
                top++;
            }
        }

        // 如果top大于length，意思就是最后一个还能在结果数组中的元素是0，由于0要再复制一个，所以它占两个位置，就会使得top大于length，超出数组界限，需要特殊处理。所以此时只能放入一个0进入到结果数组中，这个0就放在结果数组的末尾
        // 如果top不大于length，意思就是最后一个还能在结果数组中的元素不是0，这里就不用特殊处理，不会发生越界情况
        int j = length - 1;
        if (top > length) {
            arr[j] = 0;
            j--;
            i--;
        }

        // 从上面得到的最后一个还在结果中的元素索引i位置处向前遍历，将它们写到结果中对应的位置上
        // 原数组从后往前遍历，即j索引，将还留在数组中的元素依次写入对应位置
        // 1.把i位置上的元素，放到j位置上即可
        // 2.如果i位置上元素为0，则j-1位置上应该也是0
        while (j >= 0) {
            arr[j] = arr[i];
            j--;
            if (arr[i] == 0) {
                arr[j] = arr[i];
                j--;
            }
            i--;
        }
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
        Solution3 solution = new Solution3();
        int[] data = {1,0,2,3,0,4,5,0};
        int[] data1 = {1, 2, 3};
        int[] data3 = {0, 0, 3, 0, 0, 4};
        solution.duplicateZeros(data3);
        System.out.println(solution.toString(data3));
    }
}
