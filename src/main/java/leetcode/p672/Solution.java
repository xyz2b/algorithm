package leetcode.p672;

// 找规律
public class Solution {
    /**
     * 一共就四种操作，且每个操作只有一次和零次的区别 (同一个操作按两次等于没按)
     *
     * 当灯泡个数为2时，操作4等同于操作3，故特殊讨论。
     * 当灯泡个数为1时，操作4等同于操作3，操作2等同于空操作，故特殊讨论。
     * 当灯泡个数足够多(大于2)时，每个操作各不相同，我们讨论操作次数来确认我们的选择。
     *
     * */
    public int flipLights(int n, int presses) {
        if (presses == 0) {
            return 1;
        }
        if (n == 1) {
            return 2;
        }
        if (n == 2) {
            return presses == 1 ? 3 : 4;
        }
        if (presses == 1) {
            return 4;
        }
        return presses == 2 ? 7 : 8;
    }
}
