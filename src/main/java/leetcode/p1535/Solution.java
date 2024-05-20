package leetcode.p1535;

class Solution {
    /**
     如果k==1 结果就是第一个数和第二个数中最大的那
     和第i个数pk的一定是前i-1个数中赢得那个
     设置两个变量 一个是前i-1个数中赢得数 第二个它是赢得轮数
     如果赢得轮数等于k 结果就是它
     如果数组遍历结束 也没有赢的轮数为k的数 则结果是数组中的最大数 循环pk之后最大数总会赢到k轮 因为pk输的都会挪到数组末尾 数组中所有元素pk一遍之后就会进入循环
     */
    public int getWinner(int[] arr, int k) {
        if(arr.length == 1) {
            return arr[0];
        }
        if(k == 1) {
            return Math.max(arr[0], arr[1]);
        }

        int winner = arr[0];
        int winCount = 0;
        int maxNum = arr[0];
        for(int i = 1; i < arr.length; i++) {
            if(winner > arr[i]) {
                winCount++;
            } else {
                winner = arr[i];
                winCount = 1;
            }
            maxNum = Math.max(arr[i],maxNum);
            if(winCount == k) {
                return winner;
            }
        }
        return maxNum;
    }
}
