package leetcode.p1184;

public class Solution {
    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        int n = distance.length;

        // 顺时针走的距离
        int clockwise = 0;
        // 逆时针走的距离
        int counterclockwise = 0;

        // 顺时针走当前所处于的位置
        int i = start;
        // 逆时针走当前所处于的位置
        int j = start;

        // 标识顺时针是否到达终点
        int clockwiseFlag = 0;
        // 标识逆时针是否达到终点
        int counterclockwiseFlag = 0;
        // 因为distance[i] 表示编号为 i 的车站和编号为 (i + 1) % n 的车站之间的距离，所以逆时针走的时候，从j 到 j-1的距离，在因为distance[j-1]上，所以这里的判断条件是j < destination就是说明逆时针走到了终点并且距离累加上了
        while (i != destination || j >= destination) {
            // 如果顺时针还未到达终点，继续累加距离往前走
            if (clockwiseFlag == 0) {
                clockwise += distance[i];
                i = (i + 1) % n;
            }

            // 如果逆时针还未到达终点，继续累加距离往后走
            if (counterclockwiseFlag == 0) {
                // 逆时针走的时候，从j 到 j-1的距离，在因为distance[j-1]上，所以需要先将j往后挪一步，然后再加上当前j所表示的距离（即从j 到 j-1的距离）
                j = j == 0 ? n : j - 1;
                counterclockwise += distance[j];
            }

            // 如果顺时针已经达到终点
            if (i == destination) {
                // 如果顺时针已经达到终点并且顺时针达到终点的距离小于等于当前逆时针走的距离（不管逆时针有没有到达终点）
                if(clockwise <= counterclockwise) {
                    break;
                } else {     // 如果顺时针已经达到终点并且顺时针达到终点的距离大于当前逆时针走的距离，这时就需要逆时针继续走，看看哪个小，此时将顺时针到达终点标识记为1
                    clockwiseFlag = 1;
                }
            }

            // 如果逆时针已经达到终点
            if (j == destination) {
                // 如果逆时针已经达到终点并且逆时针达到终点的距离小于等于当前顺时针走的距离（不管顺时针有没有到达终点）
                if (counterclockwise <= clockwise) {
                    break;
                } else { // 如果逆时针已经达到终点并且逆时针达到终点的距离大于当前顺时针走的距离，这时就需要顺时针继续走，看看哪个小，此时将逆时针到达终点标识记为1
                    counterclockwiseFlag = 1;
                }
            }
        }

        // 顺时针先到达终点，并且距离比当前逆时针走的小
        if (clockwiseFlag == 1 && counterclockwiseFlag != 1) {
            return clockwise;
        } else if (clockwiseFlag != 1 && counterclockwiseFlag == 1) {           // 逆时针先到达终点，并且距离比当前顺时针走的小
            return counterclockwise;
        } else {     // 顺时针逆时针一起到达终点，返回最小的即可
            return Math.min(clockwise, counterclockwise);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] distance = {12,27,2,5,0,27,14,17,8,3,6,9,16,19};
        System.out.println(solution.distanceBetweenBusStops(distance, 12, 13));
    }
}
