package leetcode.p3096;

public class Solution {
    // 前缀和
    public int minimumLevels(int[] possible) {
        int ret = -1;
        int n = possible.length;
        // 将其中的所有0替换成-1，即分数
        // 同时计算完成所有关卡的总分数
        int allPoint = 0;
        for(int i = 0; i < n; i++) {
            if (possible[i] == 0) {
                possible[i] = -1;
            }
            allPoint += possible[i];
        }

        // 前缀和
        // 完全当前关卡以及前面所有关卡的得分
        int[] sum = new int[n+1];
        for(int i = 0; i < n; i++) {
            sum[i+1] = sum[i] + possible[i];
        }

        // 因为最后必须剩下一个关卡给Bob完成，所以这里遍历终点不能到最后一个关卡n
        for(int i = 1; i < n; i++) {
            if(sum[i] > allPoint - sum[i]) {
                ret = i;
                break;
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        int[] possible = {0,0};
        Solution solution = new Solution();
        System.out.println(solution.minimumLevels(possible));
    }
}
