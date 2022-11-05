package leetcode.p470;


import java.util.Random;

class SolBase {
    private static Random rnd = new Random();
    public int rand7() {
        return rnd.nextInt(7) + 1;
    }
}

public class Solution extends SolBase {
    // 调用两次rand7的组合
    // a = rand7()，b = rand7()
    // (a,b)有序对的组合数为49，这49种可能是等概率出现的
    // 相当于一个矩阵，矩阵的行列编号是(a-1, b-1)，行列长度都是7，所以从中间取10个组合作为可能的结果即可，遇到其他的组合就继续执行两次rand7组合，直到遇到是10种组合之内的
    // 给矩阵的每个行列从0开始编号，作为矩阵的值
    // 矩阵值和矩阵行列编号的关系，t = (a - 1) * 7 + (b - 1)
    // t为[0,39]接受，返回 t % 10 + 1

    // 期望
    // E = 40/49 * 1 + 9/49 * (1 + E)
    public int rand10() {
        int t = 0;

        do {
            int a = rand7(), b = rand7();
            t = (a - 1) * 7 + (b - 1);
        } while (t > 39 || t < 0);

        return t % 10 + 1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.rand10());
    }
}