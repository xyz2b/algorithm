package leetcode.p307;

public class NumArraySQRT {
    private int[] data;
    // 每个分组的和
    private int[] p;
    // 元素数量
    private int size;
    // 每个分组元素数量
    private int psize;
    // 分数数量
    private int pcount;

    public NumArraySQRT(int[] nums) {
        data = nums;
        size = nums.length;

        psize = (int) Math.sqrt(size);
        pcount = size / psize + (size % psize > 0 ? 1 : 0);

        p = new int[pcount];

        for(int i = 0; i < size; i++) {
            p[i / psize] += data[i];
        }
    }

    public int sumRange(int left, int right) {
        if(left < 0 || left >= size || right < 0 || right >= size || left > right) {
            return 0;
        }

        // left在第几个分组
        int lp = left / psize;
        // right在第几个分组
        int rp = right / psize;

        int rst = 0;
        // left和right在同一个分组，直接遍历
        if(lp == rp) {
            for(int i = left; i <= right; i++) {
                rst += data[i];
            }
            return rst;
        }

        // left和right不在同一个分组
        // 1.遍历left到它所在分组末尾元素
        // (lp + 1) * psize就是left所在分组后一个分组的开头的元素
        for(int i = left; i < (lp + 1) * psize; i++) {
            rst += data[i];
        }
        // 2.遍历left所在分组之后和right所在分组之前的分组
        for(int k = lp + 1; k < rp; k++) {
            rst += p[k];
        }
        // 3.遍历right所在分组开头的元素到right
        // rp * psize就是right所在分组开头的元素
        for(int i = rp * psize; i <= right; i++) {
            rst += data[i];
        }

        return rst;
    }

    public void update(int index, int val) {
        int old = data[index];
        data[index] = val;
        p[index / psize] = p[index / psize] + val - old;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {-1};
        NumArraySQRT numArraySQRT = new NumArraySQRT(nums);
        System.out.println(numArraySQRT.sumRange(0, 0));
    }
}
