package knuthshuffle;

import java.util.Random;

// 洗牌算法
// 选择的每个组合的概率是一样的
// 对于n个不同的元素，有n!个组合，所以选中一个的概率是1/n!
// 比如元素个数为6
// 1.第一次以1/6的概率在6个元素中随机选取一个元素
// 2.第一次以1/5的概率在5个元素中随机选取一个元素
// 3.第一次以1/4的概率在4个元素中随机选取一个元素
// 4.第一次以1/3的概率在3个元素中随机选取一个元素
// 5.第一次以1/2的概率在2个元素中随机选取一个元素
// 6.第一次以1/1的概率在1个元素中随机选取一个元素
// 在计算机中实现，使用数组存储这些元素，需要随机访问
// 1.第一次随机生成[0,5]中的一个数值，作为访问数组的索引，然后将该索引元素与数组末尾元素进行交换
// 2.第二次随机生成[0,4]中的一个数值，作为访问数组的索引，然后将该索引元素与数组最后一个未被选中过的元素进行交换（即倒数第二个元素）
// 3.以此类推，如果是随机选中的元素就是最后一个未被选中的元素其实也没事，就是自己和自己交换
public class Shuffle {
    public void shuffle(int[] arr) {
        Random random = new Random();
        for(int i = arr.length - 1; i >= 0; i--) {
            int chooseIndex = random.nextInt(i + 1);
            swap(arr, chooseIndex, i);
        }
    }

    public void shuffle2(int[] arr) {
        Random random = new Random();
        for(int i = 0; i < arr.length; i++) {
            int chooseIndex = random.nextInt(arr.length - i) + i;
            swap(arr, chooseIndex, i);
        }
    }

    private void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {1,2,3};
        Shuffle shuffle = new Shuffle();
        shuffle.shuffle2(arr);

        for(int i : arr) {
            System.out.println(i);
        }
    }
}
