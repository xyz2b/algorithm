package leetcode.p670;

class Solution {
    /**
     * 根据以上等式我们可以看出，若使得 \textit{val}val 的值最大，应依次满足如下条件：
     *
     * 最优的交换一定需要满足 d_j > d_k；
     * 在满足 d_j > d_k时，应该保证索引 k 越大从而使得数字 val 越大；
     * 在同样大小的数字 d_k时，应使得数字 d_j 越大从而使得 val 越大；
     * 在同样大小的数字 d_j 时，应使得索引 j 越小从而使得 val 越大；
     *
     * 通过以上可以观察到右边越大的数字与左边较小的数字进行交换，这样产生的整数才能保证越大。因此我们可以利用贪心法则，尝试将数字中右边较大的数字与左边较小的数字进行交换，这样即可保证得到的整数值最大。具体做法如下：
     *
     * 我们将从右向左扫描数字数组，并记录当前已经扫描过的数字的最大值的索引为 maxId 且保证 maxId 越靠近数字的右侧，此时则说明 charArray[maxId] 则为当前已经扫描过的最大值。
     * 如果检测到当前数字 charArray[i]<charArray[maxId]，此时则说明索引 i 的右侧的数字最大值为 charArray[maxId]，此时我们可以尝试将 charArray[i] 与 charArray[maxId] 进行交换即可得到一个比 num 更大的值。我们尝试记录当前可以交换的数对(i,maxId)，根据贪心法则，此时最左边的 i 构成的可交换的数对进行交换后形成的整数值最大。
     * */
    public int maximumSwap(int num) {
        char[] charArray = String.valueOf(num).toCharArray();
        int n = charArray.length;
        int maxIdx = n - 1;
        int idx1 = -1, idx2 = -1;
        for (int i = n - 1; i >= 0; i--) {
            if (charArray[i] > charArray[maxIdx]) {
                maxIdx = i;
            } else if (charArray[i] < charArray[maxIdx]) {
                idx1 = i;
                idx2 = maxIdx;
            }
        }
        if (idx1 >= 0) {
            swap(charArray, idx1, idx2);
            return Integer.parseInt(new String(charArray));
        } else {
            return num;
        }
    }

    public void swap(char[] charArray, int i, int j) {
        char temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
    }
}
