package reservoir;

import java.util.Arrays;
import java.util.Random;

// 蓄水池算法
// 处理数据流中的多次抽样问题
// 从数据流中抽取k个元素，使得数据流中每一个元素可以被等概率的抽取
public class Reservoir {
    public int[] randomSelectK(int[] arr, int k) {
        if(arr.length < k) {
            return null;
        }

        // 先从arr中取出k个元素作为蓄水池，rst就是蓄水池
        int[] rst = Arrays.copyOf(arr, k);

        Random random = new Random();
        // 处理数据流中索引为i的元素(第i+1个元素)，起始就是arr中索引为k的元素
        for(int i = k; i < arr.length; i++) {
            // 随机生成[0, i]之间的一个索引j
            int j = random.nextInt(i+1);
            // 如果j在[0...k-1]之间，则将这个新元素替换蓄水池[j]，否则扔掉
            if(j >= 0 && j <= k-1) {
                rst[j] = arr[i];
            }
        }

        return rst;
    }


    private int k;
    // 蓄水池，大小为k
    private int[] reservoir;
    // 当前蓄水池中的元素数量，最多为k
    private int size;
    private Random random;
    public Reservoir(int k) {
        this.k = k;
        reservoir = new int[k];
        size = 0;
        random = new Random();
    }

    public void fetch(int new_value) {
        if(size < k) {  // 蓄水池还没满，直接丢进去
            reservoir[size] = new_value;
            return;
        }

        // 蓄水池满了
        // 处理数据流中索引为i的元素(第i+1个元素)，其实就是k，第k个元素就是新加的元素应该具有的索引
        // 随机生成[0, i]之间的一个索引j
        int j = random.nextInt(k+1);
        // 如果j在[0...k-1]之间，则将这个新元素替换蓄水池[j]，否则扔掉
        if(j >= 0 && j <= k-1) {
            reservoir[j] = new_value;
        }
    }

    public int[] sampling() {
        return reservoir.clone();
    }
}
