package leetcode.p2961;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<Integer> getGoodIndices(int[][] variables, int target) {
        List<Integer> ret = new ArrayList<>();

        for(int i = 0; i < variables.length; i++) {
            int a = variables[i][0];
            int b = variables[i][1];
            int c = variables[i][2];
            int m = variables[i][3];

            // ab = (a ^ b) % 10
            int lessA = a % 10;
            int ab = lessA;
            for(int j = 2; j <= b; j++) {
                ab = (ab * lessA) % 10;
            }

            // abc = (ab ^ c) % m
            int lessAb = ab % m;
            int abc = lessAb;
            for(int j = 2; j <= c; j++) {
                abc = (abc * lessAb) % m;
            }

            if(abc == target) {
                ret.add(i);
            }
        }

        return ret;
    }
}
